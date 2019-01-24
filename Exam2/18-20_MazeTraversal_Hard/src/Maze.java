/**
 * The Maze class will hold the contents of the maze and provide traversal methods to move through the maze
 * The algorithm use here requires that the ending point is on the right side and the entry is on the left
 * @author Ben Weinberg
 */
public class Maze {
    private static final int rows = 12; // max number of rows
    private static final int columns = 12; // max number of columns

    /**
     *  This method will tell you what is above the point passed in
     * @param current the point that you want to know what is above
     * @return the data at the point above
     */
    public static char getUp(Point current, Point[][] pointArray) {
        if (current.getRow() - 1 >= 0 && current.getRow() - 1 <= rows) {
            return pointArray[current.getRow() - 1][current.getColumn()].getData();
        } else return '#';
    }

    /**
     *  This method will tell you what is to the right of the point passed in
     * @param current the point that you want to know what is to the right of
     * @return the data at the point to the right
     */
    public static char getRight(Point current, Point[][] pointArray) {
        if (current.getColumn() + 1 >= 0 && current.getColumn() + 1 < columns) {
            return pointArray[current.getRow()][current.getColumn() + 1].getData();
        }
        else if(current.getColumn() + 1 == columns){return 'E';} // WIN CONDITION (you are currently as far right as you can go so you must have won)
        else return '#';
    }

    /**
     *  This method will tell you what is below of the point passed in
     * @param current the point that you want to know what is below
     * @return the data at the point below it
     */
    public static char getDown(Point current, Point[][] pointArray) {
        if (current.getRow() + 1 >= 0 && current.getRow() + 1 <= rows) {
            return pointArray[current.getRow() + 1][current.getColumn()].getData();
        } else return '#';
    }

    /**
     *  This method will tell you what is to the left of the point passed in
     * @param current the point that you want to know what is to the left of
     * @return the data at the point to the left
     */
    public static char getLeft(Point current, Point[][] pointArray) {
        if (current.getColumn() - 1 >= 0 && current.getColumn() - 1 <= columns) {
            return pointArray[current.getRow()][current.getColumn() - 1].getData();
        } else return '#';
    }

    /**
     * This method will print the current state of the maze to the console.
     */
    public static void printMaze(char[][] mazeIn){
        Point[][] pointArray = charArrToPointArr(mazeIn);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if(pointArray[i][j].getData() == 'O' || pointArray[i][j].getData() == '.') System.out.print(' ');
                else System.out.print(pointArray[i][j].getData());
                System.out.print(' ');
            }
            System.out.println(' ');
        }
        System.out.println(' ');
        System.out.println(' ');
    }

    /**
     * Changes a character array into an array of points (custom cass made by me)
     * @param mazeIn the character array you want to change into a point array
     * @return the point array
     */
    public static Point[][] charArrToPointArr(char[][] mazeIn){
        Point[][] pointArray = new Point[rows][columns]; // array to store all points
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                pointArray[i][j] = new Point(i, j, mazeIn[i][j]);
            }
        }
        return pointArray;
    }

    /**
     * A recursive algorithm to solve mazes
     * @param mazeIn the input character array of a maze
     * @param currentRow the starting row
     * @param currentColumn the starting column
     * @return true if the maze was solved, false if the maze was not solved
     */
    public static boolean mazeTraversal(char mazeIn[][], int currentRow, int currentColumn){
        boolean solved = false;

        Point[][] pointArray = charArrToPointArr(mazeIn);
        Point currentPoint = pointArray[currentRow][currentColumn];

        currentPoint.setData('X');
        mazeIn[currentRow][currentColumn] = 'X';

        if(getRight(currentPoint, pointArray) == 'E'){
            // if there tis nothing to the right, then it is out of bounds and we are at the exit.
            solved = true;
        }
        if(getUp(currentPoint, pointArray) == '.' && !solved){
            // System.out.println("Moving up");
            solved = mazeTraversal(mazeIn, currentRow -1 , currentColumn);
        }
        if(getRight(currentPoint, pointArray) == '.' && !solved){
            // System.out.println("Moving right");
            solved = mazeTraversal(mazeIn, currentRow, currentColumn + 1);
        }
        if(getDown(currentPoint, pointArray) == '.' && !solved){
            // System.out.println("Moving down");
            solved = mazeTraversal(mazeIn, currentRow + 1, currentColumn);
        }
        if(getLeft(currentPoint, pointArray) == '.' && !solved){
            // System.out.println("Moving left");
            solved = mazeTraversal(mazeIn, currentRow , currentColumn - 1);
        }
        if(!solved){
            // if we have nowhere else to go, set this point to 'O' and back up the recursion
            currentPoint.setData('O');
            mazeIn[currentRow][currentColumn] = 'O';
        }
        return solved;
    }




}
