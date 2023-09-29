package Stc.example.Stc.Assyment.dto;

import lombok.Data;

import java.util.List;

@Data
public class SpaceRequest {

    private String spaceName ;
    private String groupName ;
    private List<UserPermissionRequest> userPermissions;


}
