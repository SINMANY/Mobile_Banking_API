package co.istad.mbanking.api.notification;

import co.istad.mbanking.api.notification.web.CreateNotificationDto;
import co.istad.mbanking.api.notification.web.NotificationDto;
import org.springframework.stereotype.Service;

@Service
public interface NotificationService {
    boolean pushNotification(CreateNotificationDto notificationDto);
}
