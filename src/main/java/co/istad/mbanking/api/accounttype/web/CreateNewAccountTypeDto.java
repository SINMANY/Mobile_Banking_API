package co.istad.mbanking.api.accounttype.web;

import lombok.Builder;

@Builder
public record CreateNewAccountTypeDto(String name) {
}
