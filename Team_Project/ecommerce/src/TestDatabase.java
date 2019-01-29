public class TestDatabase {
    public static void main(String[] args){
        boolean validUser = Database.checkUserAndPass("BenW", "Testing123");
        System.out.println(validUser);
        validUser = Database.checkUserAndPass("BenW", "Testing12");
        System.out.println(validUser);
        validUser = Database.checkUserAndPass("BenW", "Testing1234");
        System.out.println(validUser);

        int userID = Database.getUserID("benw");
        System.out.println(userID);
    }
}
