/**
 * Holds all payment information
 * @author Ben Weinberg
 */
public class Payment {
    private String sender;
    private int amount;
    private String receiver;
    public Payment(String sender, int amount, String reciever){
        this.sender = sender;
        this.amount = amount;
        this.receiver = reciever;
    }

    //print the information properly
    @Override
    public String toString(){
        return "\nPayment data: \nSender: " + sender + "\nAmount: " + amount + "\nReceiver: " + receiver;
    }
}
