import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ClientTest
{
    public static void main(String[] args)
    {
        Client client = new Client("127.0.0.1");
        client.runClient();
    }
}
