package models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor @Builder @ToString @Getter
public class LoginSucesso {
    private String message;
    private String authorization;
}
