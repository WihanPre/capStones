//Doing all required imports
import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Thus is a project management software system to keep track of building projects working from MySQL database
 * with function like storing data of the projects and build a data base of Clients, Contractor, Architects and Project Managers
 * @author Wihan Pretorius
 * @version 1.01
 */

// This is the initiation menu (main menu) with if statements to call submenus and as always, greeting the user.
public class PoiseApp {
    public static void mainMenu() {
        System.out.println("""
                Please select from the Menu:
                 1) Projects
                 2) Architects
                 3) Structural Engineers
                 4) Clients
                 5) Project Managers""");
        try {
            Scanner userIn = new Scanner(System.in);
            System.out.println("Please enter selection: ");
            int userMainIn = userIn.nextInt();
            if (userMainIn == 1) {
                Project.projectMenu();
            }
            if (userMainIn == 2) {
                Architect.arcMenu();
            }
            if (userMainIn == 3) {
                StructuralEngineer.conMenu();
            }
            if (userMainIn == 4) {
                Client.clntMenu();
            }
            if (userMainIn >= 5) {
                ProjectMananger.pmMenu();
            }
            if (userMainIn >= 6) {
                System.out.println("Please enter a number value from the menu.");
                mainMenu();
            }
        } catch (Exception e) {
            System.out.println("Please enter a numerical values only.");
            mainMenu();
        }

    }

    //Main is running a greeting message with todays date and all current projects in the database.
    public static void main(String [] args) throws ClassNotFoundException {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yy");
            LocalDateTime now = LocalDateTime.now();
            String dateNow = dtf.format(now);
            System.out.println("\nGood day user, today's date is: " + dateNow + "\n");
            System.out.println("The projects in your database is: \n");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/PoisePMS?allowPublicKeyRetrieval=true&useSSL=false", "otheruser", "3574Wihan");
            Statement statement = connection.createStatement();
            ResultSet results;
            int rowsAffected;
            results = statement.executeQuery("SELECT * FROM Poise_Project");
            while (results.next()) {
                System.out.println(results.getInt("projectNum")
                        + ", " + results.getString("projectName")
                        + ", " + results.getString("buildingType")
                        + ", " + results.getString("phAddress")
                        + ", " + results.getInt("erfNum")
                        + ", " + results.getFloat("totalFee")
                        + ", " + results.getFloat("totalPaid")
                        + ", " + results.getDate("deadLine")
                        + ", " + results.getString("assignedArch")
                        + ", " + results.getString("assignedClnt")
                        + ", " + results.getString("assignedPM")
                        + ", " + results.getString("assignedSE"));
            }
            results.close();
            statement.close();
            connection.close();
            System.out.println("\n");
            mainMenu();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}