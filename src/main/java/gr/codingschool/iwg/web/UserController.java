package gr.codingschool.iwg.web;

import gr.codingschool.iwg.model.Game;
import gr.codingschool.iwg.model.User;
import gr.codingschool.iwg.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private GameService gameService;

    @RequestMapping(value = {"/user","/user/home"}, method = RequestMethod.GET)
    public ModelAndView home(Pageable pageable, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        User loggedInUser = (User) session.getAttribute("user");

        Page<Game> gamePage = gameService.findAllGames(pageable);
        PageWrapper<Game> page = new PageWrapper<Game>(gamePage, "/user/home");

        modelAndView.addObject("list", page.getContent());
        modelAndView.addObject("page", page);
        modelAndView.addObject("user", loggedInUser);
        modelAndView.setViewName("user/index");
        return modelAndView;
    }
}
