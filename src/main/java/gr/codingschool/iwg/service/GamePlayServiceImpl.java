package gr.codingschool.iwg.service;

import gr.codingschool.iwg.model.Game;
import gr.codingschool.iwg.model.GamePlay;
import gr.codingschool.iwg.model.User;
import gr.codingschool.iwg.repository.GamePlayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GamePlayServiceImpl implements GamePlayService {
    @Autowired
    private GamePlayRepository gamePlayRepository;

    @Override
    public List<GamePlay> findByUserAndGame(User user, Game game) {
        return gamePlayRepository.findByUserAndGame(user,game);
    }

    @Override
    public List<GamePlay> findRecentlyPlayedByUser(User user) {
        return gamePlayRepository.findTop4ByUserOrderByDate(user);
    }

    @Override
    public GamePlay save(User user, Game game, Boolean outcome) {
        GamePlay gamePlay = new GamePlay();
        gamePlay.setUser(user);
        gamePlay.setGame(game);
        gamePlay.setOutcome(outcome);
        return gamePlayRepository.save(gamePlay);
    }
}
