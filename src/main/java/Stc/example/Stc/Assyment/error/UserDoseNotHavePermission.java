package Stc.example.Stc.Assyment.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserDoseNotHavePermission extends RuntimeException{


    public UserDoseNotHavePermission(String message) {
        super(message);
    }

}
