package com.ensslin.tictactoe;

public class TicTacToe {

    // linear array with 9 slots
    protected char[] board;

    // marker for X and O
    protected char userMarker;

    protected char aiMarker;
    protected char winner;

    // current position
    protected char currentMarker;

    // constructor
    public TicTacToe(char playerToken, char aiMarker){
        this.userMarker = playerToken;
        this.aiMarker = aiMarker;
        this.winner = '-';
        this.board = setBoard();
        this.currentMarker = this.userMarker;
    }

    // set the board to a clean slate
    public static char[] setBoard(){
        char[] board = new char[9];
        for (int i = 0; i< board.length; i++){
            board[i] = '-';
        }
        return board;
    }

    // validate spot and keep track of whos turn
    public boolean playTurn(int spot){
        // must make sure the spot is valid
        boolean isValid = withinRange(spot) && !isSpotTaken(spot);

        if (isValid){
            board[spot-1] = currentMarker;

            // if user turn then make current user marker, else make ai marker
            currentMarker = (currentMarker == userMarker) ? aiMarker : userMarker;
        }
        return isValid;
    }

    // check is the spot is in the range
    public boolean withinRange(int spot){
        return (spot > 0 && spot < board.length +1);
    }

    // check is the spot is already taken
    public boolean isSpotTaken(int spot){
        return board[spot - 1] != '-';
    }

    public void printBoard(){
        System.out.println();

        for(int i = 0; i < board.length; i++){
            if (i % 3 == 0 && i != 0){
                System.out.println();
                System.out.println(" ------------");
            }
            System.out.print(" | " + board[i]);
        }
        System.out.println();
    }

    public static void printIndexBoard(){
        System.out.println();

        for(int i = 0; i < 9; i++){
            if (i % 3 == 0 && i != 0){
                System.out.println();
                System.out.println(" ------------");
            }
            System.out.print(" | " + (i + 1));
        }
        System.out.println();
    }

    // checks if there is a winner combination on the board
    public boolean isThereAWinner(){

        boolean diagonalsAndMiddles = (rightDig() || leftDig() || middleRow() || secondCol()) && board[4] != '-';
        boolean topAndFirst = (topRow() || firstCol()) && board[0] != '-';
        boolean bottomAndThird = (bottomRow() || thirdCol()) && board[8] != '-';

        if (diagonalsAndMiddles){
            this.winner = board[4];
        }else if (topAndFirst){
            this.winner = board[0];
        } else if (bottomAndThird){
            this.winner = board[8];
        }

        return diagonalsAndMiddles || topAndFirst || bottomAndThird;
    }

    public boolean topRow(){
        return board[0] == board[1] && board[1] == board[2];
    }

    public boolean middleRow(){
        return board[3] == board[4] && board[4] == board[5];
    }

    public boolean bottomRow(){
        return board[6] == board[7] && board[7] == board[8];
    }

    public boolean firstCol(){
        return board[0] == board[3] && board[3] == board[6];
    }

    public boolean secondCol(){
        return board[1] == board[4] && board[4] == board[7];
    }

    public boolean thirdCol(){
        return board[2] == board[5] && board[5] == board[8];
    }

    public boolean rightDig(){
        return board[0] == board[4] && board[4] == board[8];
    }

    public boolean leftDig(){
        return board[2] == board[4] && board[4] == board[6];
    }

    // check if the board is filled
    public boolean isBoardFilled(){

        for ( int i=0; i < board.length; i++){
            if (board[i] == '-'){
                return false;
            }
        }
        return true;
    }

    // did someone win?
    public String gameOver(){

        boolean didSomeoneWin = isThereAWinner();

        if (didSomeoneWin){
            return "We have a winner: " + this.winner + "!!!";
        } else if (isBoardFilled()){
            return "Draw. Game is over. Sorry!";
        } else
            return "notOver";
    }
}
