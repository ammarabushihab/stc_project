package Stc.example.Stc.Assyment.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class SpaceRequest {
    @NotNull
    private String spaceName ;
    @NotNull
    private String groupName ;
    @NotEmpty
    private List<UserPermissionRequest> userPermissions;


}
