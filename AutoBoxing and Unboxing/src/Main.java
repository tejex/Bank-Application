import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Bank bank =  new Bank();

    public static void main(String[] args) {
        boolean quit  = false;
        int choice = 0;
        printInstructions();
        while(!quit) {
            System.out.println("Enter Your Choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0 -> printInstructions();
                case 1 -> newBranch();
                case 2 -> printBranches();
                case 3 -> newCustomer();
                case 4 -> customerOprations();
                case 5 -> viewCustomerTransactions();
                case 6 -> quit = true;
            }
        }
    }
    public  static void  printInstructions(){
        System.out.println("\n Press ");
        System.out.println("\t 0- Print the choice of options");
        System.out.println("\t 1- New Branch");
        System.out.println("\t 2- Print available branches");
        System.out.println("\t 3- New customer");
        System.out.println("\t 4- Customer Operations");
        System.out.println("\t 5- Print customer transactions");
        System.out.println("\t 6- Quit application");
    }
    public static void newBranch(){
        System.out.println("Enter name for new branch: ");
        String newBranchName = scanner.nextLine();
        bank.createNewBranch(newBranchName);
    }
    public static void printBranches(){
        bank.printBranches();
    }
    public  static void newCustomer(){
        System.out.println("Welcome to this bank");
        System.out.println("What is your name");
        String customerName = scanner.nextLine();

        System.out.println("Which branch would you like to join");
        printBranches();
        String preferredBranch = scanner.nextLine();
        boolean proceed = bank.branchExists(preferredBranch);
        if(proceed){
            System.out.println("Please enter an initial deposit above $50.00, in this format $XY.00: ");
            double initial = scanner.nextDouble();
            bank.addCustomerToBranch(preferredBranch,customerName,initial);
        }
        else{
            System.out.println("Branch does not exist");
        }
    }
    public static void customerOprations(){
        System.out.println("Please enter the name of your branch");
        String branchName = scanner.nextLine();
        boolean proceed = checkBranchExists(branchName);
        if(proceed){
            System.out.println("Enter your name");
            String customerName = scanner.nextLine();
            Branch customerBranch = bank.returnBranch(branchName);
            if(customerBranch.findCustomer(customerName)!=null){
                System.out.println("What would you like to do? Enter W for Withdraw and D for Deposit: ");
                String userResponse = scanner.nextLine();
                if (userResponse.equals("W")) {
                    System.out.println("Enter the amount you want to withdraw: ");
                    double amount = scanner.nextDouble();
                    customerBranch.withdraw(customerName,amount);
                }
                else if(userResponse.equals("D")){
                    System.out.println("Enter the amount you want to deposit: ");
                    double amount = scanner.nextDouble();
                    customerBranch.deposit(customerName,amount);
                }
                else{
                    System.out.println("Please enter apporopraite response, W or D. Try again.");
                }
            }
            System.out.println("Would you like to see your overall balance? Enter Y or N");
            String viewBalance = scanner.nextLine();
            if(viewBalance.equals("Y")){
                Customer customer = customerBranch.findCustomer(customerName);
                if(customer!=null){
                    Double total = customerBranch.getTotal(customer);
                    System.out.println("Your total is currently: $" + total);
                }
            }
            else{
                System.out.println("Thank you for using our services, have a nice day");
            }

        }
    }
    public static void viewCustomerTransactions(){
        System.out.println("Please enter the name of your branch");
        String branchName = scanner.nextLine();
        boolean proceed = checkBranchExists(branchName);
        if(proceed) {
            System.out.println("Enter your name");
            String customerName = scanner.nextLine();
            Branch customerBranch = bank.returnBranch(branchName);
            if(customerBranch.findCustomer(customerName)!=null){
                System.out.println("Here are your recent transactions ");
                customerBranch.printTransactions(customerName);
            }
            else{
                System.out.println("You are not in this branch, make sure to enter your name correctly. Try again");
            }

        }
    }
    public static boolean checkBranchExists(String branchName){
        boolean proceed = bank.branchExists(branchName);
        return proceed;
    }
}