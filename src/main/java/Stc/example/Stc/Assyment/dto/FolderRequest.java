package Stc.example.Stc.Assyment.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FolderRequest{
    @NotNull
    private  String folderName;
    @NotNull
    private String spaceName ;
    @Email
    private String userEmail ;


}
