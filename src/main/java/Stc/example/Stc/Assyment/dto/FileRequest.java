package Stc.example.Stc.Assyment.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileRequest {

private String fileName ;
//Folder Or Space
private String directoryName ;
private String userEmail ;
private MultipartFile file ;



}
