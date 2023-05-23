package co.istad.mbanking.api.user.web;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.List;

@Builder
public record UserDto(@NotBlank(message = "Name is required..!") String name,
                      @NotBlank(message = "Gender is required..!") String gender,
                      String studentCardId,
                      Boolean isStudent,
                      List<Role> roles) {
}
