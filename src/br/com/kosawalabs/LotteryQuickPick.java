package br.com.kosawalabs;

import org.uncommons.maths.number.NumberGenerator;
import org.uncommons.maths.random.DiscreteUniformGenerator;
import org.uncommons.maths.random.MersenneTwisterRNG;

import java.util.*;

/**
 * Created by bkosawa on 10/11/15.
 */
public class LotteryQuickPick {

    private static final int MAX_ITERATION = 1000000;

    private int minNumber = 1;
    private int maxNumber = 60;
    private int numberPerGame = 6;

    public LotteryQuickPick() {
    }

    public LotteryQuickPick(int minNumber, int maxNumber, int numberPerGame) {
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
        this.numberPerGame = numberPerGame;
    }

    public Set<Game> run(int numOfGames){
        Set<Game> games = new HashSet<Game>();

        Random rnd;
//        rnd = new Random(System.currentTimeMillis());
        rnd = new MersenneTwisterRNG();
//        rnd = new CellularAutomatonRNG();
//        rnd = new AESCounterRNG();
//        rnd = new JavaRNG();

        NumberGenerator<Integer> dug = new DiscreteUniformGenerator(minNumber, maxNumber, rnd);
        int iterations = 0;
        int generated = 0;
        int discarded = 0;

        while (games.size() < numOfGames || MAX_ITERATION < iterations) {
            Set<Integer> numbers = new HashSet<Integer>();
            while(numbers.size() < numberPerGame){
                numbers.add(dug.nextValue());
            }

            Integer[] numbersArray = asSortedList(numbers).toArray(new Integer[numberPerGame]);


            Game.Builder builder = new Game.Builder();
            Game game = builder
                        .setMinSize(numberPerGame)
                        .setNumberList(numbersArray)
                        .build();

            boolean hasSame = false;
            for(Game g : games ) {
                if( g.equals(game) ){
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
