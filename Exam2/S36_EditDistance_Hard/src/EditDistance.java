/**
 * The EditDistance class provides functions needed to give the edit distance between two variables.
 * @author Ben Weinberg
 */
public class EditDistance {
    /**
     * Helper function used in the main function
     * @param a int 1 to compare
     * @param b int 2 to compare
     * @param c int 3 to compare
     * @return the minimum of the 3 options
     */
    public static int min(int a,int b,int c)
    {
        if (a<=b && a<=c) return a;
        if (b<=a && b<=c) return b;
        else return c;
    }

    /**
     * This function will take two strings in and return the edit distance between the two strings using a iterative solution
     * @param str1 String 1 to compare
     * @param str2 String 2 to compare
     * @return the edit distance between the two strings
     */
    public static int editDist(String str1 , String str2 )
    {

        int subCost;
        int m = str1.length();
        int n = str2.length();
        int[][] matrix = new int[m+1][n+1];


        if (str1 == "") return n;
        if (str2 == "") return m;
        if (str1 == str2) return 0;


        // first row, matches the length of the string to that point
        // aka, ALL ADDITIONS
        for (int i = 0; i < n+1; i++)
        {
            matrix[0][i] = i;
        }

        // first column, matches the length of the string to that point
        // aka, ALL ADDITIONS
        for (int i = 0; i < m+1; i++) {
            matrix[i][0] = i;
        }

        // check which of the edits are better and put the best one in that location
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (str1.charAt(i) == str2.charAt(j)) subCost = 0;
                else subCost = 1;

                matrix[i+1][j+1] = min(matrix[i][j+1] + 1,              // insert
                                       matrix[i+1][j] + 1,               // delete
                                       matrix[i][j] + subCost);         // sub
            }
        }
        return matrix[m][n];
    }
}
