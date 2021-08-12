package ir.maktab56.jpa.domain;

import ir.maktab56.jpa.base.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "wallet_log")
public class WalletLog extends BaseEntity<Long> {

    private Long totalAmount;

    private Long creditAmount;

    private Long cashAmount;

    private Long creditAmountChange;

    private Long cashAmountChange;

    public WalletLog() {
    }

    public WalletLog(Long totalAmount, Long creditAmount, Long cashAmount) {
        this.totalAmount = totalAmount;
        this.creditAmount = creditAmount;
        this.cashAmount = cashAmount;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(Long creditAmount) {
        this.creditAmount = creditAmount;
    }

    public Long getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(Long cashAmount) {
        this.cashAmount = cashAmount;
    }

    public Long getCreditAmountChange() {
        return creditAmountChange;
    }

    public void setCreditAmountChange(Long creditAmountChange) {
        this.creditAmountChange = creditAmountChange;
    }

    public Long getCashAmountChange() {
        return cashAmountChange;
    }

    public void setCashAmountChange(Long cashAmountChange) {
        this.cashAmountChange = cashAmountChange;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + getId() +
                "totalAmount=" + totalAmount +
                ", creditAmount=" + creditAmount +
                ", cashAmount=" + cashAmount +
                '}';
    }
}
