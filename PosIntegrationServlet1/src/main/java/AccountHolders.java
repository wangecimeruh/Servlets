
import javax.persistence.*;

@Entity
@Table(name = "Account_Holders")
public class AccountHolders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column(name = "AccountNumber", columnDefinition = "Int")
    private int AccountNumber;


    @Column(name = "BankBalances" , columnDefinition = "Int")
    private int Bankbalance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(int AccountNumber) {
        this.AccountNumber = AccountNumber;
    }
    public int getBankbalance() {
        return Bankbalance;
    }

    public void setBankbalance(int bankbalance) {
        Bankbalance = bankbalance;
    }
}
