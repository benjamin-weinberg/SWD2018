/**
 * The Board class holds all of the needs to play a tick tack toe game
 * @author Ben Weinberg
 */
public class Board {
    private char[][] board = new char[3][3];
    private Player[] players = new Player[2];
    private int turn = 0;

    /**
     * Constructor for board
     * @param p1 player 1
     * @param p2 player 2
     */
    public Board(Player p1, Player p2){
        players[0] = p1;
        players[1] = p2;
    }

    /**
     * run this method to play the game after adding players in
     * runs by polymorphism
     */
    public void play(){
        while(!win()){
            turn = (turn+1)%2;
            if(turn == 0)System.out.println("X's turn:");
            if(turn == 1)System.out.println("O's turn:");
            int[] move = players[turn].turn(this);
            if(turn == 0)board[move[0]][move[1]] = 'X';
            if(turn == 1)board[move[0]][move[1]] = 'O';
            printBoard();
        }
    }

    /**
     * This method checks to see if a player won and displays them if it does
     * @return
     */
    public boolean win (){
        if(winCheck('X')){
            System.out.println("X wins the game!");
            return true;
        }
        else if(winCheck('O')) {
            System.out.println("O wins the game!");
            return true;
        }
        else{
            boolean full = true;
            for(int i = 0; i<3;i++){
                for(int j = 0; j<3; j++){
                    if (board[i][j] != 'X' && board[i][j] != 'O') full = false;
                }
            }
            if(full) System.out.println("Cats Game! No Winners");
            return full;
        }
    }

    /**
     * helper method to help determine a winner
     * @param player player to check for
     * @return if the player won
     */
    private boolean winCheck(char player){
        // check all win conditions
        for (int i = 0; i < 3; i++){
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] == player) return true;         // column wins
            else if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] == player) return true;    // row wins
        }
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[1][1] == player) return true;             // top left to bot right
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[1][1] == player) return true;             // top right to bot left
        return false;
    }

    /**
     * Prints the status of the board
     */
    public void printBoard(){
        // go thru the whole board
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(board[i][j] == 'X' || board[i][j] == 'O') System.out.print(board[i][j]);
                else System.out.print(' ');
                if(j<2) System.out.print('|');
            }
            if(i<2) System.out.print("\n-----\n");
        }
        System.out.println("\n\n");
    }

    /**
     * provide the board to players if they request
     */
    public char[][] getBoard() {
        return board;
    }
}
