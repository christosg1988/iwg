package gr.codingschool.iwg.web;

import gr.codingschool.iwg.model.Game;
import gr.codingschool.iwg.model.User;
import gr.codingschool.iwg.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private GameService gameService;

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public ModelAndView home(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        User loggedInUser = (User) session.getAttribute("user");
        if(loggedInUser != null) {
            modelAndView.addObject("user", loggedInUser);
            if(loggedInUser.hasRole("ROLE_USER"))
                modelAndView.setViewName("redirect:/user");
            else if(loggedInUser.hasRole("ROLE_ADMIN"))
                modelAndView.setViewName("redirect:/admin");
            return modelAndView;
        }
        else{
            List<Game> listOfGames = gameService.findAllGames();

            modelAndView.addObject("list", listOfGames);
            modelAndView.setViewName("index");
            return modelAndView;
        }
    }
}
