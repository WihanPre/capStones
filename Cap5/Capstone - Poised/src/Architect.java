import java.util.*;
//Note If I understand this Capstone correctly this the only part of this that should work 100% is the "menu selection 1" -
//as we are not currently able to store information to update or amend according to menu selection 2. Am I correct?
//Creating new project calls and attributes
public class Architect
{
    String arcName;
    String arcTelNum;
    String arcEmail;
    String arcAddress;
    int arcProjNum;

    //Creating project Constructor for input to attributes
    public Architect(String arcName, String arcTelNum, String arcEmail, String arcAddress, int arcProjNum)
    {
        this.arcName=arcName;
        this.arcTelNum=arcTelNum;
        this.arcEmail=arcEmail;
        this.arcAddress=arcAddress;
        this.arcProjNum=arcProjNum;
    }
    //Creating Architect Menu method for client interaction and if statements to execute selections.
    public static void arcMenu()
    {
        System.out.println("Please select from the Menu:\n " +
                "1) Enter New Architect\n " +
                "2) Update Architect\n ");
    }
    //Creating New architect Method to take in in user input
    public static void newArc()
    {
        Scanner userIn = new Scanner(System.in);
        System.out.println("Please enter new Architect Name: ");
        String arcName = userIn.nextLine();
        System.out.println("Please enter the Telephone Number: ");
        String arcTelNum = userIn.nextLine();
        System.out.println("Please enter the eMail Address: ");
        String arcEmail = userIn.nextLine();
        System.out.println("Please enter the Physical Address: ");
        String arcAddress = userIn.nextLine();
        System.out.println("Please enter the Project Number assigned to this Architect: ");
        int arcProjNum = userIn.nextInt();
        Architect architect = new Architect(arcName, arcTelNum, arcEmail, arcAddress, arcProjNum);
        System.out.println("\nNew Architect confirmed as:");
        System.out.println(architect.toString());
    }
    //Again using "toString" method to display and confirm user input as part of New Architect
    public String toString() {
        String output = "Architect Name: " + arcName;
        output += "\nTelephone Number: " + arcTelNum;
        output += "\neMail Address: " + arcEmail;
        output += "\nPhysical Address: " + arcAddress;
        output += "\nProject Number assigned: " + arcProjNum;
        return output;
    }
    //Update Architect Method (Only entering input as we can not yet save details)
    public static void updateArc()
    {
        Scanner newInfo = new Scanner(System.in);
        System.out.println("Please enter update info: ");
        String infoIn = newInfo.next();
    }
    //Running main to get user selection of method to execute and user inter action.
    public static void main ( String [] args )
    {
        Scanner userInt = new Scanner(System.in);
        arcMenu();
        System.out.println("Please enter selection: ");
        int userIntIn = userInt.nextInt();
        if(userIntIn==1)
        {
            newArc();
        }
        if(userIntIn==2)
        {
            updateArc();
        }
    }
}
