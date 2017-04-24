package gr.codingschool.iwg.service;

import gr.codingschool.iwg.model.GameTries;
import gr.codingschool.iwg.repository.GameTriesRepository;

/**
 * Created by christos_georgiadis on 24/04/2017.
 */
public interface GameTriesService {
    GameTries findTriesByUserIdAndGameId(int userId, int gameId);
    GameTries save(GameTries gameTries);
}
