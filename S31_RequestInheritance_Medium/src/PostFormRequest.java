import java.util.UUID;

/**
 * type of request to post a form
 * @author Ben Weinberg
 */
public class PostFormRequest extends PostRequest {
    private Form requestForm;
    private static int timesRequested=0;

    public PostFormRequest(UUID requestUUID, String IP, Form requestForm){
        super(requestUUID, IP);
        timesRequested++;
        this.requestForm = requestForm;
    }

    // print info correctly
    @Override
    public String toString() {
        String output = super.toString();
        output += requestForm.toString();
        return output;
    }

    public static int count(){
        return timesRequested;
    }
}
