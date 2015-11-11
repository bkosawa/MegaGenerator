package br.com.kosawalabs;

import org.uncommons.maths.number.NumberGenerator;
import org.uncommons.maths.random.DiscreteUniformGenerator;
import org.uncommons.maths.random.MersenneTwisterRNG;

import java.util.*;

/**
 * Created by bkosawa on 10/11/15.
 */
public class LotteryQuickPick {

    /** C(60,6) = n!/((n-p)!*p!)
     * C(60,6) = 60!/((60-6)!*6!) = 50063860 **/
    private static final int MAX_ITERATION = 50063860;

    private int mMinNumber = 1;
    private int mMaxNumber = 60;
    private int mNumberPerGame = 6;

    public LotteryQuickPick() {
    }

    public LotteryQuickPick(int minNumber, int maxNumber, int numberPerGame) {
        this.mMinNumber = minNumber;
        this.mMaxNumber = maxNumber;
        this.mNumberPerGame = numberPerGame;
    }

    public Set<Game> run(int numOfGames){
        return run(numOfGames, this.mNumberPerGame);
    }

    public Set<Game> run(int numOfGames, int numberPerGame){
        return run(new LotteryOption(numOfGames, numberPerGame));
    }

    public Set<Game> run(LotteryOption... options){
        Set<Game> games = new HashSet<Game>();

        Random rnd;
//        rnd = new Random(System.currentTimeMillis());
        rnd = new MersenneTwisterRNG();
//        rnd = new CellularAutomatonRNG();
//        rnd = new AESCounterRNG();
//        rnd = new JavaRNG();

        NumberGenerator<Integer> dug = new DiscreteUniformGenerator(mMinNumber, mMaxNumber, rnd);
        int iterations = 0;
        int generated = 0;
        int discarded = 0;
        int diffGamesCount = 0;

        while (diffGamesCount < options.length ) {

            int gamesCount = 0;
            while (gamesCount < options[diffGamesCount].getNumOfGames() || MAX_ITERATION < iterations) {
                Set<Integer> numbers = new HashSet<Integer>();
                while (numbers.size() < options[diffGamesCount].getNumberPerGame()) {
                    numbers.add(dug.nextValue());
                }

                Integer[] numbersArray = asSortedList(numbers).toArray(new Integer[options[diffGamesCount].getNumberPerGame()]);


                Game.Builder builder = new Game.Builder();
                Game game = builder
                        .setMinSize(options[diffGamesCount].getNumberPerGame())
                        .setNumberList(numbersArray)
                        .build();

                boolean hasSame = false;
                for (Game g : games) {
                    if (g.equals(game)) {
                        hasSame = true;
                    }
                }

                if (game != null && !hasSame) {
                    games.add(game);
                    generated++;
                } else {
                    discarded++;
                }

                iterations++;
                gamesCount++;
            }

            diffGamesCount++;
        }

        System.out.println("Generated: " + generated + " - Discarded: " + discarded);
        System.out.println("Number of iterations: " + iterations);

        return games;
    }

    public static <T extends Comparable<? super T>> List<T> asSortedList(Collection<T> c) {
        List<T> list = new ArrayList<T>(c);
        java.util.Collections.sort(list);
        return list;
    }
}
