import java.util.UUID;

/**
 * type of post request with a payment
 */
public class PostPaymentRequest extends PostRequest{
    private Payment pay;
    private static int timesRequested=0;
    public PostPaymentRequest(UUID requestID, String IP, Payment pay){
        super(requestID, IP);
        timesRequested++;
        this.pay = pay;
    }

    // print info correctly
    @Override
    public String toString() {
        String output = super.toString();
        output += pay.toString();
        return output;
    }

    public static int count(){
        return timesRequested;
    }
}
