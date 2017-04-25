package gr.codingschool.iwg.web;

import com.sun.net.httpserver.Authenticator;
import gr.codingschool.iwg.model.*;
import gr.codingschool.iwg.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Random;

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
    @Autowired
    private GameTriesService gameTriesService;
    @Autowired
    private GameService gameService;
    @Autowired
    private UserWalletService userWalletService;

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

    @RequestMapping(value = {"/user/try"}, produces = "application/json")
    public @ResponseBody int getGameTriesPerUserViaAjax(@RequestParam("gameId") int gameId, HttpSession session){
        User loggedInUser = (User) session.getAttribute("user");

        GameTries foundGameTries = gameTriesService.findTriesByUserIdAndGameId(loggedInUser.getId(), gameId);

        if(foundGameTries != null){

            foundGameTries.setTries(foundGameTries.getTries() - 1);

            if(foundGameTries.getTries() == 0){
                return 0;
            }
            else{
                GameTries savedGameTries = gameTriesService.save(foundGameTries);
                Game game = gameService.findGameById(gameId);

                return findRandomOdds(game.getOdds());
            }
        }
        else {
            Game game = gameService.findGameById(gameId);

            GameTries gameTries = new GameTries();
            gameTries.setGame(game);
            gameTries.setUser(loggedInUser);
            gameTries.setTries(3);

            GameTries savedGameTries = gameTriesService.save(gameTries);

            return findRandomOdds(game.getOdds());
        }

    }

    @RequestMapping(value = {"/user/play"})
    public ResponseEntity<GameResult> playGameViaAjax(@RequestParam("gameId") int gameId, HttpSession session){
        User loggedInUser = (User) session.getAttribute("user");
        Game game = gameService.findGameById(gameId);

        int result = findRandomOdds(game.getOdds());

        GameResult gameResult = new GameResult();
        gameResult.setFlag(result);
        gameResult.setBalance(calculateAndUpdateBalance(result, loggedInUser, game));

        return new ResponseEntity<GameResult>(gameResult, HttpStatus.OK);
    }

    private int findRandomOdds(int odds){
        Random myRandom = new Random( System.currentTimeMillis() );
        int randomInt = myRandom.nextInt(100) + 1;

        System.out.println(randomInt);

        if(randomInt <= odds){
            return 1;
        }
        else{
            return -1;
        }
    }

    private int calculateAndUpdateBalance(int result, User user, Game game){
        int newBalance;

        if(result == 1){
            newBalance = user.getWallet().getBalance() + (game.getPrize() - game.getPrice());
        }
        else{
            newBalance = user.getWallet().getBalance() - game.getPrice();
        }

        UserWallet userWallet = user.getWallet();
        userWallet.setBalance(newBalance);

        userWalletService.save(userWallet);

        return userWallet.getBalance();
    }
}
