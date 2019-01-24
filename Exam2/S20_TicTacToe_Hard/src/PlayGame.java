import java.util.Scanner;

/**
 * Example usage of the board and player classes
 */
public class PlayGame {
    public static void main(String[] args){
        Player p1 = new ComputerPlayer();
        Player p2 = new ComputerPlayer();
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the number of the type of game you would like to play: ");
        System.out.println("1) 2 Humans");
        System.out.println("2) 1 Human 1 Computer");
        System.out.println("3) 2 Computers\n");
        System.out.print("Enter game number (1-3):  ");
        int type = input.nextInt();
        if(type == 1){
            p1 = new HumanPlayer();
            p2 = new HumanPlayer();
        }
        else if(type == 2){
            p1 = new HumanPlayer();
        }
        Board board = new Board(p1, p2);
        board.play();
    }
}
