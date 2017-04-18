package gr.codingschool.iwg.web.admin;

import gr.codingschool.iwg.model.Game;
import gr.codingschool.iwg.model.Notification;
import gr.codingschool.iwg.model.User;
import gr.codingschool.iwg.service.NotificationService;
import gr.codingschool.iwg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class NotificationsController {
    @Autowired
    private UserService userService;
    @Autowired
    private NotificationService notificationService;

    @RequestMapping(value = {"/admin/notifications"}, method = RequestMethod.GET)
    public ModelAndView adminNotifications(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        User loggedInUser = (User) session.getAttribute("user");
        List<User> users = userService.findAll();
        modelAndView.addObject("user", loggedInUser);
        modelAndView.addObject("list", users);
        modelAndView.setViewName("admin/notifications");
        return modelAndView;
    }

    @RequestMapping(value = {"/admin/notifications/send"}, method = RequestMethod.GET)
    public ModelAndView adminCreateNotificationGame(@RequestParam("username") String username, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        User loggedInUser = (User) session.getAttribute("user");
        User userToSend = userService.findByUsername(username);
        Notification notification = new Notification();
        notification.setUser(userToSend);
        modelAndView.addObject("user", loggedInUser);
        modelAndView.addObject("userToSend", userToSend);
        modelAndView.addObject("notification", notification);
        modelAndView.setViewName("admin/notifications/send");
        return modelAndView;
    }

    @RequestMapping(value = {"/admin/notifications/send"}, method = RequestMethod.POST)
    public ModelAndView adminSendNotification(User userToSend, Notification notification, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        User loggedInUser = (User) session.getAttribute("user");
        userToSend = userService.findByUsername(userToSend.getUsername());
        notification.setUser(userToSend);
        notificationService.save(notification);
        modelAndView.addObject("user", loggedInUser);
        modelAndView.addObject("notification", notification);
        modelAndView.setViewName("/admin/notifications/sent");
        return modelAndView;
    }
}
