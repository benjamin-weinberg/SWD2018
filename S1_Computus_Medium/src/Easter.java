/**
 * The Easter class will calculate and print out the number of times easter will occur before they repeat
 * @author Ben Weinberg
 */

public class Easter {
    private final int month;            // to store the year months and date that easter will occur on
    private final int day;
    private final int year;

    /**
     * Constructor for Easter that will automatically calculate the day and month for easter
     * @param year      the year in which easter should be calculated for
     */
    public Easter(int year) {
        this.year = year;
        int a = year % 19;                                  // start formula for calculating month/day
        int b = year / 100;
        int c = year % 100;
        int d = b / 4;
        int e = b % 4;
        int f = (b + 8) / 25;
        int g = (b - f + 1) / 3;
        int h = (19 * a + b - d - g + 15) % 30;
        int i = c / 4;
        int k = c % 4;
        int l = (32 + 2 * e + 2 * i - h - k) % 7;
        int m = (a + 11 * h + 22 * l) / 451;
        month = (h + l - 7 * m + 114) / 31;                 // set the month based on the formula
        day = ((h + l - 7 * m + 114) % 31) + 1;             // set the day based on the formula
    }

    public static void main(String[] args) {

        int startDate = 2018;

        int[] possibleDates = new int[35];
        Easter e;
        for (int i = startDate; i < (startDate+5700000); i++){              // repeat for 5700000 years
            e = new Easter (i);


            // Sort each day into a position in the array of all possible dates for easter
            if (e.getMonth() == 3){
                if (e.getDay() == 22){possibleDates[0]++;}
                else if (e.getDay() == 23){possibleDates[1]++;}
                else if (e.getDay() == 24){possibleDates[2]++;}
                else if (e.getDay() == 25){possibleDates[3]++;}
                else if (e.getDay() == 26){possibleDates[4]++;}
                else if (e.getDay() == 27){possibleDates[5]++;}
                else if (e.getDay() == 28){possibleDates[6]++;}
                else if (e.getDay() == 29){possibleDates[7]++;}
                else if (e.getDay() == 30){possibleDates[8]++;}
                else if (e.getDay() == 31){possibleDates[9]++;}
                else System.out.println("INVALID DAY 3");
            }
            else if (e.getMonth() == 4){
                if (e.getDay() == 1){possibleDates[10]++;}
                else if (e.getDay() == 2){possibleDates[11]++;}
                else if (e.getDay() == 3){possibleDates[12]++;}
                else if (e.getDay() == 4){possibleDates[13]++;}
                else if (e.getDay() == 5){possibleDates[14]++;}
                else if (e.getDay() == 6){possibleDates[15]++;}
                else if (e.getDay() == 7){possibleDates[16]++;}
                else if (e.getDay() == 8){possibleDates[17]++;}
                else if (e.getDay() == 9){possibleDates[18]++;}
                else if (e.getDay() == 10){possibleDates[19]++;}
                else if (e.getDay() == 11){possibleDates[20]++;}
                else if (e.getDay() == 12){possibleDates[21]++;}
                else if (e.getDay() == 13){possibleDates[22]++;}
                else if (e.getDay() == 14){possibleDates[23]++;}
                else if (e.getDay() == 15){possibleDates[24]++;}
                else if (e.getDay() == 16){possibleDates[25]++;}
                else if (e.getDay() == 17){possibleDates[26]++;}
                else if (e.getDay() == 18){possibleDates[27]++;}
                else if (e.getDay() == 19){possibleDates[28]++;}
                else if (e.getDay() == 20){possibleDates[29]++;}
                else if (e.getDay() == 21){possibleDates[30]++;}
                else if (e.getDay() == 22){possibleDates[31]++;}
                else if (e.getDay() == 23){possibleDates[32]++;}
                else if (e.getDay() == 24){possibleDates[33]++;}
                else if (e.getDay() == 25){possibleDates[34]++;}
                else System.out.println("INVALID DAY 4");
            }
        }

        // print all values form the array associated with each day
        System.out.println("On: March 22 easter will occur " + possibleDates[0] + " times");
        System.out.println("On: March 23 easter will occur " + possibleDates[1] + " times");
        System.out.println("On: March 24 easter will occur " + possibleDates[2] + " times");
        System.out.println("On: March 25 easter will occur " + possibleDates[3] + " times");
        System.out.println("On: March 26 easter will occur " + possibleDates[4] + " times");
        System.out.println("On: March 27 easter will occur " + possibleDates[5] + " times");
        System.out.println("On: March 28 easter will occur " + possibleDates[6] + " times");
        System.out.println("On: March 29 easter will occur " + possibleDates[7] + " times");
        System.out.println("On: March 30 easter will occur " + possibleDates[8] + " times");
        System.out.println("On: March 31 easter will occur " + possibleDates[9] + " times");
        System.out.println("On: April 1 easter will occur " + possibleDates[10] + " times");
        System.out.println("On: April 2 easter will occur " + possibleDates[11] + " times");
        System.out.println("On: April 3 easter will occur " + possibleDates[12] + " times");
        System.out.println("On: April 4 easter will occur " + possibleDates[13] + " times");
        System.out.println("On: April 5 easter will occur " + possibleDates[14] + " times");
        System.out.println("On: April 6 easter will occur " + possibleDates[15] + " times");
        System.out.println("On: April 7 easter will occur " + possibleDates[16] + " times");
        System.out.println("On: April 8 easter will occur " + possibleDates[17] + " times");
        System.out.println("On: April 9 easter will occur " + possibleDates[18] + " times");
        System.out.println("On: April 10 easter will occur " + possibleDates[19] + " times");
        System.out.println("On: April 11 easter will occur " + possibleDates[20] + " times");
        System.out.println("On: April 12 easter will occur " + possibleDates[21] + " times");
        System.out.println("On: April 13 easter will occur " + possibleDates[22] + " times");
        System.out.println("On: April 14 easter will occur " + possibleDates[23] + " times");
        System.out.println("On: April 15 easter will occur " + possibleDates[24] + " times");
        System.out.println("On: April 16 easter will occur " + possibleDates[25] + " times");
        System.out.println("On: April 17 easter will occur " + possibleDates[26] + " times");
        System.out.println("On: April 18 easter will occur " + possibleDates[27] + " times");
        System.out.println("On: April 19 easter will occur " + possibleDates[28] + " times");
        System.out.println("On: April 20 easter will occur " + possibleDates[29] + " times");
        System.out.println("On: April 21 easter will occur " + possibleDates[30] + " times");
        System.out.println("On: April 22 easter will occur " + possibleDates[31] + " times");
        System.out.println("On: April 23 easter will occur " + possibleDates[32] + " times");
        System.out.println("On: April 24 easter will occur " + possibleDates[33] + " times");
        System.out.println("On: April 25 easter will occur " + possibleDates[34] + " times");

    } // end method main

    /**
     * @return month        returns the month that easter will occur as an int (1-12)
     */
    public int getMonth() {
        return month;
    }

    /**
     * @return day          returns the day in which easter will occur as an int (1-31)
     */
    public int getDay() {
        return day;
    }
}
