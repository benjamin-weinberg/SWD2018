import javax.swing.*;

/**
 * Converts binary to hex and hex to binary, or decimal to octal and octal to decimal
 */
public class Converter {
    public static void main(String[] args){
        String currentNumber = "0";
        String currentBase = "0";
        boolean valid = false;          // for data validation
        while(!valid) {                 // keep asking for inputs until a valid one is given
            currentNumber = JOptionPane.showInputDialog("Enter current number");
            currentBase = JOptionPane.showInputDialog("Enter current base as a number");
            valid = true;
            try {
                Integer.parseInt(currentNumber, Integer.parseInt(currentBase));
            } catch (NumberFormatException e) {                                             // catch the exception if the number given is not in that base
                valid = false;
                JOptionPane.showMessageDialog(null, currentNumber + "is not in base " + currentBase + ". Please enter a new number and base!","Error", JOptionPane.PLAIN_MESSAGE);
            }
        }
        String output;
        int base = Integer.parseInt(currentBase);
        if(base == 2) output = binaryToHex(currentNumber);              // current base is binary, convert to hex
        else if(base == 16) output =hexToBinary(currentNumber);         // current base is hex, convert to binary
        else if(base == 10) output = decToOct(currentNumber);           // current base is decimal, convert to octal
        else if(base == 8) output = octToDec(currentNumber);            // current base is octal, convert to decimal
        else output = "Base " + currentBase + " not supported";         // if the base is not supported, display this message

        JOptionPane.showMessageDialog(null, "The Answer is: " + output,"Result", JOptionPane.PLAIN_MESSAGE);    // show result
    }

    /**
     * Converts binary to hex
     * @param number binary number as a string
     * @return hex number as a string
     */
    public static String binaryToHex(String number) {
        return Integer.toHexString(Integer.parseInt(number,2));
    }

    /**
     * Converts binary to hex
     * @param number hex number as a string
     * @return binary number as a string
     */
    public static String hexToBinary(String number) {
        return Integer.toBinaryString(Integer.parseInt(number,16));
    }

    /**
     * Converts binary to hex
     * @param number decimal number as a string
     * @return octal number as a string
     */
    public static String decToOct(String number){
        return Integer.toOctalString(Integer.parseInt(number, 10));
    }

    /**
     * Converts binary to hex
     * @param number octal number as a string
     * @return decimal number as a string
     */
    public static String octToDec(String number){
        return Integer.toString(Integer.parseInt(number, 8));
    }
}