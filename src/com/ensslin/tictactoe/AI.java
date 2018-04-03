package com.ensslin.tictactoe;

import java.util.ArrayList;
import java.util.Random;

public class AI {

    // picks a spot to play
    public int pickSpot(TicTacToe game){
        ArrayList<Integer> choices = new ArrayList();

        for (int i = 0; i < 9; i++){
            // if slots not taken, add it as the choice
            if (game.board[i] == '-') {
                choices.add(i + 1);
            }
        }

        Random rand = new Random(); // picks a random spot

        int choice = choices.get(Math.abs(rand.nextInt() % choices.size()));

        return choice;
    }
}
