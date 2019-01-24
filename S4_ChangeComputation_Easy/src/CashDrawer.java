import java.util.Scanner;

/**
 * The CashDrawer class will prompt for an input of how much the item costs and how much was paid for the item
 * and will print out the results for how much change should be given of each type of bill
 *
 * @author Ben Weinberg
 */

public class CashDrawer {
    public static void main(String[] args) {

        CashDrawer d = new CashDrawer();                // initialize cash drawer

        Scanner input = new Scanner(System.in);
        System.out.print("Enter item price: ");
        int price = (int) (input.nextDouble()*100);
        System.out.print("Enter amount paid: ");
        int paid = (int) (input.nextDouble()*100);

        d.makeChange(price, paid);                      // make change for the customer and print it out
    }

    /**
     * This method will calculate and print out the change needed to be given
     * @param priceInCents  the price in cents that the item costs
     * @param paidInCents   the amount in cents that was given to you by the person paying
     */
    static void makeChange(int priceInCents, int paidInCents) {
        int remainder = paidInCents - priceInCents;
        if (remainder < 0) System.out.println("Not enough cash was given");     // not enough cash was given to make change
        else {                                                                  // calculate the change given
            int change20s = remainder / 2000;
            remainder = remainder % 2000;
            int change10s = remainder / 1000;
            remainder = remainder % 1000;
            int change5s = remainder / 500;
            remainder = remainder % 500;
            int change1s = remainder / 100;
            remainder = remainder % 100;
            int changeQuarters = remainder / 25;
            remainder = remainder % 25;
            int changeDimes = remainder / 10;
            remainder = remainder % 10;
            int changeNickles = remainder / 5;
            remainder = remainder % 5;
            int changePennies = remainder;

            System.out.println("\nChange to be given: ");
            System.out.println("20's: " + change20s);
            System.out.println("10's: " + change10s);
            System.out.println("5's: " + change5s);
            System.out.println("1's: " + change1s);
            System.out.println("Quarters: " + changeQuarters);
            System.out.println("Dimes: " + changeDimes);
            System.out.println("Nickles: " + changeNickles);
            System.out.println("Pennies: " + changePennies);
        }
    }
}
