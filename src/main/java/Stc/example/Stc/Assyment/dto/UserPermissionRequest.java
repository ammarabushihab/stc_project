package Stc.example.Stc.Assyment.dto;

import Stc.example.Stc.Assyment.entity.PermissionType;
import lombok.Data;

@Data
public class UserPermissionRequest {
    private String userEmail;
    private PermissionType permissionType;
}
