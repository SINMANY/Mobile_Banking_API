package co.istad.mbanking.api.accounttype.web;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
public record CreateNewAccountTypeDto(@NotBlank(message = "Name is required..!") String name) {
}
