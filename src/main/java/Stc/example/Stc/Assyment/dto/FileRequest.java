package Stc.example.Stc.Assyment.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileRequest {
    @NotNull
private String fileName ;
//Folder Or Space
@NotNull
private String directoryName ;
@Email
private String userEmail ;
private MultipartFile file ;



}
