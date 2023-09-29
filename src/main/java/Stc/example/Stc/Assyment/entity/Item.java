package Stc.example.Stc.Assyment.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
@Entity

public class Item implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @Enumerated(EnumType.STRING)
    private ItemType type ;
    private String name;


    @ManyToOne
    @JoinColumn(name = "permission_group_id")
    private PermissionGroup permissionGroup;



    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "file_data_id",referencedColumnName = "id")
    private FileData fileData;


    @ManyToOne
    //To know This File Under Space or Folder
    private Item parent;
    @OneToMany(mappedBy = "parent")
    private List<Item> children ;



}
