package co.istad.mbanking.api.user.web;

import lombok.Builder;

@Builder
public record SelectByStudentCardIdDto(String studentCardId) {
}
