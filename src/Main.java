import org.uncommons.maths.number.NumberGenerator;
import org.uncommons.maths.random.*;


import java.util.*;

/**
 * Created by bruno.costa on 18/12/14.
 */
public class Main {

    private static final int MAX_GAMES = 40;

    public static void main(String [ ] args)
    {
        Integer NUM_GAMES = MAX_GAMES;
        if(args.length > 0){
            try {
                NUM_GAMES = Integer.valueOf(args[0]);
            }catch (NumberFormatException e){
                e.printStackTrace();
                NUM_GAMES = MAX_GAMES;
            }

        }

        Set<Game> games = new HashSet<Game>();
        Random rnd;
//        rnd = new Random(System.currentTimeMillis());
        rnd = new MersenneTwisterRNG();
//        rnd = new CellularAutomatonRNG();
//        rnd = new AESCounterRNG();
//        rnd = new JavaRNG();

        NumberGenerator<Integer> dug = new DiscreteUniformGenerator(1,60, rnd);
        while (games.size() < NUM_GAMES) {
            //int[] numbers = new int[Game.MAX_NUM];
            Set<Integer> numbers = new HashSet<Integer>();
            while(numbers.size() < Game.MAX_NUM){
                numbers.add(dug.nextValue());
            }

            Integer[] numbersArray = asSortedList(numbers).toArray(new Integer[6]);
            Game game = Game.getInstance(numbersArray);
            if(game != null) {
                games.add(game);
            }
        }

        for(Game g:games){
            System.out.println(g);
        }

    }

    public static
    <T extends Comparable<? super T>> List<T> asSortedList(Collection<T> c) {
        List<T> list = new ArrayList<T>(c);
        java.util.Collections.sort(list);
        return list;
    }



}
