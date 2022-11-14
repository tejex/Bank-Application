import java.util.ArrayList;
import java.util.Objects;

public class Branch {
    private String name;
    private ArrayList<Customer> customers = new ArrayList<Customer>();

    public Branch(String name, ArrayList<Customer> customers) {
        this.name = name;
        this.customers = customers;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    //Creating new customer for the branch
    public void createNewCustomer(String name, double initialAmount){
        newCustomer(name, initialAmount);
    }
    public void newCustomer(String name, double initialAmount){
        if(findCustomer(name)!=null){
            if(initialAmount < 50.00){
                System.out.println("Initial Deposit must be higher. Try Again");
                return;
            }
            Customer customer = new Customer(name, initialAmount);
            customers.add(customer);
        }
        else{
            System.out.println("Customer with that name already exists");
        }

    }
    public double getTotal(Customer customer){
        double total =0;
        for(int i=0;i<customer.getTransactions().size();i++) {
            total+= customer.getTransactions().get(i);
        }
        return total;
    }


///additional transactions for the customer.
    public void deposit(String name,Double amount){
        if(amount < 50.00){
            System.out.println("Deposit must be higher. Try Again");
        }
        Customer customer = findCustomer(name);
        if(customer!= null){
            customer.getTransactions().add(amount);
            getTotal(customer);
        }
    }
    public void withdraw(String name, Double amount){
        Customer customer = findCustomer(name);
        double total=0;
        if(customer!=null){
            total = getTotal(customer);
        }
        if(amount > total ){
            System.out.println("Cannot withdraw more than what is available. Try again.");
        }
        else{
            assert customer != null;
            customer.getTransactions().add(-amount);
            getTotal(customer);
        }
    }

    //searching for the customer
    public Customer findCustomer(String name){
        Customer customer = searchCustomer(name);
        if(customer!=null){
            return customer;
        }
        else{
            return null;
        }
    }
    private Customer searchCustomer(String name){
        for (Customer customer : customers) {
            if (name.equals(customer.getName())) {
                return customer;
            }
        }
        return null;
    }
    public void printTransactions(String name){
        Customer customer = searchCustomer(name);
        for(int i = 0; i< Objects.requireNonNull(customer).getTransactions().size(); i++){
            System.out.println(i+1+ ") ->" + customer.getTransactions().get(i) );
        }
    }
}
