package gr.codingschool.iwg.service;

import gr.codingschool.iwg.model.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Created by christos_georgiadis on 10/04/2017.
 */
public interface GameService {
    Page<Game> findAllGames(Pageable pageable);
    List<Game> findAllGamesByOrderByNameAsc();
    List<Game> findAllGamesByOrderByNameDesc();
    List<Game> findAllGamesByOrderByOddsAsc();
    List<Game> findAllGamesByOrderByOddsDesc();
    List<Game> findAllGamesByOrderByPriceAsc();
    List<Game> findAllGamesByOrderByPriceDesc();
    List<Game> findAllGamesByOrderByPrizeAsc();
    List<Game> findAllGamesByOrderByPrizeDesc();
}
