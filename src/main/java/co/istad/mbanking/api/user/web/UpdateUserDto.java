package co.istad.mbanking.api.user.web;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UpdateUserDto(@NotBlank(message = "Name is required..!") String name,
                            @NotBlank(message = "Gender is required..!") String gender) {
}
