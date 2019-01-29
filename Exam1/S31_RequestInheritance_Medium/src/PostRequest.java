import java.util.UUID;

/**
 * type of request to post something
 * @author Ben Weinberg
 */
public class PostRequest extends Request {
    private String IP;
    private static int timesRequested=0;
    public PostRequest(UUID requestID, String IP){
        super(requestID);
        timesRequested++;
        this.IP = IP;
    }

    //print information out correctly
    @Override
    public String toString() {
        String output = super.toString();
        output += "\nPost request to server with IP address: " + IP;
        return output;
    }

    public static int count(){
        return timesRequested;
    }
}
