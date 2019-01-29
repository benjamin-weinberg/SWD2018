import java.util.UUID;

/**
 * Type of post form request that includes encryption
 * @author Ben Weinberg
 */
public class PostEncryptedFormRequest extends PostFormRequest{
    private String encryption;
    private static int timesrequested=0;

    public PostEncryptedFormRequest(UUID requestUUID, String IP, Form requestForm, String requestFormEncryption){
        super(requestUUID, IP, requestForm);
        timesrequested++;
        this.encryption = requestFormEncryption;
    }

    public static int count(){
        return timesrequested;
    }

    // print info correctly
    @Override
    public String toString() {
        String output = super.toString();
        output += "\nThis form was encrypted using: " + encryption;
        return output;
    }
}
