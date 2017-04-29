package gr.codingschool.iwg.service;

import gr.codingschool.iwg.model.Game;
import gr.codingschool.iwg.model.GamePlay;
import gr.codingschool.iwg.model.User;

import java.util.List;

public interface GamePlayService {
    List<GamePlay> findByUserAndGame(User user, Game game);
    List<GamePlay> findRecentlyPlayedByUser(User user);
    GamePlay save(User user, Game game, Boolean outcome);
}
