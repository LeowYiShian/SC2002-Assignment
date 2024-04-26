package project;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Utility class to read data from text files and import into the Fast Food Restaurant management system.
 */
public class TXT_file_reader {
	
	/**
     * Imports data from a specified text file and updates the system accordingly.
     *
     * @param filename The path of the text file to read.
     */
    public static void importDataFromTxt(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split("\\|");
                if (filename.equals("C:\\Users\\Testing\\Desktop\\ntu\\sc2002\\assignment\\src\\project\\branch_list.txt")) {
                    String name = data[0];
                    String location = data[1];
                    int staffQuota = Integer.parseInt(data[2]);
                    HQ.addBranch(name, location, staffQuota);
                }else if (filename.equals("C:\\Users\\Testing\\Desktop\\ntu\\sc2002\\assignment\\src\\project\\menu_list.txt")) {
                    String name = data[0];
                    double price = Double.parseDouble(data[1]);
                    String branchName = data[2];
                    String category = data[3];
                    MenuItem.Category itemCategory = MenuItem.Category.valueOf(category);
                    boolean available = true;
                    Branch branch = HQ.getBranch(branchName);
                    if (branch != null) {
                        branch.getMenu().addItem(name, price, branchName, "-", itemCategory, available);
                    }
                }
                else if (filename.equals("C:\\Users\\Testing\\Desktop\\ntu\\sc2002\\assignment\\src\\project\\staff_list.txt")) {
                    String staffName = data[0];
                    String loginID = data[1];
                    Staff.Role role = mapRole(data[2]);
                    Staff.Gender gender = mapGender(data[3]);
                    int age = Integer.parseInt(data[4]);
                    String branchName = data[5];
                    String password = "password";
                    Branch branch = HQ.getBranch(branchName);
                    if (branch != null) {
                        if (role == Staff.Role.ADMIN) {
                            Admin admin = new Admin(staffName, loginID, gender, age, branchName, role, password);
                            HQ.addAdmin(admin);
                        } else {
                            Staff staff = new Staff(staffName, loginID, gender, age, branchName, role, password);
                            branch.getStaffList().addStaff(staff);
                            HQ.getAllStaffList().addStaff(staff);
                        }
                    }
                }
                else if (filename.equals("C:\\Users\\Testing\\Desktop\\ntu\\sc2002\\assignment\\src\\project\\menu_list.txt")) {
                    String name = data[0];
                    double price = Double.parseDouble(data[1]);
                    String branchName = data[2];
                    String category = data[3];
                    MenuItem.Category itemCategory = MenuItem.Category.valueOf(category);
                    boolean available = true;
                    Branch branch = HQ.getBranch(branchName);
                    if (branch != null) {
                        branch.getMenu().addItem(name, price, branchName, "-", itemCategory, available);
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    /**
     * Maps a role string to its corresponding Role enum value.
     *
     * @param roleString The role string to map.
     * @return The Role enum value.
     * @throws IllegalArgumentException If the role string is invalid.
     */
    private static Staff.Role mapRole(String roleString) {
        switch (roleString) {
            case "S":
                return Staff.Role.STAFF;
            case "M":
                return Staff.Role.MANAGER;
            case "A":
                return Staff.Role.ADMIN;
            default:
                throw new IllegalArgumentException("Invalid role: " + roleString);
        }
    }

    /**
     * Maps a gender string to its corresponding Gender enum value.
     *
     * @param genderString The gender string to map.
     * @return The Gender enum value.
     * @throws IllegalArgumentException If the gender string is invalid.
     */
    private static Staff.Gender mapGender(String genderString) {
        switch (genderString) {
            case "M":
                return Staff.Gender.MALE;
            case "F":
                return Staff.Gender.FEMALE;
            default:
                throw new IllegalArgumentException("Invalid gender: " + genderString);
        }
    }
}
