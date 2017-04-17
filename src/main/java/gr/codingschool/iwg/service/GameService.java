package gr.codingschool.iwg.service;

import gr.codingschool.iwg.model.Game;

import java.util.List;

/**
 * Created by christos_georgiadis on 10/04/2017.
 */
public interface GameService {
    List<Game> findAllGames();
}
