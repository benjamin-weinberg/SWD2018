import java.util.UUID;

/**
 * type of post request to post encrypted payment
 * @author Ben Weinberg
 */
public class PostEncryptedPaymentRequest extends PostPaymentRequest{
    private static int timesrequested=0;
    private String encryption;

    public PostEncryptedPaymentRequest(UUID requestID, String IP, Payment pay, String encryption){
        super(requestID, IP, pay);
        this.encryption = encryption;
        timesrequested++;
    }

    // print info correctly
    @Override
    public String toString() {
        String output = super.toString();
        output += "\nThis payment was encrypted using: " + encryption;
        return output;
    }

    public static int count(){
        return timesrequested;
    }
}
