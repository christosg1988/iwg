package gr.codingschool.iwg.web.admin;

import gr.codingschool.iwg.model.Game;
import gr.codingschool.iwg.model.User;
import gr.codingschool.iwg.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class GamesController {
    @Autowired
    private GameService gameService;

    @RequestMapping(value = {"/admin/games"}, method = RequestMethod.GET)
    public ModelAndView adminGames(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        User loggedInUser = (User) session.getAttribute("user");
        List<Game> games = gameService.findAllGames();
        modelAndView.addObject("user", loggedInUser);
        modelAndView.addObject("list", games);
        modelAndView.setViewName("admin/games");
        return modelAndView;
    }

    @RequestMapping(value = {"/admin/games/edit"}, method = RequestMethod.GET)
    public ModelAndView adminEditGame(@RequestParam("id") int id, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        User loggedInUser = (User) session.getAttribute("user");
        Game game = gameService.findGameById(id);
        modelAndView.addObject("user", loggedInUser);
        modelAndView.addObject("game", game);
        modelAndView.setViewName("admin/games/edit");
        return modelAndView;
    }

    @RequestMapping(value = {"/admin/games/add"}, method = RequestMethod.GET)
    public ModelAndView adminAddGame(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        User loggedInUser = (User) session.getAttribute("user");
        Game game = new Game();
        modelAndView.addObject("user", loggedInUser);
        modelAndView.addObject("game", game);
        modelAndView.setViewName("admin/games/add");
        return modelAndView;
    }

    @RequestMapping(value = {"/admin/games/add"}, method = RequestMethod.POST)
    public ModelAndView adminAddGame(Game game, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        User loggedInUser = (User) session.getAttribute("user");
        Game savedGame = gameService.saveGame(game);
        modelAndView.addObject("user", loggedInUser);
        modelAndView.addObject("game", game);
        modelAndView.setViewName("admin/games/add");
        return modelAndView;
    }

    @RequestMapping(value = {"/admin/games/edit"}, method = RequestMethod.POST)
    public ModelAndView adminSaveGame(Game game, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        User loggedInUser = (User) session.getAttribute("user");
        gameService.saveGame(game);
        modelAndView.addObject("user", loggedInUser);
        modelAndView.addObject("game", game);
        modelAndView.setViewName("admin/games/edit");
        return modelAndView;
    }

    @RequestMapping(value = {"/admin/games/delete"}, method = RequestMethod.GET)
    public ModelAndView adminDeleteGame(@RequestParam("id") int id, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        User loggedInUser = (User) session.getAttribute("user");
        Game game = gameService.findGameById(id);
        gameService.deleteGameById(id);
        modelAndView.addObject("user", loggedInUser);
        modelAndView.addObject("game", game);
        modelAndView.setViewName("admin/games/deleted");
        return modelAndView;
    }
}
