package Stc.example.Stc.Assyment.controller;

import Stc.example.Stc.Assyment.dto.FileRequest;
import Stc.example.Stc.Assyment.dto.FolderRequest;
import Stc.example.Stc.Assyment.dto.SpaceRequest;
import Stc.example.Stc.Assyment.entity.FileData;
import Stc.example.Stc.Assyment.entity.Item;
import Stc.example.Stc.Assyment.service.ItemService;
import io.swagger.v3.oas.annotations.headers.Header;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/item")
public class ItemController {
@Autowired
private ItemService itemService;


    @GetMapping(value = "/find-file/{name}", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public byte[] findAllFile(@PathVariable  String name, @RequestHeader String email){
    return itemService.getFile(name, email);

    }

    @PostMapping("/space")
    public void createSpace(@RequestBody @Valid SpaceRequest spaceRequest){
    itemService.createSpace(spaceRequest);

    }
    @PostMapping("/folder")
    public void createFolder(@RequestBody @Valid FolderRequest folderRequest){
        itemService.createFolder(folderRequest);

    }
    @PostMapping( value = "/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void createFile(@Valid FileRequest fileRequest) throws IOException {
    itemService.createFile(fileRequest);

    }

}
