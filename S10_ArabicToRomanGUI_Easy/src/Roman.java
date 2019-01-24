/**
 * Basic Roman Numeral class that holds the roman version of a number
 * @author Ben Weinberg
 */
public class Roman {
    private static int[] number = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};                           // all possible arabic number combinations that directly convert to roman
    private static String[] letter = {"M",  "CM",  "D",  "CD", "C",  "XC", "L", "XL", "X", "IX", "V", "IV", "I"};   // what each number listed above looks like in roman

    private String romanNumber;

    /**
     * Takes in a Arabic number and generates the roman number it represents
     * @param input the arabic number that should be converted to roman
     */
    public Roman (String input){
        int arabic = Integer.parseInt(input);
        romanNumber = "";
        for(int i = 0; i < number.length; i++){         // go through each number in the list above
            while(arabic >= number[i]){                 // if the arabic number is greater than the possible direct conversion,
                romanNumber += letter[i];               // then add the conversion to the end of the string
                arabic -= number[i];                    // and subtract that amount from the total
            }
        }
    }

    /**
     * Provides the Roman representation of the number
     * @return returns the roman representation of the number
     */
    public String getRomanNumber(){
        return romanNumber;
    }
}
