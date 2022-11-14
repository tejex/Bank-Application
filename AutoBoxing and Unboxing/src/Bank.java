import java.util.ArrayList;

public class Bank {

    private ArrayList<Branch> branches = new ArrayList<Branch>();


    public void createNewBranch(String name){
        Branch branch = new Branch(name, new ArrayList<>());
        branches.add(branch);
    }
    public void addCustomerToBranch(String branchName, String customerName, double initialAmount){
        //Customer will enter the name of branch they want to join
        Branch branch = getBranch(branchName);
        if(branch!=null){
            branch.newCustomer(customerName,initialAmount);
        }
        else{
            System.out.println("Branch does not exist");
        }
    }
//Displays available branches
    public void printBranches(){
        System.out.println("Available Branches: ");
        for(Branch branch : branches){
            System.out.println(" -> " + branch.getName());
        }

    }
    //checks if the branch exists
    public boolean branchExists(String name){
        if(getBranch(name)!=null){
            return true;
        }
        else{
            return false;
        }
    }

    //returns the branch, only used under the assumption that the bank does exist
    public Branch returnBranch(String name){
        Branch branch = getBranch(name);
        return branch;
    }
//Inner workings for checking if the bank exists
    private Branch getBranch(String name){
        for(Branch branch : branches){
            if(branch.getName().equals(name)){
                return branch;
            }
        }
        return null;
    }
}
