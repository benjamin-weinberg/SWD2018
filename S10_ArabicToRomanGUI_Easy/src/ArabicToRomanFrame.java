import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Class that will create the basic frame for the GUI
 * @author Ben Weinberg
 */
public class ArabicToRomanFrame extends JFrame {
    private final JTextField input;
    private final JTextField output;

    /**
     * Displays and runs the GUI
     */
    public static void main(String[] args){
        ArabicToRomanFrame frame = new ArabicToRomanFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(425, 100);
        frame.setVisible(true);
    }

    /**
     * Builds the frame for the GUI
     */
    public ArabicToRomanFrame() {
        super("Arabic to Roman");
        Box box = Box.createHorizontalBox(); // create box

        input = new JTextField("Enter Arabic Test Here",10);
        box.add(input);

        TextFieldHandler handler = new TextFieldHandler();
        input.addActionListener(handler);

        output = new JTextField(10);
        output.setEditable(false);
        box.add(output);

        add(box); // add box to frame
    }

    /**
     * Handles the event when enter is pressed
     */
    private class TextFieldHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            String string =  event.getActionCommand();
            output.setText(new Roman(string).getRomanNumber());
        }
    }
}
