package co.istad.mbanking.api.file;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public record FileDto(String name,
                      String url,
                      String extension,
                      String downloadUrl,
                      long size) {
}
