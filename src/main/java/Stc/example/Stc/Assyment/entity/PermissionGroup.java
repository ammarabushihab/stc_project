package Stc.example.Stc.Assyment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PermissionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    private String name ;
    @OneToMany(mappedBy = "permissionGroup")
    private List<Item> itemList ;

    @OneToMany(mappedBy = "permissionGroup")
    private  List<Permission> permissionList;
}
