package gr.codingschool.iwg.service;

import gr.codingschool.iwg.model.Game;
import gr.codingschool.iwg.repository.GameTriesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by christos_georgiadis on 10/04/2017.
 */
public interface GameService{
    Page<Game> findAllGames(Pageable pageable);
    Game findGameById(int id);
    Game saveGame(Game game);
    int deleteGameById(int id);
}
