package models.request;

import lombok.*;

@AllArgsConstructor @Getter @Setter @ToString @Builder
public class LoginPayload {
    private String email;
    private String password;
}
