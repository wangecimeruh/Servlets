import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Scanner;

public class HibernateHelper {
    public static void Helper(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter name ");
        String name = scan.nextLine();
        System.out.println("Enter Account Number");
        int accountNumber = scan.nextInt();
        System.out.println("Enter Ammount");
        int ammount = scan.nextInt();

        Session session = HibernateDB.getSessionFactory().getCurrentSession();
        //session.beginTransaction();
        Transaction tx = session.getTransaction();
        tx.begin();

        AccountHolders accountHolders = new AccountHolders();
        accountHolders.setName(name);
        accountHolders.setAccountNumber(accountNumber);
        accountHolders.setBankbalance(ammount);
        session.save(accountHolders);


        //session.getTransaction().commit();
        tx.commit();
    }
    public static void retrieveUsingIdWithGetMethod(){

        Session session = HibernateDB.getSessionFactory().openSession();

      AccountHolders user = session.get( AccountHolders.class,1);
        System.out.println("******** Loaded data ****");

        System.out.println(user.getName());
        System.out.println(user.getAccountNumber());
        System.out.println(user.getBankbalance());


    }
    public static void retrieveUsingIdWithLoadMethod() {

        Session session = HibernateDB.getSessionFactory().openSession();

        AccountHolders user = session.load(AccountHolders.class, 1);
        System.out.println("******** Data not load ..waiting to user the object ****");

        System.out.println("Started to use the object");
        System.out.println(user.getId());
        System.out.println("It was loaded..after using the object the first time...");

        System.out.println(user.getName());
        System.out.println(user.getAccountNumber());
        System.out.println(user.getBankbalance());
    }
        public static void main(String args []){
       // Helper();
        retrieveUsingIdWithGetMethod();
        retrieveUsingIdWithLoadMethod();


    }
}
