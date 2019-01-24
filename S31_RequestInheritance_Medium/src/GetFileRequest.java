import java.util.UUID;

/**
 * Type of request to get a file
 * @author Ben Weinberg
 */
public class GetFileRequest extends GetRequest {
    private File f;
    private static int timesrequested;

    public GetFileRequest(UUID requestID, File f){
        super(requestID, "file://" + f.getPath());
        this.f = f;
        timesrequested++;
    }

    //print proper information
    @Override
    public String toString() {
        String output = super.toString();
        output += f.toString();
        return output;
    }

    public static int count(){
        return timesrequested;
    }
}
