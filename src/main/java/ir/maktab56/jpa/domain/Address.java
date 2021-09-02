package ir.maktab56.jpa.domain;

import ir.maktab56.jpa.base.domain.BaseEntity;
import ir.maktab56.jpa.domain.embedable.BankInfo;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "address")
public class Address extends BaseEntity<Long> {

    private String address;

    private String postalCode;

    @ElementCollection
    @JoinTable(name = "user_bank_infos")
    private Set<BankInfo> bankInfoSet = new HashSet<>();

    public Address(String address, String postalCode) {
        this.address = address;
        this.postalCode = postalCode;
    }

    public Address() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Set<BankInfo> getBankInfoSet() {
        return bankInfoSet;
    }

    public void setBankInfoSet(Set<BankInfo> bankInfoSet) {
        this.bankInfoSet = bankInfoSet;
    }
}
