package gr.codingschool.iwg.model;

import javax.persistence.*;

/**
 * Created by christos_georgiadis on 24/04/2017.
 */
@Entity
@Table(name = "games_tries")
public class GameTries {

    @Id
    @SequenceGenerator(name = "game_tries_id_seq", sequenceName = "game_tries_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_tries_id_seq")
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "userId")
    private int userId;

    @Column(name = "gameId")
    private int gameId;

    @Column(name = "tries")
    private int tries;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getTries() {
        return tries;
    }

    public void setTries(int tries) {
        this.tries = tries;
    }
}
