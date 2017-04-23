package gr.codingschool.iwg.web;

import com.sun.net.httpserver.Authenticator;
import gr.codingschool.iwg.model.Event;
import gr.codingschool.iwg.model.LoginForm;
import gr.codingschool.iwg.model.User;
import gr.codingschool.iwg.service.EventService;
import gr.codingschool.iwg.service.SecurityService;
import gr.codingschool.iwg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by christos_georgiadis on 23/04/2017.
 */
@RestController
public class AjaxController {
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private EventService eventService;

    @SuppressWarnings("Duplicates")
    @RequestMapping(value = {"/modalLogin"})
    public ResponseEntity getSearchResultViaAjax(@RequestBody LoginForm loginForm, HttpSession session) {

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

            return new ResponseEntity<Error>(HttpStatus.UNAUTHORIZED);
        }

        securityService.authenticateUser(existingUser);

        Event loginEvent = new Event();
        loginEvent.setUser(existingUser);
        loginEvent.setType("Login");
        loginEvent.setInformation("The user logged in");
        eventService.save(loginEvent);

        session.setAttribute("user", existingUser);

        return new ResponseEntity<Authenticator.Success>(HttpStatus.OK);
    }
}
