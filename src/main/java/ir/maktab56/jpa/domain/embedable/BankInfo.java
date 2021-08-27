package ir.maktab56.jpa.domain.embedable;

import ir.maktab56.jpa.domain.enumerated.BankType;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Embeddable
public class BankInfo implements Serializable {

    private String accountNumber;

    private String cardNumber;

    private String shebaNumber;

    @Enumerated(EnumType.STRING)
    private BankType bankType;

    public BankType getBankType() {
        return bankType;
    }

    public void setBankType(BankType bankType) {
        this.bankType = bankType;
    }

    public BankInfo() {
    }

    public BankInfo(String accountNumber, String cardNumber, String shebaNumber) {
        this.accountNumber = accountNumber;
        this.cardNumber = cardNumber;
        this.shebaNumber = shebaNumber;
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
