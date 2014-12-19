import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;

/**
 * Created by bruno.costa on 18/12/14.
 */
public class Game {
    public static final int MAX_NUM = 6;

    int[] numbers = new int[MAX_NUM];

    public static Game getInstance(int[] numbers){
        Game game = isNumbersUnique(numbers)? new Game(numbers): null;
        return game;
    }

    private static boolean isNumbersUnique(int[] numbers) {
        for(int i=0;i < numbers.length;i++){
            for(int j=0;j < numbers.length-i;j++){
                if(j!=i
                    && numbers[i] == numbers[j]){
                    return false;
                }
            }
        }
        return true;
    }

    private Game(int[] numbers) {
        if(numbers.length != 6){
            throw new RuntimeException("Nao pode ter menos de 6");
        }
        this.numbers = numbers;
    }

    @Override
    public boolean equals(Object obj) {
        Game other;
        other = obj instanceof Game? (Game) obj: null;
        if(other == null)
            return false;
        int[] otherArray = other.getNumbers();
        if (otherArray.length != numbers.length)
            return false;

        int counter = 0;
        for(int i = 0 ; i < numbers.length;i++){
            boolean find = false;
            for(int j = 0; j < otherArray.length; j++) {
                if (numbers[i] == otherArray[j]) {
                    find = true;
                    break;
                }
            }

            if(find) {
                counter++;
            }
        }

        if(counter >= 4){
            return true;
        }
        else{
            return false;
        }
    }

    public int[] getNumbers() {
        return numbers;
    }

    public void setNumbers(final int[] numbers) {
        if(numbers.length < MAX_NUM){
            throw new RuntimeException("Nao pode ter menos de 6");
        }
        this.numbers = numbers;
    }

    @Override
    public String toString() {
        StringBuilder  builder = new StringBuilder();
        NumberFormat formatter = new DecimalFormat("00");

        for(int i =0; i < numbers.length - 1; i++){
            builder.append(formatter.format(numbers[i])).append("-");
        }
        builder.append(formatter.format(numbers[numbers.length -1]));

        return builder.toString();
    }
}
