import java.io.EOFException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;

/**
 * The client will run and connect to the server, it will also provide the server with what the client wants the server to do
 */
public class Client{

    //Global variables to be used
    private Scanner input;
    private Formatter output;
    private String address;
    private Socket connection;
    private boolean stopOperations = false;

    Client(String address)
    {
        this.address = address;
    }


    /**
     * runs the client
     */
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

    /**
     * connects to the server through a socket
     * @throws IOException
     */
    //Connects to the server through a socket
    private void connectToServer() throws IOException
    {
        connection = new Socket(InetAddress.getByName(address), 23610);
    }

    /**
     * setup input and output streams to be used
     * @throws IOException
     */
    //Gets the input and output Streams to be used
    private void getStreams() throws IOException
    {
        output = new Formatter(connection.getOutputStream());
        output.flush();

        input = new Scanner(connection.getInputStream());
    }

    /**
     * Runs the main program as long as
     */
    //While loop that is constantly running so the client can always wait on what the server is saying
    private void runOperations()
    {
        output.format("deal\n");
        output.flush();
        while(!stopOperations)
        {
            if (input.hasNextLine())
            {
                determineMessage(input.nextLine());
            }
        }
    }

    /**
     * closing operations for the connection and sockets
     */
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

    /**
     * function that sends messages to the server depending on what is sent to the client
     * @param message
     */
    //Decode what the server says to it
    private void determineMessage(String message)
    {
        if(message.contains("card:"))
        {
            message = message.replace("card: ", "");
            System.out.println("You got a " + message);
            System.out.println("0: Hit");
            System.out.println("1: Stay");
            System.out.print("Enter the choice you would like to do (0 or 1): ");
            Scanner clientIn = new Scanner(System.in);
            int action = clientIn.nextInt();
            if (action == 0){
                output.format("hit\n");
                output.flush();
            }
            else if(action == 1){
                output.format("stay\n");
                output.flush();
            }
        }
        if(message.contains("MSG:"))
        {
            message = message.replace("MSG: ", "");
            System.out.println(message);
        }
        if(message.contains("W/L:"))
        {
            message = message.replace("W/L: ", "");
            System.out.println(message);
            System.out.println("Would you like to play again?");
            System.out.println("0: yes");
            System.out.println("1: no");
            System.out.print("Enter the choice you would like to do (0 or 1): ");
            Scanner clientIn = new Scanner(System.in);
            int action = clientIn.nextInt();
            if (action == 0){
                output.format("deal\n");
                output.flush();
            }
            else {
                stopOperations = true;
                output.format("client terminating\n");
                output.flush();
            }
        }
    }
}
