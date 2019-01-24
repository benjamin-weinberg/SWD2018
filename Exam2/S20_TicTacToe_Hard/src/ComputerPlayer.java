import java.util.Random;
/**
 * The computer has random logic to make moves
 * @author Ben Weinberg
 */
public class ComputerPlayer extends Player {
    /**
     * request the move from the computer
     * @param board current board
     * @return returns requested move
     */
    @Override
    public int[] turn(Board board) {
        Random random = new Random();
        int[] move = new int[2];
        // try a move first, then see if it was valid and try again if its not
        do{
            move[0] = random.nextInt(3);
            move[1] = random.nextInt(3);
        }
        while(this.checkValid(board, move));
        return move;
    }
}
