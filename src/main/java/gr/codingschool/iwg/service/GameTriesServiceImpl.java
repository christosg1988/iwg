package gr.codingschool.iwg.service;

import gr.codingschool.iwg.model.GameTries;
import gr.codingschool.iwg.repository.GameTriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by christos_georgiadis on 24/04/2017.
 */
@Service("gameTriesService")
public class GameTriesServiceImpl implements GameTriesService {
    @Autowired
    private GameTriesRepository gameTriesRepository;

    @Override
    public GameTries findTriesByUserIdAndGameId(int userId, int gameId) {
        return gameTriesRepository.findTriesByUserIdAndGameId(userId, gameId);
    }

    @Override
    public GameTries save(GameTries gameTries) {
        return gameTriesRepository.save(gameTries);
    }
}
