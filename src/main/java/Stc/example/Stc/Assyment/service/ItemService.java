package Stc.example.Stc.Assyment.service;

import Stc.example.Stc.Assyment.dto.FileRequest;
import Stc.example.Stc.Assyment.dto.FolderRequest;
import Stc.example.Stc.Assyment.dto.SpaceRequest;
import Stc.example.Stc.Assyment.dto.UserPermissionRequest;
import Stc.example.Stc.Assyment.entity.*;
import Stc.example.Stc.Assyment.error.RecordFoundException;
import Stc.example.Stc.Assyment.error.RecordNotFoundException;
import Stc.example.Stc.Assyment.error.UserDoseNotHavePermission;
import Stc.example.Stc.Assyment.repository.FileRepository;
import Stc.example.Stc.Assyment.repository.ItemRepository;
import Stc.example.Stc.Assyment.repository.PermissionGroupRepository;
import Stc.example.Stc.Assyment.repository.PermissionRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private PermissionGroupRepository permissionGroupRepository;
    @Autowired
    private PermissionRepository permissionRepository;


    public void createSpace (SpaceRequest spaceRequest){

        if (itemRepository.existsByNameAndTypeIn(spaceRequest.getSpaceName(), List.of(ItemType.SPACE))){
            throw  new RecordFoundException("Already Exist");

        }

        if (permissionGroupRepository.existsByName(spaceRequest.getGroupName())){
            throw  new RecordFoundException("Already Exist");

        }

        Item space =new Item();
        space.setName(spaceRequest.getSpaceName());
        space.setType(ItemType.SPACE);
        /*

    private String userEmail ;
    private  PermissionType permissionLevel ;*/
        PermissionGroup permissionGroup = new PermissionGroup();
        permissionGroup.setName(spaceRequest.getGroupName());

        List<Permission> permissions = spaceRequest.getUserPermissions().stream()
                .map(value -> mapToPermission(value, permissionGroup))
                .collect(Collectors.toList());

        permissionGroup.setPermissionList(permissions);

        space.setPermissionGroup(permissionGroup);

        permissionGroupRepository.save(permissionGroup);
        permissionRepository.saveAll(permissions);
        itemRepository.save(space);

    }

    public void createFolder (FolderRequest folderRequest){

        Optional<Item> space = itemRepository.findByNameAndTypeIn(folderRequest.getSpaceName(), List.of(ItemType.SPACE, ItemType.FOLDER));


        if (!space.isPresent()){
            throw new RecordNotFoundException("Space Does Not Exist");
        }

        boolean havePermission = space.get().getPermissionGroup().getPermissionList().stream()
                .anyMatch(permission -> permission.getUserEmail().equals(folderRequest.getUserEmail())
                        && permission.getPermissionLevel().equals(PermissionType.EDIT));
        if(!havePermission){
            throw new RecordNotFoundException("User doe's not have permission");
        }

        Item item=new Item();
        item.setName(folderRequest.getFolderName());
        item.setParent(space.get());
        item.setPermissionGroup(space.get().getPermissionGroup());
        item.setType(ItemType.FOLDER);


        itemRepository.save(item);

    }

    @Transactional
    public void createFile (FileRequest fileRequest) throws IOException {

        boolean nameExist = itemRepository.existsByNameAndTypeIn(fileRequest.getFileName(), List.of(ItemType.File));
        if(nameExist){
            throw new RecordFoundException("File already exist");
        }

        Optional<Item> directory = itemRepository.findByNameAndTypeIn(fileRequest.getDirectoryName(), List.of(ItemType.SPACE, ItemType.FOLDER));


        if (!directory.isPresent()){
            throw new RecordNotFoundException("Directory Does Not Exist");
        }

        boolean havePermission = directory.get().getPermissionGroup().getPermissionList().stream()
                .anyMatch(permission -> permission.getUserEmail().equals(fileRequest.getUserEmail())
                        && permission.getPermissionLevel().equals(PermissionType.EDIT));
        if(!havePermission){
            throw new UserDoseNotHavePermission("User doe's not have permission");
        }

        boolean nameDoExist = itemRepository.findByParentId(directory.get().getId()).stream().anyMatch(item -> item.getName().equalsIgnoreCase(fileRequest.getFileName()));
        if(nameDoExist){
            throw new RecordFoundException("File name already exist in directory");
        }

        FileData fileData=new FileData();
        fileData.setBinary(fileRequest.getFile().getBytes());

        fileRepository.save(fileData);

        Item item=new Item();
        item.setType(ItemType.File);
        item.setName(fileRequest.getFileName());
        item.setPermissionGroup(directory.get().getPermissionGroup());
        item.setParent(directory.get());
        item.setFileData(fileData);


        itemRepository.save(item);

    }

    private Permission mapToPermission(UserPermissionRequest userPermissionRequest, PermissionGroup permissionGroup){
        Permission permission = new Permission();
        permission.setUserEmail(userPermissionRequest.getUserEmail());
        permission.setPermissionLevel(userPermissionRequest.getPermissionType());
        permission.setPermissionGroup(permissionGroup);
        return permission;
    }


    @Transactional
    public byte[] getFile(String name, String email) {
        Optional<Item> file = itemRepository.findByNameAndTypeIn(name, List.of(ItemType.File));


        if (!file.isPresent()){

            throw new RecordNotFoundException("The File Does Not Exist");
        }
        boolean havePermission =file.get().getPermissionGroup().getPermissionList().stream().anyMatch(permission -> permission.getUserEmail().equalsIgnoreCase(email));


        if (!havePermission ){
            throw new UserDoseNotHavePermission("User Does Not Have Permission");
        }

        byte[] fileData = file.get().getFileData().getBinary();


        return fileData;
    }
    }


