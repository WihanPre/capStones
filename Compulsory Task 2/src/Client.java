//Required imports
import java.sql.*;
import java.util.*;
/**This Client Class serves as an point to build and maintain-
 * a data base of Clients.
 * Alll functions end in a return to this classes sub menu.
 **/

//Setting client
public class Client {
    //Initiating submenu and using if statements to call methods
    public static void clntMenu() {
        System.out.println("Please select from the Menu:\n " +
                "1) Enter New Client\n " +
                "2) Search & Update Client\n " +
                "3) Main Menu ");
        try {
            Scanner userIn = new Scanner(System.in);
            System.out.println("Please enter selection: ");
            int userIntIn = userIn.nextInt();
            if (userIntIn == 1) {
                newClnt();
            }
            if (userIntIn == 2) {
                searchUpdateClnt();
            }
            if (userIntIn == 3) {
                PoiseApp.mainMenu();
            }
            if (userIntIn >= 4) {
                System.out.println("Please enter a number value from the menu.");
                clntMenu();
            }
        } catch (Exception e) {
            System.out.println("Please enter a numerical values only.");
            clntMenu();
        }
    }

    //Method to register new clients via a connection to the database to insert new details
    //using sql syntax to exicute
    public static void newClnt() throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/PoisePMS?allowPublicKeyRetrieval=true&useSSL=false", "otheruser", "3574Wihan");
        Statement statement = connection.createStatement();
        ResultSet results;
        Scanner userIn = new Scanner(System.in);
        System.out.println("Please enter new Client Name: ");
        String clntName = userIn.nextLine();
        System.out.println("Please enter the Telephone Number: ");
        String clntTelNum = userIn.nextLine();
        System.out.println("Please enter the eMail Address: ");
        String clntEmail = userIn.nextLine();
        System.out.println("Please enter the Physical Address: ");
        String clntAddress = userIn.nextLine();
        clntName = '"' + clntName + '"';
        clntTelNum = '"' + clntTelNum + '"';
        clntEmail = '"' + clntEmail + '"';
        clntAddress = '"' +clntAddress + '"';
        String strInsert = "insert into Poise_CLNT " + "values(" +clntName+ "," +clntTelNum+ "," +clntEmail+ "," +clntAddress+")";
        int countInserted = statement.executeUpdate(strInsert);

        statement.close();
        connection.close();
        clntMenu();
    }

    //Method to search for and update clients via a connection to the database to insert new details
    //and using while loop to display all information needed
    public static void searchUpdateClnt() throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/PoisePMS?allowPublicKeyRetrieval=true&useSSL=false", "otheruser", "3574Wihan");
        Statement statement = connection.createStatement();
        Scanner userIn = new Scanner(System.in);
        System.out.println("Please enter the client name: ");
        String userSearch = userIn.nextLine();
        userSearch = '"' + userSearch + '"';
        ResultSet results;
        int rowsAffected;
        results = statement.executeQuery("SELECT * FROM Poise_CLNT WHERE CLNTName = " +userSearch);
        System.out.print("Your search result is: \n");
        while (results.next()) {
            System.out.println("CLNTName : "+results.getString("CLNTName")
                    + ", CLNTTelNum : " + results.getString("CLNTTelNum")
                    + ", CLNTEmail : " + results.getString("CLNTEmail")
                    + ", CLNTAddress: " + results.getString("CLNTAddress"));
        }
        System.out.println("Please enter the parameter you want to Update as shown (ie CLNTName): ");
        String projUpdate = userIn.nextLine();
        System.out.println("Please enter the new details for the parameter: ");
        String projNewUpdate = userIn.nextLine();
        projNewUpdate = '"' + projNewUpdate + '"';
        String sqlUpdate = "update Poise_CLNT set " + projUpdate + "=" + projNewUpdate + "where CLNTName = " + userSearch;
        int countUpdated = statement.executeUpdate(sqlUpdate);
        results.close();
        statement.close();
        connection.close();
        System.out.println("Data updated.\n");
        clntMenu();
    }

    //Initiating menu
    public static void main (String [] args) {
        clntMenu();
    }
}
