/**
 * The point class will hold the contents of a point and its location
 * @author Ben Weinberg
 */
public class Point {
    // Variables to store the location and data of the maze
    private final int row;
    private final int column;
    private char data;

    /**
     * Constructor for Point that will initialize the variables and hold the initial data
     * @param row The row of the point
     * @param column The column of the point
     * @param data The initial data of the point
     */
    public Point(int row, int column, char data){
        this.row = row;
        this.column = column;
        this.data = data;
    }

    /**
     * @return Returns the row of the point
     */
    public int getRow() {
        return row;
    }

    /**
     * @return Returns the column of the point
     */
    public int getColumn() {
        return column;
    }

    /**
     * @return Returns the current Data of the point
     */
    public char getData() {
        return data;
    }

    /**
     *  Used to set the data of the point to a new value
     * @param data new data to be held in the point
     */
    public void setData(char data){
        this.data = data;
    }
}
