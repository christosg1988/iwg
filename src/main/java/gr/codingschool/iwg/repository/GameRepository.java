package gr.codingschool.iwg.repository;

import gr.codingschool.iwg.model.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by christos_georgiadis on 10/04/2017.
 */
@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Page<Game> findAll(Pageable pageable);
    List<Game> findAllByOrderByNameAsc();
    List<Game> findAllByOrderByNameDesc();
    List<Game> findAllByOrderByOddsAsc();
    List<Game> findAllByOrderByOddsDesc();
    List<Game> findAllByOrderByPriceAsc();
    List<Game> findAllByOrderByPriceDesc();
    List<Game> findAllByOrderByPrizeAsc();
    List<Game> findAllByOrderByPrizeDesc();
    List<Game> findAll();
    Game findById(int id);
    Game save(Game game);
    int deleteById(int id);
}
