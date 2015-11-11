package br.com.kosawalabs;


import java.util.*;

/**
 * Created by bruno.costa on 18/12/14.
 */
public class Main {

    private static final int MAX_GAMES = 40;

    public static void main(String [ ] args)
    {
        int numOfGames = MAX_GAMES;
        int minNumber = 1;
        int maxNumber = 60;
        int numberPerGame = 6;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-g":
                    if(hasNextArgs(args, i)) {
                        try {
                            numOfGames = Integer.valueOf(args[i + 1]);
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
                            minNumber = Integer.valueOf(args[i + 1]);
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
                            maxNumber = Integer.valueOf(args[i + 1]);
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
                            numberPerGame = Integer.valueOf(args[i + 1]);
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
                LotteryQuickPick(minNumber, maxNumber, numberPerGame);
//            LotteryQuickPick();

        Set<Game> games = quickPick.run(numOfGames);

        for(Game g:games){
            System.out.println(g);
        }

    }

    private static boolean hasNextArgs(String[] args, int i) {
        return i + 1 < args.length;
    }

}
