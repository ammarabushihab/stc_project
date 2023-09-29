package Stc.example.Stc.Assyment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    private String userEmail ;
    private  PermissionType permissionLevel ;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private PermissionGroup permissionGroup;




}
