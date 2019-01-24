import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The imageRotator class makes the GUI run and impliments the listeners
 */
public class ImageRotator implements ActionListener {
    private int angle = 0;  // current angle used for auto rotate
    Timer timer = new Timer(10, this); // timer used to constantly trigger the rotation of the image

    //JFX variables
    @FXML
    private ImageView image;
    @FXML
    private Slider autoSlider;
    @FXML
    private ToggleButton toggleButton;
    @FXML
    private Slider manualSlider;


    /**
     * This makes the automatic rotation keep happening when the timer goes off
     * @param event The event passed from the GUI
     */
    public void actionPerformed(ActionEvent event){
        angle++;
        image.setRotate(angle);
    }

    /**
     * This starts or stops the timer based on if the button is clicked or not
     * @param event event passed from the GUI
     */
    @FXML
    public void autoRotate(MouseEvent event) {
        if(toggleButton.isSelected()){
            timer.start();
        }
        else{
            timer.stop();
        }
    }

    /**
     * Sets the rotation of the image depending on what the value of the manual slider is
     * @param event event passed from the GUI
     */
    @FXML
    void manualDrag(MouseEvent event) {
        image.setRotate(manualSlider.getValue());
    }

    /**
     * Sets the delay of the timer to something different depending on the value of the GUI
     * @param event event passed from the GUI
     */
    @FXML
    void autoDrag(MouseEvent event) {
        timer.setDelay(100/((int) autoSlider.getValue()+1));
    }
}