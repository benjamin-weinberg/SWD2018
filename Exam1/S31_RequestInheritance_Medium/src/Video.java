/**
 * Holds video information
 * @author Ben Weinberg
 */
public class Video {
    private String URL;
    private String title;
    private String author;

    public Video(String URL, String title, String author){
        this.URL = URL;
        this.title = title;
        this.author = author;
    }

    //print correctly
    @Override
    public String toString(){
        return "\nURL: " + URL + "\nVideo: " + title + "\nBy: " + author;
    }

    public String getURL() {
        return URL;
    }
}
