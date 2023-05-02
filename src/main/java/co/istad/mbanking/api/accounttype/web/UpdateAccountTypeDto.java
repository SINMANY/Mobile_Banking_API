package co.istad.mbanking.api.accounttype.web;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UpdateAccountTypeDto(@NotBlank(message = "Name is required..!") String name) {
}
