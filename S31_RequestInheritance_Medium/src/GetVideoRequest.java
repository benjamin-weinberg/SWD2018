import java.util.UUID;

/**
 * Type of request to get a video
 * @author Ben Weinberg
 */
public class GetVideoRequest extends GetRequest {
    private Video vid;
    private static int timesrequested=0;

    public GetVideoRequest(UUID requestID, Video vid){
        super(requestID, "file://" + vid.getURL());
        this.vid = vid;
        timesrequested++;
    }

    //print proper information
    @Override
    public String toString() {
        String output = super.toString();
        output += vid.toString();
        return output;
    }

    public static int count(){
        return timesrequested;
    }
}
