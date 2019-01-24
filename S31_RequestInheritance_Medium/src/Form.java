import java.util.HashMap;

/**
 * Holds a form's fields and displays them when toString is called
 * @author Ben Weinberg
 */
public class Form {
    private HashMap<String, String> fields;
    public Form(HashMap<String, String> fields){
        this.fields = fields;
    }

    @Override
    public String toString() {
        String output = "\nForm Data:";
        for(String item: fields.keySet()){
            output += "\nLabel: " + item + "\nValue: " + fields.get(item);
        }
        return output;
    }
}
