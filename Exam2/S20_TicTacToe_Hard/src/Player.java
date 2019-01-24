/**
 * The Player class is the base class for the computer and human players
 * @author Ben Weinberg
 */
public abstract class Player {
    /**
     * check to make sure that the move a player is trying to make is valid
     * @param board the current board
     * @param move the current move as an array
     * @return true or false. If the move is valid return false
     */
    public boolean checkValid(Board board, int[] move){
        char[][] currentBoard= board.getBoard();
        if(currentBoard[move[0]][move[1]] == 'X' || currentBoard[move[0]][move[1]] == 'O') return true;
        else return false;
    }

    /**
     * Abstract method to provide base for other players
     * @param board current board
     * @return return the move requested
     */
    public abstract int[] turn(Board board);
}
