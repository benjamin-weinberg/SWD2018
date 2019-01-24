import java.util.Scanner;
/**
 * The human controlled player logic
 * @author Ben Weinberg
 */
public class HumanPlayer extends  Player{
    /**
     * ask for inputs for the turn
     * @param board current board
     * @return return the requested move
     */
    @Override
    public int[] turn(Board board) {
        int[] move = new int[2];
        boolean repeat = false;
        do {
            if(repeat) System.out.println("Invalid input, try again");
            Scanner input = new Scanner(System.in);
            System.out.print("Enter the row you want to play in (0-2): ");
            int row = input.nextInt();
            System.out.print("Enter the column you want to play in (0-2): ");
            int column = input.nextInt();
            move[0] = row;
            move[1] = column;
            repeat = true;
        }
        while(this.checkValid(board, move));
        return move;
    }
}