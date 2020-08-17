import org.hibernate.Session;
import org.hibernate.Transaction;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class Calculator extends Frame implements ActionListener {
    Label label1;
    Label label2;
    Label label3;

    TextField Account_no;
    TextField amount;
    TextField newAccount;
    Button withdraw;
    Button transfer;
    Button deposit;

    Calculator(){
        setLayout(null);

        label1 = new Label("A/C no: ");
        label1.setBounds(10,30,60,20);
        Account_no = new TextField();
        Account_no.setBounds(50,50,270,30);
        label2 = new Label("Amount: ");
        label2.setBounds(10,80,60,20);
        amount = new TextField();
        amount.setBounds(50,100,270,30);
        label3 = new Label("Acc to Transfer to: ");
        label3.setBounds(10,130,100,20);
        newAccount = new TextField();
        newAccount.setBounds(50,150,270,30);
        withdraw = new Button("Withdraw");
        withdraw.setBounds(50,200,120,30);
        deposit = new Button("Deposit");
        deposit.setBounds(50,250,120,30);
        transfer = new Button("Transfer Funds");
        transfer.setBounds(50,300,120,30);

        withdraw.addActionListener(this);
        deposit.addActionListener(this);
        transfer.addActionListener(this);

        add(label1);
        add(label2);
        add(label3);
        add(Account_no);
        add(newAccount);
        add(amount);
        add(withdraw);
        add(deposit);
        add(transfer);


        setVisible(true);
        setSize(400,400);
        setBackground(Color.LIGHT_GRAY);
    }
    public void Withdraw() {

        Session session = HibernateDB.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        AccountHolders user = session.load(AccountHolders.class, 1);
        int id =user.getId();
        int result;
        int account_no = Integer.parseInt(Account_no.getText());
        Account_no.setText("");
        int accno = user.getAccountNumber();
        int Amount = Integer.parseInt(amount.getText());
        amount.setText("");
        int ammount = user.getBankbalance();
        if (account_no == accno) {
            //check if there are available funds
            if (ammount < Amount)
                System.out.println("Invalid Funds");
            //Perform Withdrawal
            result = ammount - Amount;
            System.out.println("Withdrawal of " + Amount + " Successful new bank balance is " + result);

            session.update(user);
            user.setBankbalance(result);
            tx.commit();
            session.close();
        }
    }
    public void deposit(){
            Session session = HibernateDB.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            AccountHolders user = session.load(AccountHolders.class, 1);
            int result;
            int account_no= Integer.parseInt(Account_no.getText());
            Account_no.setText("");
            int accno = user.getAccountNumber();
            int Amount = Integer.parseInt(amount.getText());
            amount.setText("");
            int ammount = user.getBankbalance();
            if(account_no==accno){
                //Perform Withdrawal
                result = ammount + Amount;
                System.out.println("Deposit of "+Amount+" Successful new bank balance is "+ result);

                session.update(user);
                user.setBankbalance(result);
                tx.commit();
                session.close();
            }
    }
    public void Transfer() {
        Session session = HibernateDB.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        AccountHolders user = session.load(AccountHolders.class, 1);
        AccountHolders user2 = session.load(AccountHolders.class, 2);

        int result;
        int result2;
        int account_no = Integer.parseInt(Account_no.getText());
        Account_no.setText("");
        int account_no2 = Integer.parseInt(newAccount.getText());
        newAccount.setText("");
        int accno = user.getAccountNumber();
        int acco2 = user2.getAccountNumber();
        int Amount = Integer.parseInt(amount.getText());
        amount.setText("");
        int ammount = user.getBankbalance();
        int ammount2 =user2.getBankbalance();
        if (account_no == accno && account_no2== acco2) {
            //check if there are available funds
            if (ammount < Amount)
                System.out.println("Invalid Funds");
            //Perform Withdrawal
            result = ammount - Amount;
            result2 =Amount + ammount2;
            System.out.println("Transfer of amount "+Amount+"from account "+ accno + " to "+acco2+ " Successful");

            session.update(user);
            user.setBankbalance(result);
            user2.setBankbalance(result2);
            tx.commit();
            session.close();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == withdraw) {
            Withdraw();
        } else if (e.getSource() == deposit) {
            deposit();
        } else if (e.getSource() == transfer) {
           Transfer();
        }
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
    }
}




