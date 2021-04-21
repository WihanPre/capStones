//Required importss
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.Date;

/**The bulk of the prorgam funtions are in the Project Class as this is to -
 * "run, build and search" for info regarding the projects.
 * Alll functions end in a return to this classes sub menu.
 **/

//Setting project
public class Project {
    //Initiating submenu and using if statements to call methods
    public static void projectMenu() {
        System.out.println("""
                Please select from the Menu:
                 1) Enter New Project
                 2) Search & Update Projects
                 3) List of running Projects
                 4) Projects Over Due
                 5) Finalise Project
                 6) Main Menu""");
        try {
            Scanner userIn = new Scanner(System.in);
            System.out.println("Please enter selection: ");
            int userIntIn = userIn.nextInt();
            if (userIntIn == 1){
                newProject();
            }
            if (userIntIn == 2){
                searchUpdate();
            }
            if (userIntIn == 3){
                listUncompleted();
            }
            if (userIntIn == 4){
                projectsOverDue();
            }
            if (userIntIn == 5){
                finaliseProject();
            }
            if (userIntIn == 6){
                PoiseApp.mainMenu();
            }
            if (userIntIn >= 7){
                System.out.println("Please enter a number value from the menu.");
                projectMenu();
            }
        }
        catch (Exception e){
            System.out.println("Please enter numerical values only.");
            projectMenu();
        }
    }

    //Method to register new projects via a connection to the database to insert new details
    //using sql syntax to exicute
    public static void newProject() throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/PoisePMS?allowPublicKeyRetrieval=true&useSSL=false", "otheruser", "3574Wihan");
        Statement statement = connection.createStatement();
        ResultSet results;
        results = statement.executeQuery("SELECT * FROM Poise_Project");
        Scanner proSIn = new Scanner(System.in);
        System.out.println("Please enter new Project Name: ");
        String projectName = proSIn.nextLine();
        System.out.println("Please enter the Building Type: ");
        String buildingType = proSIn.nextLine();
        System.out.println("Please enter the Physical Address: ");
        String phAddress = proSIn.nextLine();
        System.out.println("Please enter the Deadline: ");
        String deadLine = proSIn.nextLine();
        System.out.println("Please enter Architect Name: ");
        String archName = proSIn.nextLine();
        System.out.println("Please enter Client Name: ");
        String clntName = proSIn.nextLine();
        System.out.println("Please enter Structural Engineer Name: ");
        String SEName = proSIn.nextLine();
        System.out.println("Please enter Project Manager Name: ");
        String PMName = proSIn.nextLine();
        System.out.println("Please enter new Project Number: ");
        int projectNum = proSIn.nextInt();
        System.out.println("Please enter the Erf Number: ");
        int erfNum = proSIn.nextInt();
        System.out.println("Please enter the Total Fee: ");
        double totalFee = proSIn.nextDouble();
        System.out.println("Please enter the Total Amount Paid: ");
        double totalPaid = proSIn.nextDouble();
        if(projectName == ""){
            projectName = clntName +" "+ buildingType;
        }
        projectName = '"' + projectName + '"';
        buildingType = '"' + buildingType + '"';
        phAddress = '"' + phAddress + '"';
        deadLine = '"' + deadLine + '"';
        archName = '"' + archName + '"';
        clntName = '"' + clntName + '"';
        SEName = '"' + SEName + '"';
        PMName = '"' + PMName + '"';
        String strInsert = "insert into Poise_Project " + "values(" +projectNum+ "," +projectName+ "," +buildingType+ "," +phAddress+"," +erfNum+"," +totalFee+"," +totalPaid+"," +deadLine+"," +archName+"," +clntName+"," +PMName+"," +SEName+")";
        int countInserted = statement.executeUpdate(strInsert);
        results.close();
        statement.close();
        connection.close();
        projectMenu();
    }

    //Method to search for and update projects via a connection to the database to insert new details
    //and using while loop to display all information needed
    public static void searchUpdate() throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/PoisePMS?allowPublicKeyRetrieval=true&useSSL=false", "otheruser", "3574Wihan");
        Statement statement = connection.createStatement();
        Scanner userIn = new Scanner(System.in);
        System.out.println("Please enter project number: ");
        Integer userSearch = Integer.valueOf(userIn.nextLine());
        ResultSet results;
        int rowsAffected;
        results = statement.executeQuery("SELECT * FROM Poise_Project WHERE projectNum = " +userSearch);
        System.out.print("Your search result is: \n");
        while (results.next()) {
            System.out.println("projectNum: "+results.getInt("projectNum")
                    + ", projectName: " + results.getString("projectName")
                    + ", buildingType: " + results.getString("buildingType")
                    + ", phAddress: " + results.getString("phAddress")
                    + ", erfNum: " + results.getInt("erfNum")
                    + ", totalFee: " + results.getFloat("totalFee")
                    + ", totalPaid: " + results.getFloat("totalPaid")
                    + ", deadLine: " + results.getDate("deadLine")
                    + ", assignedArch: " + results.getString("assignedArch")
                    + ", assignedClnt: " + results.getString("assignedClnt")
                    + ", assignedPM: " + results.getString("assignedPM")
                    + ", assignedSE: " + results.getString("assignedSE"));
        }
        System.out.println("Please enter the parameter you want to Update as shown (ie projectName): ");
        String projUpdate = userIn.nextLine();
        System.out.println("Please enter the new details for the parameter: ");
        String projNewUpdate = userIn.nextLine();
        projNewUpdate = '"' + projNewUpdate + '"';

        String sqlUpdate = "update Poise_Project set " + projUpdate + "=" + projNewUpdate + "where projectNum = " + userSearch;
        int countUpdated = statement.executeUpdate(sqlUpdate);
        results.close();
        statement.close();
        connection.close();
        System.out.println("Data updated.\n");
        projectMenu();
    }

    //Method to give a list of ongoing projects not finalised in the database
    //and using while loop to display all information needed
    public static void listUncompleted() throws SQLException {
        Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/PoisePMS?allowPublicKeyRetrieval=true&useSSL=false", "otheruser", "3574Wihan");
        Statement statement = connection.createStatement();
        ResultSet results;
        int rowsAffected;
        results = statement.executeQuery("SELECT * FROM Poise_Project");
        System.out.println("Your requested list is: \n");
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
        System.out.print("\n");
        projectMenu();
    }

    //Method for user to see a list of projects that is overdue.
    //and using while loop to display all information needed
    public static void projectsOverDue() throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/PoisePMS?allowPublicKeyRetrieval=true&useSSL=false", "otheruser", "3574Wihan");
        Statement statement = connection.createStatement();
        ResultSet results;
        int rowsAffected;
        System.out.println("Your requested list is: \n");
        results = statement.executeQuery("SELECT * FROM Poise_Project WHERE deadLine < NOW()");
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
        projectMenu();
    }

    //Method to finalise projects with the added functions of displaying an invoice and list the project as finalised -
    //under the buildingType column in the database.
    //and using while loops to display all information needed
    public static void finaliseProject() throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/PoisePMS?allowPublicKeyRetrieval=true&useSSL=false", "otheruser", "3574Wihan");
        Statement statement = connection.createStatement();
        Scanner userIn = new Scanner(System.in);
        System.out.println("Please enter project number: ");
        Integer userSearch = Integer.valueOf(userIn.nextLine());
        ResultSet results;
        int rowsAffected;
        results = statement.executeQuery("SELECT * FROM Poise_Project WHERE projectNum = " + userSearch);
        System.out.print("Your search result is: \n");
        while (results.next()) {
            System.out.println("projectNum: " + results.getInt("projectNum")
                    + ", projectName: " + results.getString("projectName")
                    + ", buildingType: " + results.getString("buildingType")
                    + ", phAddress: " + results.getString("phAddress")
                    + ", erfNum: " + results.getInt("erfNum")
                    + ", totalFee: " + results.getFloat("totalFee")
                    + ", totalPaid: " + results.getFloat("totalPaid")
                    + ", deadLine: " + results.getDate("deadLine")
                    + ", assignedArch: " + results.getString("assignedArch")
                    + ", assignedClnt: " + results.getString("assignedClnt")
                    + ", assignedPM: " + results.getString("assignedPM")
                    + ", assignedSE: " + results.getString("assignedSE"));
        }
        try {
            System.out.println("\n");
            System.out.println("To finalise project enter Yes, or No to proceed to menu.");
            String cont = userIn.nextLine();
            if (cont.equals("Yes")) {
                String finalised = "**Finalised**";
                String sqlUpdate = "UPDATE Poise_Project SET buildingType = '"+finalised+"' where projectNum = " + userSearch;
                try {
                    int countUpdated = statement.executeUpdate(sqlUpdate);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            System.out.println("Project "+userSearch+" has been marked as Finalised under the buildingType column.\n");
            results = statement.executeQuery("SELECT totalFee, totalPaid, assignedClnt, projectName, phAddress FROM Poise_Project WHERE projectNum = " + userSearch);
            while (results.next()) {
                System.out.println("Invoice Total: R" + results.getFloat("totalFee"));//control
                System.out.println("Balance Paid Total: R" + results.getFloat("totalPaid"));//control
                float balance1 = results.getFloat("totalFee"); //control
                float balance2 = results.getFloat("totalPaid"); //control
                System.out.println(balance1);System.out.println(balance2); //Contaol
                if (balance1!=0){
                    System.out.println("Generated Invoice for Project: "+userSearch+"\n");
                    System.out.println("Client Name: " + results.getString("assignedClnt"));
                    System.out.println("Project Name: " + results.getString("projectName"));
                    System.out.println("Physical Address: " + results.getString("phAddress"));
                    float finalTotal = balance1-balance2;
                    System.out.println("Balance to be settled: R "+finalTotal);
                }
                else {
                    System.out.println("No amount due.");
                }
            }
            results.close();
            statement.close();
            connection.close();
            projectMenu();
    }
        catch (Exception e) {
            e.printStackTrace();
            projectMenu();
        }
    }

    //Initiating menu
    public static void main (String [] args) {
        projectMenu();
    }
}
