import java.util.HashSet;
import java.util.Set;

/**
 * Created by bruno.costa on 18/12/14.
 */
public class Main {

    private static final int MAX_GAMES = 100;

    public static void main(String [ ] args)
    {

        Set<Game> games = new HashSet<Game>();
        while (games.size() < MAX_GAMES) {
            int[] numbers = new int[Game.MAX_NUM];
            for(int i = 0; i < numbers.length; i++){
                numbers[i] = (int)((60.0 * Math.random()) + 1);
//                numbers[i] = (int)((60.0 * ) + 1);
            }
            games.add(new Game(numbers));
        }

        for(Game g:games){
            System.out.println(g);
        }

    }


}
