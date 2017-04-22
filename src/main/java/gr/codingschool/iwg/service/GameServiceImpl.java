package gr.codingschool.iwg.service;

import gr.codingschool.iwg.model.Game;
import gr.codingschool.iwg.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by christos_georgiadis on 10/04/2017.
 */
@Service("gameService")
public class GameServiceImpl implements GameService {
    @Autowired
    private GameRepository gameRepository;

    @Override
    public List<Game> findAllGames(){
        return gameRepository.findAll();
    }

    @Override
    public Game findGameById(int id){
        return gameRepository.findById(id);
    }

    @Override
    @Transactional
    public Game saveGame(Game game){
        return gameRepository.save(game);
    }

    @Override
    @Transactional
    public int deleteGameById(int id) {
        return gameRepository.deleteById(id);
    }
}