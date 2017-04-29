package gr.codingschool.iwg.web.user;

import gr.codingschool.iwg.model.Game;
import gr.codingschool.iwg.model.User;
import gr.codingschool.iwg.service.GameService;
import gr.codingschool.iwg.service.NotificationService;
import gr.codingschool.iwg.service.UserService;
import gr.codingschool.iwg.web.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by christos_georgiadis on 24/04/2017.
 */
@Controller
public class GameController {
    @Autowired
    private GameService gameService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/user/game", "/user/favourites/game"}, method = RequestMethod.GET)
    public ModelAndView home(@RequestParam("id") int id , HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();

        User loggedInUser = (User) session.getAttribute("user");
        int unreadNotifications = notificationService.findUnreadNotificationsByUser(loggedInUser).size();
        User user = userService.findByUsername(loggedInUser.getUsername());

        Game game = gameService.findGameById(id);

        modelAndView.addObject("user", user);
        modelAndView.addObject("unreadNotifications", unreadNotifications);
        modelAndView.addObject("wallet", user.getWallet());
        modelAndView.addObject("game", game);
        modelAndView.addObject("isFavourite", isFavouriteGame(user, game));

        modelAndView.setViewName("user/game");

        return modelAndView;
    }

    private boolean isFavouriteGame(User user, Game game){
        return user.getListOfFavouriteGameIds().contains(game.getId());
    }
}
