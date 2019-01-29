import java.util.UUID;

/**
 * Type of request
 * @author Ben Weinberg
 */
public class GetRequest extends Request {
    private String URL;
    private static int timesRequested = 0;
    public GetRequest(UUID requestID, String URL){
        super(requestID);
        timesRequested++;
        this.URL = URL;
    }

    // prints proper information
    @Override
    public String toString(){
        String output = super.toString();
        if (URL != "")output += "\nUniversal Resource Locator (URL): " + URL;
        return output;
    }

    public static int count(){
        return timesRequested;
    }
}
