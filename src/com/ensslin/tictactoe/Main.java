package com.ensslin.tictactoe;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // get input
        Scanner sc = new Scanner(System.in);
        boolean doYouWantToPlay = true;

        // while player wants to play
        while (doYouWantToPlay) {

            // print welcome message and assign the players tokens
            System.out.println("Welcome to Tatiana's Tic Tac Toe. Do you want to play a game?\n "
                    + "Pick a character to appear as on the board. Then pick one for me!");
            System.out.println();
            System.out.println("Enter a single character for yourself.");
            char playerToken = sc.next().charAt(0);
            System.out.println("Great. Enter a different token for myself.");
            char aiToken = sc.next().charAt(0);

            // create an instance of the tic tac toe board
            TicTacToe game = new TicTacToe(playerToken, aiToken);
            AI ai = new AI();

            // set up the game
            System.out.println();
            System.out.println("Lets start the game.... To play, enter a number for the token to placed.\n");
            System.out.println("The numbers go from 1-9 -- left to right.");
            TicTacToe.printIndexBoard();
            System.out.println();

            // play game
            while (game.gameOver().equals("notOver")) {
                if (game.currentMarker == game.userMarker) {

                    System.out.println("Your turn! Enter the spot for your next token:");
                    int spot = sc.nextInt();

                    while (!game.playTurn(spot)) {
                        System.out.println("Try again." + spot + " This spot is already taken. Enter a new spot!");

                        spot = sc.nextInt();
                    }
                    System.out.println("You picked: " + spot);
                } else { // AI turn

                    System.out.println("It's my turn. MWAHAH!");
                    int aiSpot = ai.pickSpot(game);
                    game.playTurn(aiSpot);
                    System.out.println("I picked: " + aiSpot);
                }

                // print updated board
                System.out.println();
                game.printBoard();
            }

            System.out.println(game.gameOver());
            System.out.println();

            // ask to set up a new game
            System.out.println(" Do you want to play again? Y for yes. Enter anything else otherwise...");
            char response = sc.next().charAt(0);

            // update response
            doYouWantToPlay = (response == 'Y' || response == 'y');
            System.out.println();
            System.out.println();
        }
    }
}
