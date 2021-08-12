package ir.maktab56.jpa.domain;

import ir.maktab56.jpa.base.domain.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wallet")
public class Wallet extends BaseEntity<Long> {

    private Long totalAmount;

    private Long creditAmount;

    private Long cashAmount;

    @OneToOne(mappedBy = "wallet")
    private User user;

    @OneToMany
    @JoinColumn(name = "wallet_id")
    private List<WalletLog> walletLogList = new ArrayList<>();

    public Wallet() {
    }

    public Wallet(Long totalAmount, Long creditAmount, Long cashAmount) {
        this.totalAmount = totalAmount;
        this.creditAmount = creditAmount;
        this.cashAmount = cashAmount;
    }

    public Wallet(Long totalAmount, Long creditAmount, Long cashAmount, User user) {
        this.totalAmount = totalAmount;
        this.creditAmount = creditAmount;
        this.cashAmount = cashAmount;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public List<WalletLog> getWalletLogList() {
        return walletLogList;
    }

    public void setWalletLogList(List<WalletLog> walletLogList) {
        this.walletLogList = walletLogList;
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
