//Required imports
import java.sql.*;
import java.util.*;
/**This Project Manager Class serves as an point to build and maintain-
 * a data base of Project Managers.
 * Alll functions end in a return to this classes sub menu.
 **/

//setting projectmanager
public class ProjectMananger {
    //Initiating submenu and using if statements to call methods
    public static void pmMenu() {
        System.out.println("Please select from the Menu:\n " +
                "1) Enter New Project Manager\n " +
                "2) Search & Project Manager\n " +
                "3) Main Menu ");
        try {
            Scanner userIn = new Scanner(System.in);
            System.out.println("Please enter selection: ");
            int userIntIn = userIn.nextInt();
            if(userIntIn == 1){
                newPM();
            }
            if(userIntIn ==  2){
                searchUpdatePM();
            }
            if(userIntIn ==  3){
                PoiseApp.mainMenu();
            }
            if(userIntIn >= 4){
                System.out.println("Please enter a number value from the menu.");
                pmMenu();
            }
        }
        catch (Exception e){
            System.out.println("Please enter a numerical values only.");
            pmMenu();
        }
    }

    //Method to register new project manager via a connection to the database to insert new details
    //using sql syntax to exicute
    public static void newPM() throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/PoisePMS?allowPublicKeyRetrieval=true&useSSL=false", "otheruser", "3574Wihan");
        Statement statement = connection.createStatement();
        ResultSet results;
        Scanner userIn = new Scanner(System.in);
        System.out.println("Please enter new Project Manager Name: ");
        String PMName = userIn.nextLine();
        System.out.println("Please enter the Telephone Number: ");
        String PMTelNum = userIn.nextLine();
        System.out.println("Please enter the eMail Address: ");
        String PMEmail = userIn.nextLine();
        System.out.println("Please enter the Physical Address: ");
        String PMAddress = userIn.nextLine();
        PMName = '"' + PMName + '"';
        PMTelNum = '"' + PMTelNum + '"';
        PMEmail = '"' + PMEmail + '"';
        PMAddress = '"' + PMAddress + '"';
        String strInsert = "insert into Poise_PM " + "values(" +PMName+ "," +PMTelNum+ "," +PMEmail+ "," +PMAddress+")";
        int countInserted = statement.executeUpdate(strInsert);
        statement.close();
        connection.close();
        pmMenu();
    }

    //Method to search for and update project manager via a connection to the database to insert new details
    //and using while loop to display all informaiton needed
    public static void searchUpdatePM() throws SQLException{
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/PoisePMS?allowPublicKeyRetrieval=true&useSSL=false", "otheruser", "3574Wihan");
        Statement statement = connection.createStatement();
        Scanner userIn = new Scanner(System.in);
        System.out.println("Please enter the project manager name: ");
        String userSearch = userIn.nextLine();
        userSearch = '"' + userSearch + '"';
        ResultSet results;
        int rowsAffected;
        results = statement.executeQuery("SELECT * FROM Poise_PM WHERE PMName = " +userSearch);
        System.out.print("Your search result is: \n");
        while (results.next()) {
            System.out.println("PMName : "+results.getString("PMName")
                    + ", PMTelNum : " + results.getString("PMTelNum")
                    + ", PMEmail : " + results.getString("PMEmail")
                    + ", PMAddress: " + results.getString("PMAddress"));
        }
        System.out.println("Please enter the parameter you want to Update as shown (ie PMName): ");
        String projUpdate = userIn.nextLine();
        System.out.println("Please enter the new details for the parameter: ");
        String projNewUpdate = userIn.nextLine();
        projNewUpdate = '"' + projNewUpdate + '"';
        String sqlUpdate = "update Poise_PM set " + projUpdate + "=" + projNewUpdate + "where PMName = " + userSearch;
        int countUpdated = statement.executeUpdate(sqlUpdate);
        results.close();
        statement.close();
        connection.close();
        System.out.println("Data updated.\n");
        pmMenu();
    }

    //Initiating submenu
    public static void main (String [] args) {
        pmMenu();
    }
}

