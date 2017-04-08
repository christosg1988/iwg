package gr.codingschool.iwg.web;

import gr.codingschool.iwg.model.User;
import gr.codingschool.iwg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public ModelAndView home(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        User loggedInUser = (User) session.getAttribute("user");
        if(loggedInUser != null) {
            modelAndView.addObject("user", loggedInUser);
            if(loggedInUser.hasRole("ROLE_USER"))
                modelAndView.setViewName("redirect:/user/home");
            else if(loggedInUser.hasRole("ROLE_ADMIN"))
                modelAndView.setViewName("redirect:/admin/home");
            return modelAndView;
        }
        else{
            modelAndView.setViewName("index");
            return modelAndView;
        }
    }
}