package co.istad.mbanking.api.auth.web;

import co.istad.mbanking.api.user.validator.email.EmailUnique;
import co.istad.mbanking.api.user.validator.password.Password;
import co.istad.mbanking.api.user.validator.password.PasswordMatch;
import co.istad.mbanking.api.user.validator.role.RoleIdConstraints;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
@PasswordMatch(password = "password", confirmPassword = "confirmPassword")
public record RegisterDto(
        @NotBlank(message = "The field email is required.")
        @EmailUnique
        @Email
        String email,
        @NotBlank(message = "The field password is required.")
        @Password
        String password,
        @NotBlank(message = "The field confirm_password is required.")
        @Password
        String confirmPassword,

        @NotNull(message = "The field roles is required.")
        @RoleIdConstraints
        List<Integer> roleIds
) {
}