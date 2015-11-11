package br.com.kosawalabs;

import org.uncommons.maths.number.NumberGenerator;
import org.uncommons.maths.random.DiscreteUniformGenerator;
import org.uncommons.maths.random.MersenneTwisterRNG;

import java.util.*;

/**
 * Created by bkosawa on 10/11/15.
 */
public class LotteryQuickPick {

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
        while (games.size() < numOfGames) {
            //int[] numbers = new int[br.com.kosawalabs.Game.MAX_NUM];
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
            if(game != null) {
                games.add(game);
            }
        }

        return games;
    }

    public static <T extends Comparable<? super T>> List<T> asSortedList(Collection<T> c) {
        List<T> list = new ArrayList<T>(c);
        java.util.Collections.sort(list);
        return list;
    }
}
