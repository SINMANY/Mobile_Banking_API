package co.istad.mbanking.api.user;

import co.istad.mbanking.api.user.web.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class User {
    private Integer id;
    private String name;
    private String gender;
    private String oneSignalId;
    private String studentCardId;
    private Boolean isStudent;
    private Boolean isDeleted;


//    Auth feature
    private String email;
    private String password;
    private Boolean isVerified;
    private String verifiedCode;

    private List<Role> roles;
}
