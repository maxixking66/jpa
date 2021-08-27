package ir.maktab56.jpa.domain.embedable;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class BankInfo implements Serializable {

    private String accountNumber;

    private String cardNumber;

    private String shebaNumber;

    private String bank;

    public BankInfo() {
    }

    public BankInfo(String accountNumber, String cardNumber, String shebaNumber) {
        this.accountNumber = accountNumber;
        this.cardNumber = cardNumber;
        this.shebaNumber = shebaNumber;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getShebaNumber() {
        return shebaNumber;
    }

    public void setShebaNumber(String shebaNumber) {
        this.shebaNumber = shebaNumber;
    }
}
