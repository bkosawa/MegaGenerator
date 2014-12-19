import org.uncommons.maths.number.NumberGenerator;
import org.uncommons.maths.random.*;


import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by bruno.costa on 18/12/14.
 */
public class Main {

    private static final int MAX_GAMES = 100;

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
            int[] numbers = new int[Game.MAX_NUM];
            for(int i = 0; i < numbers.length; i++){
                numbers[i] = dug.nextValue();
            }
            Game game = Game.getInstance(numbers);
            if(game != null) {
                games.add(game);
            }
        }

        for(Game g:games){
            System.out.println(g);
        }

    }


}
