package gr.codingschool.iwg.service;

import gr.codingschool.iwg.model.Notification;
import gr.codingschool.iwg.model.User;

import java.util.List;

public interface NotificationService {
    List<Notification> findNotificationsByUser(User user);
    List<Notification> findUnreadNotificationsByUser(User user);
    Notification save(Notification notification);
    int delete(int id);
}
