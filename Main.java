package project;
import java.util.Scanner;

/**
 * The Main class for the Fast Food Restaurant management system.
 */
public class Main {
	
	/**
     * The main method to start the Fast Food Restaurant management system.
     * 
     * @param args The command line arguments.
     */
  public static void main(String[] args) {
    HQ hq = new HQ();
    //Initialising
    String userDir = System.getProperty("user.dir");
    String branchListPath = userDir + "\\src\\project\\branch_list.txt";
    String menuListPath = userDir + "\\src\\project\\menu_list.txt";
    String staffListPath = userDir + "\\src\\project\\staff_list.txt";
    final Scanner sc = new Scanner(System.in);
    TXT_file_reader.importDataFromTxt(branchListPath);
    TXT_file_reader.importDataFromTxt(menuListPath);
    TXT_file_reader.importDataFromTxt(staffListPath);
    Payment pay = new Payment();
    HQ.addAdmin(new Admin("Boss","boss",Staff.Gender.FEMALE,62,"-",Staff.Role.ADMIN,"password"));
    Branch branch;
    while(true){
        int branchlist_len=HQ.getBranchList().size();
        System.out.println("\nWelcome to the Fast Food Restaurant!\n"
            + "\nAre you a customer, staff, manager or admin?\n"
            + "1. Customer\n"
            + "2. Staff\n"
            + "3. Manager\n"
            + "4. Admin\n"
            + "Enter your choice:");
          int role = sc.nextInt();
          String loginId, password;
          sc.nextLine();
          System.out.println();
            if(role == 1 || role == 2 || role == 3){
                System.out.println("Please select a branch:");
                HQ.displayBranch();
                System.out.println("Enter " + (branchlist_len + 1) + " to quit.");
                System.out.println("Enter the branch number: ");
                int branchChoice = sc.nextInt();
                sc.nextLine();
                System.out.println();
                if(branchChoice == branchlist_len+1){
                System.out.println("Thanks for using our service!");
                break;
                }
                branch = HQ.getBranchById(branchChoice - 1);
                if(branch == null) {
                    System.out.println("Invalid branch!\n");
                }
                if(role == 1){
                    CustomerInterface.handler(branch,sc);
                }
                else{
                    System.out.println("Please enter your loginId and password to login.");
                    System.out.println("Enter your loginId:");
                    loginId = sc.nextLine();
                    System.out.println("Enter password:");
                    password = sc.nextLine();
                    Staff staff = branch.login(loginId, password);
                    if(staff != null){
                        if(role == 2){
                            StaffInterface.handler(branch,staff,sc);
                        }
                        else ManagerInterface.handler(branch,staff,sc);
                    }
                    else System.out.println("Login failed!\n");
                }
            }
            else if(role == 4) {
                System.out.println("Please enter your loginId and password to login.");
                System.out.println("Enter your loginId:");
                loginId = sc.nextLine();
                System.out.println("Enter password:");
                password = sc.nextLine();
                Admin admin = HQ.login(loginId, password);
                if(admin!=null){
                    System.out.println("Login successful!\n");
                    AdminInterface.handler(admin,sc);
                }
                else System.out.println("Login failed!\n");
            }
            else{
                System.out.println("Invalid choice. Please enter a number between 0 and 4.\n");
            }
      }
      sc.close();
      System.exit(0); 
    }
}