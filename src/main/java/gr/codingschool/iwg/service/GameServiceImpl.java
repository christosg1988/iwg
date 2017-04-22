package gr.codingschool.iwg.service;

import gr.codingschool.iwg.model.Game;
import gr.codingschool.iwg.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Page<Game> findAllGames(Pageable pageable){
        return gameRepository.findAll(pageable);
    }

    @Override
    public List<Game> findAllGamesByOrderByOddsAsc(){
        List<Game> listOfGames = new ArrayList<>(gameRepository.findAllByOrderByOddsAsc());

        return listOfGames;
    }

    @Override
    public List<Game> findAllGamesByOrderByOddsDesc(){
        List<Game> listOfGames = new ArrayList<>(gameRepository.findAllByOrderByOddsDesc());

        return listOfGames;
    }

    @Override
    public List<Game> findAllGamesByOrderByPrizeAsc(){
        List<Game> listOfGames = new ArrayList<>(gameRepository.findAllByOrderByPrizeAsc());

        return listOfGames;
    }

    @Override
    public List<Game> findAllGamesByOrderByPrizeDesc(){
        List<Game> listOfGames = new ArrayList<>(gameRepository.findAllByOrderByPrizeDesc());

        return listOfGames;
    }

    @Override
    public List<Game> findAllGamesByOrderByNameAsc(){
        List<Game> listOfGames = new ArrayList<>(gameRepository.findAllByOrderByNameAsc());

        return listOfGames;
    }

    @Override
    public List<Game> findAllGamesByOrderByNameDesc(){
        List<Game> listOfGames = new ArrayList<>(gameRepository.findAllByOrderByNameDesc());

        return listOfGames;
    }

    @Override
    public List<Game> findAllGamesByOrderByPriceAsc(){
        List<Game> listOfGames = new ArrayList<>(gameRepository.findAllByOrderByPriceAsc());

        return listOfGames;
    }

    @Override
    public List<Game> findAllGamesByOrderByPriceDesc() {
        List<Game> listOfGames = new ArrayList<>(gameRepository.findAllByOrderByPriceDesc());

        return listOfGames;
    }

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
