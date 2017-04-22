/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.codingschool.iwg.web;

import gr.codingschool.iwg.model.Event;
import gr.codingschool.iwg.model.LoginForm;
import gr.codingschool.iwg.model.User;
import gr.codingschool.iwg.service.EventService;
import gr.codingschool.iwg.service.SecurityService;
import gr.codingschool.iwg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 *
 * @author xrist
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private EventService eventService;

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(required=false, defaultValue="false") Boolean successRegister, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        if(session.getAttribute("user") != null){
            modelAndView.setViewName("redirect:/home");
        }
        else {
            LoginForm loginForm = new LoginForm();
            if(successRegister)
                modelAndView.addObject("successRegister", "You have been successfully registered!\n" +
                        "Please login using the form below.");
            modelAndView.addObject("loginForm", loginForm);
            modelAndView.setViewName("login");
        }
        return modelAndView;
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public ModelAndView login(@Valid LoginForm loginForm, BindingResult bindingResult, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("login");
            return modelAndView;
        }

        User existingUser = userService.authenticate(loginForm.getUsername(), loginForm.getPassword());
        if(existingUser == null)
        {
            User failedUser = userService.findByUsername(loginForm.getUsername());
            if(failedUser != null) {
                Event loginEvent = new Event();
                loginEvent.setUser(failedUser);
                loginEvent.setType("Failed Login");
                loginEvent.setInformation("The user failed to login");
                eventService.save(loginEvent);
            }

            modelAndView.addObject("successMessage", "Login unsuccessfull");
            modelAndView.setViewName("login");
            return modelAndView;
        }

        securityService.authenticateUser(existingUser);

        Event loginEvent = new Event();
        loginEvent.setUser(existingUser);
        loginEvent.setType("Login");
        loginEvent.setInformation("The user logged in");
        eventService.save(loginEvent);

        session.setAttribute("user", existingUser);
        modelAndView.setViewName("redirect:/home");
        return modelAndView;
    }
}
