package co.istad.mbanking.api.user.web;

import lombok.Builder;

@Builder
public record SelectUserByNameDto(String name) {
}
