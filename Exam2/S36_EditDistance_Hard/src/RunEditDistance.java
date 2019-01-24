/**
 * Provides example usage of the EditDistance class
 * @author Ben Weinberg
 */
public class RunEditDistance {
    public static void main(String[] args){
        String s1 = "";
        String s2 = "";

        int output = EditDistance.editDist(s1,s2); // should be 0
        System.out.println("The distance between '" + s1 + "' and '" + s2 + "' is: " + output);

        s1 = "kitten";
        s2 = "sitting";
        output = EditDistance.editDist(s1,s2); // should be 3
        System.out.println("The distance between '" + s1 + "' and '" + s2 + "' is: " + output);

        s1 = "saturday";
        s2 = "sunday";
        output = EditDistance.editDist(s1,s2);  // should be 3
        System.out.println("The distance between '" + s1 + "' and '" + s2 + "' is: " + output);

        s1 = "lawn";
        s2 = "flaw";
        output = EditDistance.editDist(s1,s2);  // should be 2
        System.out.println("The distance between '" + s1 + "' and '" + s2 + "' is: " + output);

        s1 = "manahaton";
        s2 = "manhattan";
        output = EditDistance.editDist(s1,s2);  // should be 3
        System.out.println("The distance between '" + s1 + "' and '" + s2 + "' is: " + output);

        s1 = "test";
        s2 = "";
        output = EditDistance.editDist(s1,s2);
        System.out.println("The distance between '" + s1 + "' and '" + s2 + "' is: " + output);

        s1 = "";
        s2 = "test";
        output = EditDistance.editDist(s1,s2);
        System.out.println("The distance between '" + s1 + "' and '" + s2 + "' is: " + output);
    }
}
