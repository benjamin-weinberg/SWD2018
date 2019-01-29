import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.EOFException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class Client extends JFrame implements ActionListener, KeyListener {

    //Global variables to be used
    private Scanner input;
    private Formatter output;
    private String address;
    private Socket connection;
    private boolean stopOperations = false;
    private ArrayList<Product> prodList;

    //GUI components of the log in frame
    private JFrame logInFrame;
    private JPanel logInPanel;
    private JButton createAccountButton;
    private JButton logInButton;
    private JLabel outputLogInLabel;
    private JTextField username;
    private JPasswordField password;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JButton guestButton;

    //GUI components of the main frame
    private JFrame mainFrame;
    private JPanel mainPanel;
    private JButton nextItem;
    private JButton previousItem;
    private JButton buyItem;
    private JTextArea productDisp;
    private JButton sellItem;
    private static int indexOfProd = 0;

    //GUI components of the guest frame
    private JFrame guestFrame;
    private JPanel guestPanel;
    private JButton nextButton;
    private JButton previousButton;
    private JButton backToLogIn;
    private JTextArea guestDisplay;
    private static int guestIndexProd = 0;

    //GUI components of the sell frame
    private JFrame sellFrame;
    private JPanel sellPanel;
    private JTextArea information;
    private JLabel instructions;
    private JButton infoCorrect;
    private JLabel name;
    private JLabel price;
    private JLabel description;
    private JLabel quantity;
    private JLabel status;
    private static int enterCounter = 0;
    private static String prodName = "";
    private static String prodPrice = "";
    private static String prodDescription ="";
    private static String prodQuantity = "";
    private boolean firstTime = true;
    private boolean prodNameValid = false;
    private boolean prodPriceValid = false;
    private boolean prodDesValid = false;
    private boolean prodQuantityValid = false;

    Client(String address)
    {
        //Initializing global variables
        prodList = new ArrayList<Product>();
        this.address = address;
        logInFrame = new JFrame("Log In");
        mainFrame = new JFrame("Menu");
        sellFrame = new JFrame("Sell");

        logInFrame.setSize(300,300);
        mainFrame.setSize(400,400);
        sellFrame.setSize(400,400);


        //This is creating the LogIn frame
        logInPanel = new JPanel();
        logInFrame.add(logInPanel);
        logInPanel.setLayout(null);

        createAccountButton = new JButton("Create Account");
        createAccountButton.setBounds(70,120,150,20);

        logInButton = new JButton("Log In");
        logInButton.setBounds(70,150,150,20);

        outputLogInLabel = new JLabel("<html>If you have an existing account enter your username and password, otherwise " +
                "enter a new username and password to create an account.</html>");
        outputLogInLabel.setBounds(15,160,280,100);

        username = new JTextField();
        username.setBounds(70,20,150,20);

        password = new JPasswordField();
        password.setBounds(70,60,150,20);

        usernameLabel = new JLabel("username");
        usernameLabel.setBounds(120,40,70,10);

        passwordLabel = new JLabel("password");
        passwordLabel.setBounds(120,80,70,20);

        guestButton = new JButton("Guest");
        guestButton.setBounds(70,240,150,20);

        logInPanel.add(usernameLabel);
        logInPanel.add(passwordLabel);
        logInPanel.add(username);
        logInPanel.add(password);
        logInPanel.add(createAccountButton);
        logInPanel.add(logInButton);
        logInPanel.add(outputLogInLabel);
        logInPanel.add(guestButton);
        logInFrame.setContentPane(logInPanel);
        logInFrame.setVisible(true);
        logInFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //These are all of the button listeners for the log in GUI
        createAccountButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                sendNewUserInfo(username.getText(), password.getText()); //creates new account
            }
        });

        logInButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendExistingUserInfo(username.getText(),password.getText());//logs in the user
            }
        });

        guestButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                guestWindow();//creates the guest window GUI
                logInFrame.setVisible(false);
                logInFrame.repaint();//hides the log in GUI
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == infoCorrect)
        {
            if (prodNameValid && prodQuantityValid && prodDesValid && prodPriceValid)//if everything entered is valid
            {
                prodName = prodName.replace("\n","");
                prodPrice = prodPrice.replace("\n","");
                prodDescription = prodDescription.replace("\n","");
                prodQuantity = prodQuantity.replace("\n","");
                //removing all of the new lines so that the message can be correctly looked at
                String message = "sell:"+prodName+","+prodPrice+","+prodDescription+","+prodQuantity+"\n";
                output.format(message);
                output.flush();
                status.setText("Put onto Market!");
                information.setEditable(false);
                sellFrame.repaint();
                sellPanel.remove(infoCorrect);//makes it so nobody can enter any more information in the sell window
            }
            else
            {
                status.setText("Invalid Information");
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        if (e.getSource() == information && e.getKeyChar() == KeyEvent.VK_ENTER && firstTime)//these are checking the enter button when the seller GUI is open
        {
            if (enterCounter == 0) //this is when someone enters their product name
            {
                prodName = information.getText();
                information.setText("");
                instructions.setText("Enter the product price.");
                if (prodName.length()>1)
                {
                    name.setText("name of product: valid");
                    prodNameValid = true;
                }
                else
                {
                    prodNameValid = false;
                }
            }
            if (enterCounter == 1)//when someone enters their prodcuts price
            {
                prodPrice = information.getText();
                information.setText("");
                instructions.setText("Enter a product description.");
                try {
                    double priceDouble = Double.parseDouble(prodPrice);
                    new DecimalFormat("#.###").format(priceDouble);
                    prodPrice = Double.toString(priceDouble);
                    prodPriceValid = true;
                    price.setText("price: valid");
                } catch (NumberFormatException error) {
                    prodPriceValid = false;
                }
            }
            if (enterCounter == 2)//when someone enters their product description
            {
                prodDescription = information.getText();
                information.setText("");
                instructions.setText("Enter the quantity");
                if (prodDescription.length()>1)
                {
                    description.setText("description: valid");
                    prodDesValid= true;
                }
                else
                {
                    prodDesValid = false;
                }

            }
            if (enterCounter == 3)//when someone enters their name for the seller information
            {
                prodQuantity = information.getText();
                prodQuantity = prodQuantity.replace("\n","");
                try
                {
                    int priceInt = Integer.valueOf(prodQuantity);
                    prodQuantity = Integer.toString(priceInt);
                    prodQuantityValid= true;
                    quantity.setText("quantity: valid");
                } catch (NumberFormatException error)
                {
                    prodQuantityValid = false;
                }
                firstTime = false;
                enterCounter = 0;
            }
            enterCounter++;
        }
        if (e.getSource() == information && e.getKeyChar() == KeyEvent.VK_ENTER && !firstTime)//the next couple times through, the GUI will cycle through continuously
        {
            if (enterCounter == 1)
            {
                prodQuantity = information.getText();
                information.setText(prodName);
                instructions.setText("Please enter the product name");
                prodQuantity = prodQuantity.replace("\n","");
                try
                {
                    Integer priceInt = Integer.parseInt(prodQuantity);
                    prodQuantity = Integer.toString(priceInt);
                    prodQuantityValid= true;
                    quantity.setText("quantity: valid");
                } catch (NumberFormatException error)
                {
                    prodQuantityValid = false;
                }
            }
            if (enterCounter == 2)
            {
                prodName = information.getText();
                information.setText(prodPrice);
                instructions.setText("Enter the product price.");
                prodPrice = information.getText();
                if (prodName.length()>1)
                {
                    name.setText("name of product: valid");
                    prodNameValid = true;
                }
                else
                {
                    prodNameValid = false;
                }


            }
            if (enterCounter == 3)
            {
                prodPrice = information.getText();
                information.setText(prodDescription);
                instructions.setText("Enter a product description.");


                try {
                    double priceDouble = Double.parseDouble(prodPrice);
                    new DecimalFormat("#.###").format(priceDouble);
                    prodPrice = Double.toString(priceDouble);
                    prodPriceValid = true;
                    price.setText("price: valid");
                } catch (NumberFormatException error) {
                    prodPriceValid = false;
                }
            }
            if (enterCounter == 4)
            {
                prodDescription = information.getText();
                information.setText(prodQuantity);
                instructions.setText("Enter your quantity");
                if (prodDescription.length()>1)
                {
                    description.setText("description: valid");
                    prodDesValid= true;
                }
                else
                {
                    prodDesValid = false;
                }
                enterCounter = 0;
            }
            enterCounter++;
        }
    }

    //This is the main window that allows people to look through the things for sale
    private void mainWindow()
    {
        //Marcus created mainFrame
        mainPanel = new JPanel();
        nextItem = new JButton("next");
        previousItem = new JButton("back");
        buyItem = new JButton("Buy!");
        productDisp = new JTextArea();
        sellItem = new JButton("sell");


        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(sellItem, BorderLayout.NORTH);
        mainPanel.add(nextItem, BorderLayout.EAST);
        mainPanel.add(previousItem, BorderLayout.WEST);
        mainPanel.add(new JScrollPane(productDisp), BorderLayout.CENTER);
        mainPanel.add(buyItem, BorderLayout.SOUTH);

        mainFrame.setVisible(true);
        mainFrame.setContentPane(mainPanel); //setting the main Frame as true when called
        mainFrame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                stopOperations = true;
                closeConnection();
                System.exit(0);
            }


        });

        nextItem.addActionListener(new ActionListener()//cycles through the product list to show what is for sale
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (prodList.size()>0)//makes sure the prodList doesn't go out of bounds
                {
                    indexOfProd++;
                    if (indexOfProd >= prodList.size()) {
                        indexOfProd = 0;
                    }
                    productDisp.setText(prodList.get(indexOfProd).convertToAppend());
                }
                else
                {
                    productDisp.setText("Nothing in stock.");
                }
            }
        });


        previousItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (prodList.size()>0)//makes sure the prodList doesn't go out of bounds
                {
                    indexOfProd--;
                    if (indexOfProd < 0)
                    {
                        indexOfProd = prodList.size()-1;
                    }

                    productDisp.setText(prodList.get(indexOfProd).convertToAppend());
                }
                else
                {
                    productDisp.setText("Nothing in stock.");
                }
            }
        });


        buyItem.addActionListener(new ActionListener()//when someone clicks the buy button, the ID of the item is sent so it can be removed from the database
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                output.format("buy:"+prodList.get(indexOfProd).getId()+"\n");
                output.flush();
            }
        });

        sellItem.addActionListener(new ActionListener() {//opens up the sell window GUI
            @Override
            public void actionPerformed(ActionEvent e)
            {
                sellWindow();
            }
        });

    }

    //guest window when someone clicks "guest" on the log in GUI
    private void guestWindow()
    {
        guestFrame = new JFrame("Guest User");
        guestFrame.setSize(400,400);

        guestPanel = new JPanel();
        nextButton = new JButton("   Next   ");
        previousButton = new JButton("Previous");
        backToLogIn = new JButton("Back to Log In Screen");
        guestDisplay = new JTextArea();

        guestPanel.setLayout(new BorderLayout());
        guestPanel.add(nextButton, BorderLayout.EAST);
        guestPanel.add(previousButton, BorderLayout.WEST);
        guestPanel.add(new JScrollPane(guestDisplay), BorderLayout.CENTER);
        guestPanel.add(backToLogIn, BorderLayout.SOUTH);

        guestFrame.setContentPane(guestPanel);
        guestFrame.setVisible(true);
        guestFrame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                output.format("client terminating");
                output.flush();
                stopOperations = true;
                closeConnection();
                System.exit(0);
            }


        });

        sendExistingUserInfo("guest","guest");//logs the user in as a guest

        nextButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (prodList.size()>0)
                {
                    guestIndexProd++;
                    if (guestIndexProd >= prodList.size())
                    {
                        guestIndexProd= 0;
                    }
                    guestDisplay.setText(prodList.get(guestIndexProd).convertToAppend());
                }
                else
                {
                    guestDisplay.setText("Nothing in stock.");
                }
            }
        });

        previousButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (prodList.size()>0)
                {
                    guestIndexProd--;
                    if (guestIndexProd < 0)
                    {
                        guestIndexProd= prodList.size()-1;
                    }

                    guestDisplay.setText(prodList.get(guestIndexProd).convertToAppend());
                }
                else
                {
                    guestDisplay.setText("Nothing in stock.");
                }
            }
        });

        backToLogIn.addActionListener(new ActionListener()//takes the user back to the log in GUI
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                guestFrame.setVisible(false);
                guestFrame.repaint();

                logInFrame.setVisible(true);
                logInFrame.repaint();
            }
        });
    }

    //sell window GUI when someone is trying to enter items to be sold
    private void sellWindow()
    {
        firstTime = true;
        prodNameValid = false;
        prodPriceValid = false;
        prodDesValid = false;
        prodQuantityValid = false;

        sellPanel = new JPanel();
        sellPanel.setLayout(null);

        instructions = new JLabel("Please enter the products name.");
        instructions.setBounds(10,10,200,20);

        information = new JTextArea();
        information.setBounds(10,30,370,150);
        information.setLineWrap(true);

        name = new JLabel("name of product: missing ");
        name.setBounds(10,200,200,20);

        price = new JLabel("price: missing");
        price.setBounds(10,230,200,20);

        description = new JLabel("description: missing");
        description.setBounds(10,260,200,20);

        quantity = new JLabel("quantity: missing");
        quantity.setBounds(10,290,200,20);

        infoCorrect = new JButton("Put on market");
        infoCorrect.setBounds(200,240,150,30);

        status = new JLabel("Enter all of the information.");
        status.setBounds(200,270,150,30);

        sellPanel.add(information);
        sellPanel.add(instructions);
        sellPanel.add(name);
        sellPanel.add(price);
        sellPanel.add(description);
        sellPanel.add(infoCorrect);
        sellPanel.add(quantity);
        sellPanel.add(status);
        sellFrame.setContentPane(sellPanel);


        information.addKeyListener(this);
        infoCorrect.addActionListener(this);

        sellFrame.setVisible(true);//sets the sell frame to visible so
    }
    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    //Runs the client so program can function
    public void runClient()
    {
        try {
            connectToServer();
            getStreams();
            runOperations();
        } catch (EOFException eofException) {
            System.out.println("Connection closed");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("closing connection to server");
            closeConnection();
        }
    }

    //Conects to the server through a socket
    private void connectToServer() throws IOException
    {
        connection = new Socket(InetAddress.getByName(address), 23610);
    }

    //Gets the input and output Streams to be used
    private void getStreams() throws IOException
    {
        output = new Formatter(connection.getOutputStream());
        output.flush();

        input = new Scanner(connection.getInputStream());
    }

    //While loop that is constantly running so the client can always wait on what the server is saying
    private void runOperations() throws IOException
    {
        while(!stopOperations)
        {
            if (input.hasNextLine())
            {
                    determineMessage(input.nextLine());


            }
        }
    }

    //Close connection when necessary
    private void closeConnection() {
        try {
            output.close(); // close output stream
            input.close(); // close input stream
            connection.close(); // close socket
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    //If a user presses the log in button, try and log them in
    public void sendExistingUserInfo(String userName, String password)
    {
            output.format("existingUsername:" + userName+"\n");
            output.flush();

            output.format("existingPassword:" + password+"\n");
            output.flush();
    }

    //Create an account for someone who wants to log in
    public void sendNewUserInfo(String userName,String password)
    {

            output.format("newUsername:" + userName+"\n");
            output.flush();

            output.format("newPassword:" + password+"\n");
            output.flush();
    }

    //Decode what the server says to it
    private void determineMessage(String message)
    {
        if (message.equals("logged in"))//If the login info is correct, get rid of the log in frame,
            //put on the main window.
        {
            logInFrame.setVisible(false);
            logInFrame.repaint();
            mainWindow();

        }else if(message.contains("invalid")) //If there is an invalid log in
        {
            outputLogInLabel.setText("<html>Inavlid username or password. please try again  or create an account</html>");
        }
        if(message.contains("product")) //The server is sending product info, so add the Prod info to the
            //array list so it can be displayed
        {
            message = message.replace("product:","");
            String[] splitStr = message.split(",");
            prodList.add(new Product(splitStr[0],splitStr[1],splitStr[2],splitStr[3],splitStr[4],splitStr[5]));
        }
        if(message.contains("append")) //Output the first item in the appropriate GUI
        {
            if (productDisp != null)
            {
                productDisp.setText(prodList.get(0).convertToAppend());
            }
            if (guestDisplay != null)
            {
                guestDisplay.setText(prodList.get(0).convertToAppend());
            }
        }
        if(message.contains("new list"))//clear everytime something is sold or bought
        {
            prodList.clear();
        }
    }
}
