package co.istad.mbanking.api.user.validator.password;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;



@Constraint(validatedBy = PasswordMatchConstraintsValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface PasswordMatch {
    String message() default "Your password is not match, please check again!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String password();
    String confirmPassword();

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List{
        PasswordMatch[] value();
    }

}
