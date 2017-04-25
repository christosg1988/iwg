package gr.codingschool.iwg.web.user;

import gr.codingschool.iwg.model.Game;
import gr.codingschool.iwg.model.SortedOption;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserGamesController {
    private final static String[] SORT_OPTIONS = {"Name", "Odds", "Price", "Prize"};

    @Autowired
    private GameService gameService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/user", "/user/games"}, method = RequestMethod.GET)
    public ModelAndView home(@SortDefault(value = "name") Pageable pageable, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        User loggedInUser = (User) session.getAttribute("user");
        int unreadNotifications = notificationService.findUnreadNotificationsByUser(loggedInUser).size();
        User user = userService.findByUsername(loggedInUser.getUsername());

        modelAndView.addObject("unreadNotifications", unreadNotifications);
        modelAndView.addObject("user", user);
        modelAndView.addObject("wallet", user.getWallet());

        Page<Game> gamePage = gameService.findAllGames(pageable);
        PageWrapper<Game> page = new PageWrapper<Game>(gamePage, "/user/games");

        modelAndView.addObject("selected", findSelectedOption(pageable));
        modelAndView.addObject("sortedOptions", createSortedOptions());
        modelAndView.addObject("list", page.getContent());
        modelAndView.addObject("page", page);
        modelAndView.setViewName("user/games");

        return modelAndView;
    }

    private String findSelectedOption(Pageable pageable){
        String[] parts = pageable.getSort().toString().split(":");      //get the first part of Sort object (ex. Name: Asc)

        return parts[0];
    }

    @SuppressWarnings("Duplicates")
    private List<SortedOption> createSortedOptions(){
        List<SortedOption> allSortedOptions = new ArrayList<>();

        for(String option : SORT_OPTIONS){
            SortedOption sortedOption = new SortedOption();
            sortedOption.setValue(option.toLowerCase());
            sortedOption.setText(option);

            allSortedOptions.add(sortedOption);
        }

        return allSortedOptions;
    }

}
