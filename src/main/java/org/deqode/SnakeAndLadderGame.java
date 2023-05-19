package org.deqode;

import java.util.*;
public class SnakeAndLadderGame {
    public static final int BOARD_SIZE = 100;
    public static final int WINNING_POSITION = 100;
    public static final int[] SNAKES = {16, 47, 49, 56, 62, 64, 87, 93, 95, 98};
    public static final int[] LADDERS = {1, 4, 9, 21, 28, 36, 51, 71, 80};

    public static Map<Integer, Integer> snakePositions;
    public static Map<Integer, Integer> ladderPositions;

    public static class Player {
        private String name;
        private int position;

        public Player(String name) {
            this.name = name;
            this.position = 0;
        }

        public String getName() {
            return name;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }
    }

    public static class Dice {
        public int roll() {
            Random random = new Random();
            return random.nextInt(6) + 1;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of players: ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine();

        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Enter the name of player " + (i + 1) + ": ");
            String playerName = scanner.nextLine();
            players.add(new Player(playerName));
        }

        snakePositions = new HashMap<>();
        ladderPositions = new HashMap<>();

        for (int i = 0; i < SNAKES.length; i++) {
            snakePositions.put(SNAKES[i], SNAKES[i] - 10);
        }

        for (int i = 0; i < LADDERS.length; i++) {
            ladderPositions.put(LADDERS[i], LADDERS[i] + 10);
        }

        Dice dice = new Dice();

        boolean gameWon = false;
        int currentPlayerIndex = 0;

        while (!gameWon) {
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println("\n" + currentPlayer.getName() + "'s turn.");
            System.out.println("Current position: " + currentPlayer.getPosition());

            System.out.print("Press enter to roll the dice...");
            scanner.nextLine();

            int diceRoll = dice.roll();
            System.out.println("Dice roll: " + diceRoll);

            int newPosition = currentPlayer.getPosition() + diceRoll;
            if (newPosition <= WINNING_POSITION) {
                currentPlayer.setPosition(newPosition);
                System.out.println("Moved to position " + newPosition);

                if (snakePositions.containsKey(newPosition)) {
                    int snakeTail = snakePositions.get(newPosition);
                    currentPlayer.setPosition(snakeTail);
                    System.out.println("Oops! Swallowed by a snake. Moved down to position " + snakeTail);
                }

                if (ladderPositions.containsKey(newPosition)) {
                    int ladderTop = ladderPositions.get(newPosition);
                    currentPlayer.setPosition(ladderTop);
                    System.out.println("Yay! Climbed a ladder. Moved up to position " + ladderTop);
                }

                if (currentPlayer.getPosition() == WINNING_POSITION) {
                    System.out.println("\n" + currentPlayer.getName() + " wins!");
                    gameWon = true;
                }
            }

            currentPlayerIndex = (currentPlayerIndex + 1) % numPlayers;
        }

        scanner.close();
    }
}
