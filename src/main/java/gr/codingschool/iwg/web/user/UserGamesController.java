package gr.codingschool.iwg.web.user;

import gr.codingschool.iwg.model.Game;
import gr.codingschool.iwg.model.User;
import gr.codingschool.iwg.service.GameService;
import gr.codingschool.iwg.web.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserGamesController {
    @Autowired
    private GameService gameService;

    @RequestMapping(value = {"/user", "/user/games"}, method = RequestMethod.GET)
    public ModelAndView home(Pageable pageable, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        User loggedInUser = (User) session.getAttribute("user");
        modelAndView.addObject("user", loggedInUser);

        Page<Game> gamePage = gameService.findAllGames(pageable);
        PageWrapper<Game> page = new PageWrapper<Game>(gamePage, "/user/games");

        modelAndView.addObject("list", page.getContent());
        modelAndView.addObject("page", page);
        modelAndView.setViewName("user/games");

        return modelAndView;
    }
}
