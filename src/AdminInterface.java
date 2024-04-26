package project;
import java.util.Scanner;

/**
 * The AdminInterface class provides an interface for administrators to perform various tasks such as
 * managing branches, checking staff details, and managing payments.
 */
public class AdminInterface {
	
	/**
     * Handles the user interaction for an admin.
     *
     * @param admin The admin object logged in.
     * @param sc    The Scanner object for user input.
     */
  public static void handler(Admin admin, Scanner sc) {
    System.out.println("\nWelcome, " + admin.getName() + "!");
    boolean exit = false;
    while (!exit) {
      System.out.println("\n1. Open Branch\n" +
                         "2. Close Branch\n" +
                         "3. Display Branch\n" +
                         "4. Manage Branch\n" +
                         "5. Display All Staff\n" +
                         "6. Filter Staff\n" +
                         "7. Edit Payment\n" +
                         "0. Back\n" +
                         "Enter your choice:");
      int choice = sc.nextInt();
      sc.nextLine();
      switch (choice) {
        case 1:
          System.out.println("Enter Branch Name:");
          String branchName = sc.nextLine();
          System.out.println("Enter Location:");
          String location = sc.nextLine();
          System.out.println("Enter Staff Quota:");
          int staffQuota = sc.nextInt();
          HQ.addBranch(branchName, location, staffQuota);
          break;
        case 2:
          HQ.displayBranch();
          System.out.println("Enter the branch name to be closed:");
          String a = sc.nextLine();
          HQ.closeBranch(a);
          break;
        case 3:
          HQ.displayBranch();
          break;
        case 4:
        System.out.println("Enter the branch name to be closed:");
        String b = sc.nextLine();
        Branch branch = HQ.getBranch(b);
          if(branch == null) {
            System.out.println("Branch not found.\n");
          }
          else{
            manageBranch(branch,sc);
          }
          break;
        case 5:
          StaffList staffList = HQ.getAllStaffList();
          HQ.sortStaff();
          staffList.displayStaffList();
          break;
        case 6:
          Admin.filter(sc);
          break;
        case 7:
          Admin.editPayment(sc);
          break;
        case 0:
          System.out.println("Logging out. Goodbye!\n");
          exit = true;
          break;
        default:
          System.out.println("Invalid choice. Please enter a number between 0 and 7.\n");
      }
    }
  }
  
  /**
   * Manages the operations related to a specific branch, such as displaying staff, promoting staff,
   * transferring staff, and editing staff accounts.
   *
   * @param branch The branch to be managed.
   * @param sc     The Scanner object for user input.
   */
  private static void manageBranch(Branch branch, Scanner sc){
    boolean exit = false;
      while (!exit) {
        System.out.println("\nManaging Branch:\n" +
                           "1. Display Staff List\n" +
                           "2. Promote Staff to Branch Manager\n" +
                           "3. Transfer Staff among Branches\n" +
                           "4. Edit Staff Accounts\n" +
                           "0. Back\n" +
                           "Enter your choice:");
          int choice = sc.nextInt();
          sc.nextLine();
          switch (choice) {
              case 1:
                  StaffList staffList = branch.getStaffList();
                  System.out.println("Number of staff: "+staffList.getNumOfStaff());
                  System.out.println("Staff quota: "+staffList.getStaffQuota());
                  staffList.displayStaffList();
                  break;
              case 2:
                  System.out.println("Enter Staff to be promoted");
                  String StaffToBePromoted = sc.nextLine();
                  Admin.promoteToBranchManager(StaffToBePromoted,branch);
                  break;
              case 3:
                  System.out.println("Enter Source Branch");
                  String SourceBranch = sc.nextLine();
                  System.out.println("Enter Staff to be transferred");
                  String StaffToBeTransferred = sc.nextLine();
                  System.out.println("Enter Destination Branch");
                  String DestinationBranch = sc.nextLine();
                  Admin.transferStaff(StaffToBeTransferred, SourceBranch, DestinationBranch);
                  break;
              case 4:
                  Admin.editStaffAccount(branch,sc);
                  break;
              case 0:
                  exit = true;
                  break;
              default:
                  System.out.println("Invalid choice. Please enter a number between 0 and 4.\n");
          }
      }
  }
}