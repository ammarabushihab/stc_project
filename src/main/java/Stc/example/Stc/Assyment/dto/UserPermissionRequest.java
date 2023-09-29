package Stc.example.Stc.Assyment.dto;

import Stc.example.Stc.Assyment.entity.PermissionType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserPermissionRequest {
    @Email
    private String userEmail;
    @NotNull
    private PermissionType permissionType;
}
