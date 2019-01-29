import java.util.UUID;

/**
 * Base class to request something from a server
 * @author Ben Weinberg
 */
public class Request {
    private UUID requestID;
    private static int timesRequested = 0;
    public Request(UUID requestID){
        this.requestID = requestID;
        timesRequested++;
    }

    //print out correctly
    @Override
    public String toString() {
        return "New Request: \nUUID: " + requestID;
    }

    public static int count(){
        return timesRequested;
    }
}
