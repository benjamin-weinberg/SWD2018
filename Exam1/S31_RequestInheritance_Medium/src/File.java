/**
 * File class holds a type and path to a file
 * @author Ben Weinberg
 */
public class File {
    private String path;
    private String type;
    public File(String path, String type){
        this.type = type;
        this.path = path;
    }

    @Override
    public String toString() {
        return "\nFile Path: " + path + "\nFile Type: " + type;
    }

    public String getPath() {
        return path;
    }
}
