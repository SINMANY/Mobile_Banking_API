package co.istad.mbanking.api.notification.web;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateNotificationDto(@JsonProperty("included_segments")
                                    String[] includeSegments,
                                    ContentDto contents) {
}
