package gr.codingschool.iwg.repository.game;

import gr.codingschool.iwg.model.game.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by christos_georgiadis on 10/04/2017.
 */
@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Page<Game> findAll(Pageable pageable);
    Game findById(int id);
    Game save(Game game);
    int deleteById(int id);
}
