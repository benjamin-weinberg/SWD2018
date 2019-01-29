import java.sql.*;
import java.util.ArrayList;

public class Database {
    static final String DATABASE_URL = "jdbc:mysql://s-l112.engr.uiowa.edu:3306/engr_class041";
    static final String DATABASE_USERNAME = "engr_class041";
    static final String DATABASE_PASSWORD = "Team41";

    public static boolean checkUserAndPass (String username, String password){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        boolean userfound = false;

        try{
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

            statement = connection.createStatement();

            String SQL = "SELECT * FROM users WHERE name ='" + username + "' AND password='"+ password + "'";

            resultSet = statement.executeQuery(SQL);

            while(resultSet.next()){
                userfound = true;

            }
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        finally{
            try{
                resultSet.close();
                statement.close();
                connection.close();
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }
        return userfound;
    }


    public static int getUserID (String username){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        int userID = -1;

        try{
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            statement = connection.createStatement();
            String SQL = "SELECT * FROM users WHERE name ='" + username + "'";
            resultSet = statement.executeQuery(SQL);

            while(resultSet.next()){
                userID = (int) resultSet.getObject(1);
            }
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        finally{
            try{
                resultSet.close();
                statement.close();
                connection.close();
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }
        return userID;
    }

    public static ArrayList<Product> getProducts (){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        ArrayList<Product> products = new ArrayList<Product>();

        try{
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            statement = connection.createStatement();
            String SQL = "SELECT * FROM products";
            resultSet = statement.executeQuery(SQL);

            while(resultSet.next()){

                products.add(new Product(Integer.toString((int)resultSet.getObject(1)), (String)resultSet.getObject(2), Float.toString((Float) resultSet.getObject(3)),
                                (String)resultSet.getObject(4), Integer.toString((int)resultSet.getObject(5)), Integer.toString((int)resultSet.getObject(6)) ));
            }
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        finally{
            try{
                resultSet.close();
                statement.close();
                connection.close();
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }
        return products;
    }

    public static void buy(int productID){
        Connection connection = null;
        Statement statement = null;
        int productQuantity = Database.getQuantity(productID);
        String SQL;


        if(productQuantity <= 1) SQL = "DELETE FROM products WHERE ID ='" + productID + "'";
        else SQL = "UPDATE products SET quantity = " + (productQuantity-1) + " WHERE ID ='" + productID + "'";

        try{
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            statement = connection.createStatement();
            statement.executeUpdate(SQL);

        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        finally{
            try{
                statement.close();
                connection.close();
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }
    }

    public static void sell(Product productSell, String userID){
        Connection connection = null;
        Statement statement = null;

        try{
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            statement = connection.createStatement();
            String SQL = "INSERT INTO products (Name, Price, description, UserID, quantity) values ('" + productSell.getName() +"', " + Float.parseFloat(productSell.getPrice()) + ", '" + productSell.getDescription() + "', '" +
                            userID +"'," + Integer.parseInt(productSell.getQuantity()) + ")";
            statement.executeUpdate(SQL);
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        finally{
            try{
                statement.close();
                connection.close();
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }
    }

    public static boolean createAccount(String username, String password){
        Connection connection = null;
        Statement statement = null;

        boolean userExists;

        if(Database.getUserID(username) != -1){
            userExists = true;
        }
        else {
            try {
                connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                statement = connection.createStatement();
                String SQL = "INSERT INTO users (name , password) values ('" + username + "', '" + password + "')";
                statement.executeUpdate(SQL);
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            } finally {
                try {
                    statement.close();
                    connection.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            userExists = false;
        }
        return !userExists;
    }

    public static int getQuantity(int productID){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        int quantity = -1;

        try{
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            statement = connection.createStatement();
            String SQL = "SELECT quantity FROM products WHERE id =" + productID;
            resultSet = statement.executeQuery(SQL);

            while(resultSet.next()){
                quantity = (int)resultSet.getObject(1);
            }
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        finally{
            try{
                statement.close();
                connection.close();
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }
        return quantity;
    }

    public static void updateActivity(int userID){
        Connection connection = null;
        Statement statement = null;

        try{
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            statement = connection.createStatement();
            String SQL = "UPDATE users SET activity = activity + 1 WHERE ID = " + userID;
            statement.executeUpdate(SQL);

        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        finally{
            try{
                statement.close();
                connection.close();
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }
    }

    public static void updateLogs(int fromID, String action){
        Connection connection = null;
        Statement statement = null;

        try{
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            statement = connection.createStatement();
            String SQL = "INSERT INTO logs (Userid, action ) VALUES (" + fromID + ",'" + action + "')";
            statement.executeUpdate(SQL);

        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        finally{
            try{
                statement.close();
                connection.close();
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }
    }

}
