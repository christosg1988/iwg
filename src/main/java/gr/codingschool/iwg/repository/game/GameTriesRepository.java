package gr.codingschool.iwg.repository.game;

import gr.codingschool.iwg.model.game.GameTries;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by christos_georgiadis on 24/04/2017.
 */
public interface GameTriesRepository extends JpaRepository<GameTries, Long> {
    GameTries findTriesByUserIdAndGameId(int userId, int gameId);
    GameTries save(GameTries gameTries);
}
