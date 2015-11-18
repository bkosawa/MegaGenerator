package br.com.kosawalabs;

import org.uncommons.maths.random.MersenneTwisterRNG;

import java.util.*;

/**
 * Created by bkosawa on 10/11/15.
 */
public class LotteryQuickPick {

    /** C(60,6) = n!/((n-p)!*p!)
     * C(60,6) = 60!/((60-6)!*6!) = 50063860 **/
    private static final int MAX_ITERATION = 50063860;

    private List<Integer> numbersSet;

    public LotteryQuickPick() {
        int minNumber = 1;
        int maxNumber = 60;
        int i = minNumber;
        numbersSet = new ArrayList<>();
        while (i <= maxNumber) {
            numbersSet.add(i);
            i++;
        }
    }

    public LotteryQuickPick(int minNumber, int maxNumber) {
        int i = minNumber;
        numbersSet = new ArrayList<>();
        while (i <= maxNumber) {
            numbersSet.add(i);
            i++;
        }
    }

    public LotteryQuickPick(List<Integer> selectedNumbers) {
        if(selectedNumbers == null || selectedNumbers.size() == 0){
            throw new IllegalArgumentException("You passed a invalid set of numbers.");
        }
        numbersSet = selectedNumbers;
    }

    public Set<Game> run(){
        return run(1, 6);
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

        int iterations = 0;
        int generated = 0;
        int discarded = 0;
        int diffGamesCount = 0;

        while (diffGamesCount < options.length ) {
            int numberOfGames = options[diffGamesCount].getNumOfGames();
            int numberPerGame = options[diffGamesCount].getNumberPerGame();
            int gamesCount = 0;
            while (gamesCount < numberOfGames || MAX_ITERATION < iterations) {
                Set<Integer> numbers = new HashSet<Integer>();
                List<Integer> bagOfNumbers = new ArrayList<>(numbersSet);
                while ((numbers.size() < numberPerGame)
                        && (bagOfNumbers.size() > 0)) {
                    numbers.add(pickNumber(bagOfNumbers, rnd));
                }

                if (numbers.size() < numberPerGame) {
                    throw new IllegalArgumentException("You have passed a invalid number per game parameter: " + numberPerGame); //TODO Criar uma exception para isso
                }

                Integer[] numbersArray = asSortedList(numbers).toArray(new Integer[numberPerGame]);


                Game.Builder builder = new Game.Builder();
                Game game = builder
                        .setMinSize(numberPerGame)
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

    private Integer pickNumber(List<Integer> numbers, Random rnd) {
        int index = rnd.nextInt(numbers.size());
        Integer pickedNumber = numbers.get(index);
        numbers.remove(index);
        return pickedNumber;
    }

    public static <T extends Comparable<? super T>> List<T> asSortedList(Collection<T> c) {
        List<T> list = new ArrayList<T>(c);
        java.util.Collections.sort(list);
        return list;
    }
}
