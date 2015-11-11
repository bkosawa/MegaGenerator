package br.com.kosawalabs;

/**
 * Created by brunocosta on 11/11/15.
 */
public class LotteryOption {

    private int numOfGames = 1;
    private int numberPerGame = 6;

    public LotteryOption() {
    }

    public LotteryOption(int numOfGames, int numberPerGame) {
        this.numOfGames = numOfGames;
        this.numberPerGame = numberPerGame;
    }

    public int getNumOfGames() {
        return numOfGames;
    }

    public void setNumOfGames(int numOfGames) {
        this.numOfGames = numOfGames;
    }

    public int getNumberPerGame() {
        return numberPerGame;
    }

    public void setNumberPerGame(int numberPerGame) {
        this.numberPerGame = numberPerGame;
    }
}
