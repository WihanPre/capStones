import java.io.*;
import java.sql.*;
import java.util.*;
/**This Architect Class serves as an point to build and maintain-
 * a data base of Architects.
 * Alll functions end in a return to this classes sub menu.
 **/

//Setting architect
public class Architect {
    //Initiating submenu and using if statements to call methods
    public static void arcMenu() {
        System.out.println("Please select from the Menu:\n " +
                "1) Enter New Architect\n " +
                "2) Search & Update Architect\n " +
                "3) Main Menu ");

        try {
            Scanner userIn = new Scanner(System.in);
            System.out.println("Please enter selection: ");
            int userIntIn = userIn.nextInt();
            if(userIntIn == 1){
                newArc();
            }
            if(userIntIn ==  2){
                searchUpdateArc();
            }
            if(userIntIn ==  3){
                PoiseApp.mainMenu();
            }
            if(userIntIn >= 4){
                System.out.println("Please enter a number value from the menu.");
                arcMenu();
            }
        }
        catch (Exception e){
            System.out.println("Please enter a numerical values only.");
            arcMenu();
        }
    }

    //Method to register new architects via a connection to the database to insert new details
    //using sql syntax to exicute
    public static void newArc() throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/PoisePMS?allowPublicKeyRetrieval=true&useSSL=false", "otheruser", "3574Wihan");
        Statement statement = connection.createStatement();
        ResultSet results;
        Scanner userIn = new Scanner(System.in);
        System.out.println("Please enter new Architect Name: ");
        String ARCHName = userIn.nextLine();
        System.out.println("Please enter the Telephone Number: ");
        String ARCHTelNum = userIn.nextLine();
        System.out.println("Please enter the eMail Address: ");
        String ARCHEmail = userIn.nextLine();
        System.out.println("Please enter the Physical Address: ");
        String ARCHAddress = userIn.nextLine();
        ARCHName = '"' + ARCHName + '"';
        ARCHTelNum = '"' + ARCHTelNum + '"';
        ARCHEmail = '"' + ARCHEmail + '"';
        ARCHAddress = '"' + ARCHAddress + '"';
        String strInsert = "insert into Poise_ARCH " + "values(" +ARCHName+ "," +ARCHTelNum+ "," +ARCHEmail+ "," +ARCHAddress+")";
        int countInserted = statement.executeUpdate(strInsert);

        statement.close();
        connection.close();
        arcMenu();
    }

    //Method to search for and update architects via a connection to the database to insert new details
    //and using while loop to display all information needed
    public static void searchUpdateArc() throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/PoisePMS?allowPublicKeyRetrieval=true&useSSL=false", "otheruser", "3574Wihan");
        Statement statement = connection.createStatement();
        Scanner userIn = new Scanner(System.in);
        System.out.println("Please enter the architect name: ");
        String userSearch = userIn.nextLine();
        userSearch = '"' + userSearch + '"';
        ResultSet results;
        int rowsAffected;
        results = statement.executeQuery("SELECT * FROM Poise_ARCH WHERE ARCHName = " +userSearch);
        System.out.print("Your search result is: \n");
        while (results.next()) {
            System.out.println("ARCHName : "+results.getString("ARCHName")
                    + ", ARCHTelNum : " + results.getString("ARCHTelNum")
                    + ", ARCHEmail : " + results.getString("ARCHEmail")
                    + ", ARCHAddress: " + results.getString("ARCHAddress"));
        }
        System.out.println("Please enter the parameter you want to Update as shown (ie ARCHName): ");
        String projUpdate = userIn.nextLine();
        System.out.println("Please enter the new details for the parameter: ");
        String projNewUpdate = userIn.nextLine();
        projNewUpdate = '"' + projNewUpdate + '"';
        String sqlUpdate = "update Poise_ARCH set " + projUpdate + "=" + projNewUpdate + "where ARCHName = " + userSearch;
        int countUpdated = statement.executeUpdate(sqlUpdate);
        results.close();
        statement.close();
        connection.close();
        System.out.println("Data updated.\n");
        arcMenu();
    }

    //Initiating menu
    public static void main (String [] args) {
        arcMenu();
    }
}
