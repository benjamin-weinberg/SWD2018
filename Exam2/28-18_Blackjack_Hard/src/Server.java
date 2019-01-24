import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The server class does all calculations for the blackjack game, acts as the dealer, and handles the deck
 */
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

    /**
     * runs the server and allows 4 connections from clients
     */
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


    /**
     * The user subclass is how the server will see a client and tells the server how to act
     */
    private class User implements Runnable {
        private Socket connection;  //connection to client
        private Formatter output;   //output stream to client
        private Scanner inputString;//input stream from client

        /**
         * The user that the server is talking to
         * @param socket the socket that the server has with the client
         */
        public User(Socket socket) {
            connection = socket;
            try // obtain streams from Socket
            {
                //sets up the streams
                output = new Formatter(connection.getOutputStream());
                inputString = new Scanner(connection.getInputStream());

            } catch (IOException ioException) {

                ioException.printStackTrace();
                System.exit(1);
            }
        }

        /**
         * runs the server code for the client
         */
        public void run() {
            String inputMessage;
            boolean terminateClient = false;
            Deck deck = new Deck();
            int totalClient = 0;
            int totalDealer = 0;
            boolean deal = false;
            Card card;

            while (!terminateClient) {
                if (inputString.hasNextLine()) {
                    inputMessage = inputString.nextLine();

                    if (inputMessage.contains("client terminating")) { terminateClient = true; }

                    if (!terminateClient) { //if client is terminating, skip all of this

                        if (inputMessage.contains("deal")) { deal = true; }

                        if (deal){
                            totalClient = 0;
                            totalDealer = 0;
                            deck.shuffle();
                            card = deck.dealCard();
                            totalClient += card.getValue();
                            output.format("MSG: You got a " + card.toString() + "\n");
                            output.flush();


                            card = deck.dealCard();
                            totalDealer += card.getValue();
                            output.format("MSG: Dealer gets " + card.toString() + "\n");
                            output.flush();

                            card = deck.dealCard();
                            totalClient += card.getValue();
                            output.format("card: " + card.toString() + "\n");
                            output.flush();


                            card = deck.dealCard();
                            totalDealer += card.getValue();

                            deal = false;
                        }

                        if (inputMessage.contains("hit")) { //checks to see if client is terminating connection
                            if (totalClient < 21) {
                                card = deck.dealCard();
                                totalClient += card.getValue();
                                output.format("card: " + card.toString() + "\n");
                                output.flush();
                            }
                            if(totalClient > 21){
                                output.format("W/L: You busted! Dealer wins!\n");
                                output.flush();
                            }
                        }
                        else if (inputMessage.contains("stay")) { //checks to see if client is terminating connection
                            while (totalDealer < 16){
                                card = deck.dealCard();
                                totalDealer += card.getValue();
                            }

                            if (totalDealer > totalClient && totalDealer <= 21){
                                output.format("W/L: Dealer Wins with " + totalDealer + " against " + totalClient + "\n");
                                output.flush();
                            }
                            else if(totalDealer > 21) {
                                output.format("W/L: Client Wins, Dealer busted\n");
                                output.flush();
                            }
                            else{
                                output.format("W/L: Client Wins with " + totalClient + " against " + totalDealer + "\n");
                                output.flush();
                            }
                        }
                    }
                    else {
                        try {
                            //closes all connections/steams
                            output.close();
                            inputString.close();
                            connection.close();
                            System.out.println("connection to client closed");
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}

