package co.istad.mbanking.api.user.validator.password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class PasswordMatchConstraintsValidator implements ConstraintValidator<PasswordMatch, Object> {

    private String password;
    private String confirmPassword;
    private String message;

    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
//        all of these are just properties, they are not values yet.
//        so we need to use isValid below to check the values
       this.password = constraintAnnotation.password();
       this.confirmPassword = constraintAnnotation.confirmPassword();
       this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object passwordValues = new BeanWrapperImpl(value).getPropertyValue(password);
        Object confirmPasswordValues = new BeanWrapperImpl(value).getPropertyValue(confirmPassword);

//        logic that we apply
        boolean isValid = false;
        if(passwordValues != null){
            isValid = passwordValues.equals(confirmPasswordValues);
        }
        if(!isValid){
            // Sending one message each time failed validation.
            context.disableDefaultConstraintViolation();

//            Build message for password property
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(password)
                    .addConstraintViolation();

//            Build message for confirmed password property
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(confirmPassword)
                    .addConstraintViolation();
        }
        return isValid;
    }
}
