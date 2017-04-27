package gr.codingschool.iwg.model;

/**
 * Created by christos_georgiadis on 26/04/2017.
 */
public class GameResult {
    private boolean result;
    private int balance;
    private boolean enoughBalance;

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public boolean getEnoughBalance() {
        return enoughBalance;
    }

    public void setEnoughBalance(boolean enoughBalance) {
        this.enoughBalance = enoughBalance;
    }
}
