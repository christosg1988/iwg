package gr.codingschool.iwg.service;

import gr.codingschool.iwg.model.Game;
import gr.codingschool.iwg.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<Game> listOfGames = new ArrayList<>(gameRepository.findAll());

        return listOfGames;
    }
}
