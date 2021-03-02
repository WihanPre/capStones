import java.util.*;
//Note If I understand this Capstone correctly this the only part of this that should work 100% is the "menu selection 1" -
//as we are not currently able to store information to update or amend according to menu selections 2-4. Am I correct?
//Creating new project calls and attributes
public class Project
{
    int projectNum;
    String projectName;
    String buildingType;
    String phAddress;
    int erfNum;
    Double totalFee;
    Double totalPaid;
    String deadLine;

    //Creating project Constructor for input to attributes
    public Project(int projectNum, String projectName, String buildingType,  String phAddress, int erfNum, Double totalFee, Double totalPaid,  String deadLine)
    {
        this.projectNum=projectNum;
        this.projectName=projectName;
        this.buildingType=buildingType;
        this.phAddress=phAddress;
        this.erfNum=erfNum;
        this.totalFee=totalFee;
        this.totalPaid=totalPaid;
        this.deadLine=deadLine;
    }
    //Creating Menu method for client interaction and if statements to exicute selections.
    public static void menu()
    {
        System.out.println("Please select from the Menu:\n " +
                "1) Enter New Project\n " +
                "2) Change Due Date of Project\n " +
                "3) Update Amount Paid\n " +
                "4) Finalise Project");
    }
    //Creating New Project Method to take in in user input
    public static void newProject()
    {
        Scanner proSIn = new Scanner(System.in);
        System.out.println("Please enter new Project Name: ");
        String projectName = proSIn.nextLine();
        System.out.println("Please enter the Building Type: ");
        String buildingType = proSIn.nextLine();
        System.out.println("Please enter the Physical Address: ");
        String phAddress = proSIn.nextLine();
        System.out.println("Please enter the Deadline: ");
        String deadLine = proSIn.nextLine();
        System.out.println("Please enter new Project Number: ");
        int projectNum = proSIn.nextInt();
        System.out.println("Please enter the Erf Number: ");
        int erfNum = proSIn.nextInt();
        System.out.println("Please enter the Total Fee: ");
        double totalFee = proSIn.nextDouble();
        System.out.println("Please enter the Total Amount Paid: ");
        double totalPaid = proSIn.nextDouble();
        Project project = new Project(projectNum, projectName, buildingType, phAddress, erfNum, totalFee, totalPaid, deadLine);
        System.out.println("\nNew Project confirmed as:");
        System.out.println(project.toString());
    }
    // Using "toString" method to display and confirm user input as part of New Project
    public String toString() {
        String output = "Project Number: " + projectNum;
        output += "\nProject Name: " + projectName;
        output += "\nBuilding Type: " + buildingType;
        output += "\nPhysical Address: " + phAddress;
        output += "\nErf Number: " + erfNum;
        output += "\nTotal Fee: R " + totalFee;
        output += "\nTotal Paid: R " + totalPaid;
        output += "\nDeadline: " + deadLine;
        return output;
    }
    //Change Due date Method (Only entering input as we can not yet save details)
    public static void newDueDate()
    {
        Scanner newDate = new Scanner(System.in);
        System.out.println("Please enter new Due Date: ");
        String inDueDate = newDate.next();
    }
    //Change Amount Paid (Only entering input as we can not yet save details)
    public static void updateAmountPaid()
    {
        Scanner newAmount = new Scanner(System.in);
        System.out.println("Please enter updated balance: ");
        double inPaid = newAmount.nextDouble();
    }
    //Change Amount Paid (Only entering input as we can not yet save details)
    public static void finaliseProject()
    {
        Scanner finalIn = new Scanner(System.in);
        System.out.println("Is the Project finalised (Yes/No): ");
        String newFinal = finalIn.next();
    }
//Running main to get user selection of method to exicute.
    public static void main ( String [] args )
    {
        Scanner userInt = new Scanner(System.in);
        menu();
        System.out.println("Please enter selection: ");
        int userIntIn = userInt.nextInt();
        if(userIntIn==1)
        {
            newProject();
        }
        if(userIntIn==2)
        {
            newDueDate();
        }
        if(userIntIn==3)
        {
            updateAmountPaid();
        }
        if(userIntIn==4)
        {
            finaliseProject();
        }

    }

}
