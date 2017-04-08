/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.codingschool.iwg.web;

import gr.codingschool.iwg.model.LoginForm;
import gr.codingschool.iwg.model.Role;
import gr.codingschool.iwg.model.User;
import gr.codingschool.iwg.repository.RoleRepository;
import gr.codingschool.iwg.service.SecurityService;
import gr.codingschool.iwg.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
    private RoleRepository roleRepository;

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public ModelAndView login(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        if(session.getAttribute("user") != null){
            modelAndView.setViewName("redirect:/home");
        }
        else {
            LoginForm loginForm = new LoginForm();
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
            modelAndView.addObject("successMessage", "Login unsuccessfull");
            modelAndView.setViewName("login");
            return modelAndView;
        }

        securityService.authenticateUser(existingUser);

        modelAndView.addObject("user",existingUser);
        session.setAttribute("user", existingUser);
        modelAndView.setViewName("redirect:/home");
        return modelAndView;
    }
}
