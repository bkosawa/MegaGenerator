package br.com.kosawalabs;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by bruno.costa on 18/12/14.
 */
public class Game {

    Integer[] numbers;

    public static Game getInstance(Integer[] numbers){
        Game game = isNumbersUnique(numbers)? new Game(numbers): null;
        return game;
    }

    private static boolean isNumbersUnique(Integer[] numbers) {
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

    private Game(Integer[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean equals(Object obj) {
        Game other;
        other = obj instanceof Game? (Game) obj: null;
        if(other == null)
            return false;
        Integer[] otherArray = other.getNumbers();
        if (otherArray.length != numbers.length)
            return false;

        int counter = 0;
        for(int i = 0 ; i < numbers.length;i++){
            boolean find = false;
            for(int j = 0; j < otherArray.length; j++) {
                if (numbers[i].equals(otherArray[j])) {
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

    public Integer[] getNumbers() {
        return numbers;
    }

    @Override
    public String toString() {
        StringBuilder  builder = new StringBuilder();
        NumberFormat formatter = new DecimalFormat("00");

        for(int i =0; i < numbers.length - 1; i++){
            builder.append(formatter.format(numbers[i])).append("\t");
        }
        builder.append(formatter.format(numbers[numbers.length -1]));

        return builder.toString();
    }

    public static class Builder{

        private int minSize;

        private Integer[] numbers;

        public Builder() {
        }

        public Builder setMinSize(int minSize){
            this.minSize = minSize;
            return this;
        }

        public Builder setNumberList(Integer[] numbers){
            this.numbers = numbers;
            return this;
        }

        public Game build() {
            if(numbers == null) {
                throw new RuntimeException("Number list is not set!");
            }

            if(numbers.length < minSize){
                throw new RuntimeException("Number list cannot have less than " + minSize);
            }

            return isNumbersUnique(numbers)? new Game(numbers): null;
        }
    }
}
