package gr.codingschool.iwg.web;

import com.sun.net.httpserver.Authenticator;
import gr.codingschool.iwg.model.*;
import gr.codingschool.iwg.service.EventService;
import gr.codingschool.iwg.service.GameService;
import gr.codingschool.iwg.service.SecurityService;
import gr.codingschool.iwg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GamesController {
    private final static String[] SORT_OPTIONS = {"Name", "Odds", "Price", "Prize"};

    @Autowired
    private GameService gameService;

    @RequestMapping(value = {"/", "/games"}, method = RequestMethod.GET)
    public ModelAndView home(@SortDefault(value = "name") Pageable pageable, HttpSession session) {
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
            Page<Game> gamePage = gameService.findAllGames(pageable);
            PageWrapper<Game> page = new PageWrapper<Game>(gamePage, "/games");
            LoginForm loginForm = new LoginForm();

            modelAndView.addObject("selected", findSelectedOption(pageable));
            modelAndView.addObject("sortedOptions", createSortedOptions());
            modelAndView.addObject("loginForm", loginForm);
            modelAndView.addObject("list", page.getContent());
            modelAndView.addObject("page", page);
            modelAndView.setViewName("games");
            return modelAndView;
        }
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
