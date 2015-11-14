package br.com.kosawalabs;


import java.util.*;

/**
 * Created by bruno.costa on 18/12/14.
 */
public class Main {

    private static final int MAX_GAMES = 40;

    public static void main(String [ ] args)
    {

        Options opt = new Options();

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-g":
                    if(hasNextArgs(args, i)) {
                        try {
                            opt.setNumOfGames(Integer.valueOf(args[i + 1]));
                            i++;
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                            System.out.println("Not valid number of games = [" + args[i + 1] + "]");
                        }
                    }
                    break;
                case "-m":
                    if(hasNextArgs(args, i)) {
                        try {
                            opt.setMinNumber(Integer.valueOf(args[i + 1]));
                            i++;
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                            System.out.println("Not valid min number = [" + args[i + 1] + "]");
                        }
                    }
                    break;
                case "-M":
                    if(hasNextArgs(args, i)) {
                        try {
                            opt.setMaxNumber(Integer.valueOf(args[i + 1]));
                            i++;
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                            System.out.println("Not valid Max number = [" + args[i + 1] + "]");
                        }
                    }
                    break;
                case "-p":
                    if(hasNextArgs(args, i)) {
                        try {
                            opt.setNumberPerGame(Integer.valueOf(args[i + 1]));
                            i++;
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                            System.out.println("Not valid number per games = [" + args[i + 1] + "]");
                        }
                    }
                    break;
                default:
                    System.out.println("Command not valid = " + args[i]);
            }

        }

        LotteryQuickPick quickPick = new
                LotteryQuickPick(opt.getMinNumber(),
                    opt.getMaxNumber());

        Set<Game> games = quickPick.run(opt.getNumOfGames(), opt.getNumberPerGame());
//        Set<Game> games = quickPick.run(new LotteryOption(opt.getNumOfGames(), opt.getNumberPerGame()));
//        Set<Game> games = quickPick.run(
//                new LotteryOption(24, 6)
//                , new LotteryOption(4, 7)
//                , new LotteryOption(1, 8)
//        );

        for(Game g:games){
            System.out.println(g);
        }

    }

    private static class Options{
        private int numOfGames = MAX_GAMES;
        private int minNumber = 1;
        private int maxNumber = 60;
        private int numberPerGame = 6;

        public Options() {
            numOfGames = MAX_GAMES;
            minNumber = 1;
            maxNumber = 60;
            numberPerGame = 6;
        }

        public int getNumOfGames() {
            return numOfGames;
        }

        public void setNumOfGames(int numOfGames) {
            this.numOfGames = numOfGames;
        }

        public int getMinNumber() {
            return minNumber;
        }

        public void setMinNumber(int minNumber) {
            this.minNumber = minNumber;
        }

        public int getMaxNumber() {
            return maxNumber;
        }

        public void setMaxNumber(int maxNumber) {
            this.maxNumber = maxNumber;
        }

        public int getNumberPerGame() {
            return numberPerGame;
        }

        public void setNumberPerGame(int numberPerGame) {
            this.numberPerGame = numberPerGame;
        }
    }

    private static boolean hasNextArgs(String[] args, int i) {
        return i + 1 < args.length;
    }

}
