import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
        private ServerSocket server; // server socket
        private ExecutorService service; //thread pool for all of the users connecting to the server
        private User[] users; // sets up an array of users.

        Server()
        {
            service = Executors.newFixedThreadPool(4);
            users = new User[4]; //user aray initialized to contain 4 users
            try
            {
                server = new ServerSocket(23610, 4); // set up ServerSocket
            } catch (IOException ioException)
            {
                ioException.printStackTrace();
                System.exit(1);
            }
        }

        public void execute()
        {
            for (int i = 0; i <4; i++)
            {
                try // wait for connection, create Player, start runnable
                {
                    users[i] = new User(server.accept());
                    service.execute(users[i]); // execute user runnable from the user inner class
                } catch (IOException ioException) {

                    ioException.printStackTrace();
                    System.exit(1);
                }
            }
        }



    private class User implements Runnable {
        private Socket connection; //connection to client
        private Formatter output; // output stream to client
        private Scanner input; // input stream from client
        private Scanner inputString;
        private String username = null;
        private String password = null;
        private boolean loggedIn = false; //logged in is set to false to prevent entering while loop when the user is logged in

        public User(Socket socket) {

            connection = socket;
            try // obtain streams from Socket
            {
                //sets up the streams
                input = new Scanner(connection.getInputStream());
                output = new Formatter(connection.getOutputStream());
                inputString = new Scanner(connection.getInputStream());

            } catch (IOException ioException) {

                ioException.printStackTrace();
                System.exit(1);
            }
        }

        public void run() {
            String inputMessage = "";
            String theProd;
            boolean clienTerminating = false;
            int userID = -1;

            while (!clienTerminating) {

                if (inputString.hasNextLine()) {

                    inputMessage = inputString.nextLine();
                    if (inputMessage.contains("client terminating")) { //checks to see if client is terminating connection
                        clienTerminating = true;
                    } else if (inputMessage.contains("newUsername")) { //if a new username is passed in, then an account is created
                        username = inputMessage.replace("newUsername:", "");
                        //do something on server to create username
                    } else if (inputMessage.contains("newPassword")) { //password to be set for the new user login
                        password = inputMessage.replace("newPassword:", "");
                        //do something on server to create the password
                        if (username != null & password != null) {
                            loggedIn = Database.createAccount(username, password); //validates the username and password. if its good, then logged in is true
                            if (loggedIn) {
                                userID = Database.getUserID(username);
                                output.format("logged in\n"); //login is successful, then this is sent back to let the client know
                                output.flush();
                            } else {
                                output.format("invalid\n"); //login failed and the user must re-enter the information an re login
                                output.flush();
                            }
                            username = null; //variables are reset in case they failed to log in
                            password = null;
                        }
                    } else if (inputMessage.contains("existingUsername")) {
                        username = inputMessage.replace("existingUsername:", ""); // similar to the above check, but this to see if the user actually is stored in the database and if the password is correct

                    } else if (inputMessage.contains("existingPassword")) {
                        password = inputMessage.replace("existingPassword:", "");
                        if (username != null & password != null) {
                            loggedIn = Database.checkUserAndPass(username, password);
                            if (loggedIn && !username.equals("guest")) {
                                userID = Database.getUserID(username);
                                output.format("logged in\n");
                                output.flush();
                            } else if (loggedIn && username.equals("guest")) {
                                //do nothing
                            } else {
                                output.format("invalid\n");
                                output.flush();
                            }
                            username = null;
                            password = null;
                        }
                    }


                    if (!clienTerminating) { //if client is terminating, skip all of this
                        ArrayList<Product> myList; // stores the products from the database, then sends it to the client
                        boolean runonce = false;
                        while (loggedIn) {
                            if (!runonce) {
                                output.format("new list\n");
                                output.flush();
                                myList = Database.getProducts();
                                for (int i = 0; i < myList.size(); i++) {
                                    output.format(myList.get(i).convertToMessage());
                                    output.flush();
                                }
                                output.format("append\n");
                                output.flush();
                                runonce = true;
                            }

                            if (inputString.hasNextLine()) {
                                inputMessage = inputString.nextLine();

                                if (inputMessage.contains("buy")) {
                                    inputMessage = inputMessage.replace("buy:", "");
                                    int prodNum = Integer.parseInt(inputMessage);

                                    Database.buy(prodNum); //logs info in database and updates in inventory

                                    output.format("new list\n");
                                    output.flush();
                                    myList = Database.getProducts();
                                    for (int i = 0; i < myList.size(); i++) { // gets updated inventory
                                        output.format(myList.get(i).convertToMessage());
                                        output.flush();
                                    }
                                    output.format("append");
                                    output.flush();

                                    System.out.println("user " + userID + " bought product " + prodNum);
                                }
                                if (inputMessage.contains("sell")) { // adds a product to the database
                                    inputMessage = inputMessage.replace("sell:", "");
                                    String[] splitStr = inputMessage.split(",");

                                    Product product = new Product("-1", splitStr[0], splitStr[1], splitStr[2], Integer.toString(userID), splitStr[3]);
                                    Database.sell(product, Integer.toString(userID));

                                    output.format("new list\n");
                                    output.flush();
                                    myList = Database.getProducts();
                                    for (int i = 0; i < myList.size(); i++) {
                                        output.format(myList.get(i).convertToMessage());
                                        output.flush();
                                    }
                                    output.format("append\n");
                                    output.flush();
                                }
                            }
                        }
                    }else{

                        try {//closes all connections and streams to client if the client is done
                            output.close();
                            input.close();
                            inputString.close();
                            connection.close();
                            System.out.println("connection to client closed");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }



    }
}
