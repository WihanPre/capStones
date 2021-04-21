//Required imports
import java.sql.*;
import java.util.*;
/**This Structural Engineers Class serves as an point to build and maintain-
 * a data base of Structural Engineers .
 * Alll functions end in a return to this classes sub menu.
 **/

//Setting contractor
public class StructuralEngineer {
    //Method to register new structural engineers via a connection to the database to insert new details
    public static void conMenu() {
        System.out.println("Please select from the Menu:\n " +
                "1) Enter New Contractor\n " +
                "2) Search & Update Contractor\n " +
                "3) Main Menu ");
        try {
            Scanner userIn = new Scanner(System.in);
            System.out.println("Please enter selection: ");
            int userIntIn = userIn.nextInt();
            if(userIntIn == 1){
                newCon();
            }
            if(userIntIn ==  2){
                searchUpdateCon();
            }
            if(userIntIn ==  3){
                PoiseApp.mainMenu();
            }
            if(userIntIn >= 4){
                System.out.println("Please enter a number value from the menu.");
                conMenu();
            }
        }
        catch (Exception e){
            System.out.println("Please enter a numerical values only.");
            conMenu();
        }
    }

    //Method to search for and update project managers via a connection to the database to insert new details
    //using sql syntax to exicute
    public static void newCon() throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/PoisePMS?allowPublicKeyRetrieval=true&useSSL=false", "otheruser", "3574Wihan");
        Statement statement = connection.createStatement();
        ResultSet results;
        Scanner userIn = new Scanner(System.in);
        System.out.println("Please enter new Structural Engineer Name: ");
        String SEName = userIn.nextLine();
        System.out.println("Please enter the Telephone Number: ");
        String SETelNum = userIn.nextLine();
        System.out.println("Please enter the eMail Address: ");
        String SEEmail = userIn.nextLine();
        System.out.println("Please enter the Physical Address: ");
        String SEAddress = userIn.nextLine();
        SEName = '"' + SEName + '"';
        SETelNum = '"' + SETelNum + '"';
        SEEmail = '"' + SEEmail + '"';
        SEAddress = '"' + SEAddress + '"';
        String strInsert = "insert into Poise_SE " + "values(" +SEName+ "," +SETelNum+ "," +SEEmail+ "," +SEAddress+")";
        int countInserted = statement.executeUpdate(strInsert);
        statement.close();
        connection.close();
        conMenu();
    }

    //Method to search for and update structural engineers via a connection to the database to insert new details
    //and using while loop to display all information needed
    public static void searchUpdateCon() throws SQLException{
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/PoisePMS?allowPublicKeyRetrieval=true&useSSL=false", "otheruser", "3574Wihan");
        Statement statement = connection.createStatement();
        Scanner userIn = new Scanner(System.in);
        System.out.println("Please enter the structural engineer name: ");
        String userSearch = userIn.nextLine();
        userSearch = '"' + userSearch + '"';
        ResultSet results;
        int rowsAffected;
        results = statement.executeQuery("SELECT * FROM Poise_SE WHERE SEName = " +userSearch);
        System.out.print("Your search result is: \n");
        while (results.next()) {
            System.out.println("SEName : "+results.getString("SEName")
                    + ", SETelNum : " + results.getString("SETelNum")
                    + ", SEEmail : " + results.getString("SEEmail")
                    + ", SEAddress: " + results.getString("SEAddress"));
        }
        System.out.println("Please enter the parameter you want to Update as shown (ie SEName): ");
        String projUpdate = userIn.nextLine();
        System.out.println("Please enter the new details for the parameter: ");
        String projNewUpdate = userIn.nextLine();
        projNewUpdate = '"' + projNewUpdate + '"';
        String sqlUpdate = "update Poise_SE set " + projUpdate + "=" + projNewUpdate + "where SEName = " + userSearch;
        int countUpdated = statement.executeUpdate(sqlUpdate);
        results.close();
        statement.close();
        connection.close();
        System.out.println("Data updated.\n");
            conMenu();
    }

    //Initiating menu
    public static void main (String [] args) {
        conMenu();
    }
}
