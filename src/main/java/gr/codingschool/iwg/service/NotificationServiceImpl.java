package gr.codingschool.iwg.service;

import gr.codingschool.iwg.model.Notification;
import gr.codingschool.iwg.model.User;
import gr.codingschool.iwg.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService{
    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public List<Notification> findNotificationsByUser(User user) {
        return notificationRepository.findAllByUser(user);
    }

    @Override
    public List<Notification> findUnreadNotificationsByUser(User user) {
        return notificationRepository.findAllByUserAndRead(user, false);
    }

    @Override
    public Notification readNotification(int id){
        Notification notification = notificationRepository.findById(id);
        notification.setRead(true);
        notification = notificationRepository.save(notification);
        return notification;
    }

    @Override
    public Notification unreadNotification(int id){
        Notification notification = notificationRepository.findById(id);
        notification.setRead(false);
        notification = notificationRepository.save(notification);
        return notification;
    }

    @Override
    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public int delete(int id) {
        return notificationRepository.deleteById(id);
    }
}
