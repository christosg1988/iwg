package gr.codingschool.iwg.repository;

import gr.codingschool.iwg.model.Game;
import gr.codingschool.iwg.model.GamePlay;
import gr.codingschool.iwg.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GamePlayRepository extends JpaRepository<GamePlay, Long> {
    List<GamePlay> findByUserAndGame(User user, Game game);
    List<GamePlay> findTop4ByUserOrderByDate(User user);
    GamePlay save(GamePlay gamePlay);
}
