package ir.maktab56.jpa.domain;

import ir.maktab56.jpa.base.domain.BaseEntity;
import ir.maktab56.jpa.domain.embedable.BankInfo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user_table")
public class User extends BaseEntity<Long> {

    private String firstName;

    private String lastName;

    private Integer age;

    private String username;

    private String email;

    @Embedded
    private BankInfo bankInfo;

    @ElementCollection
    @JoinTable(name = "us_mob_nu")
    private Set<String> mobileNumbers = new HashSet<>();

    @ElementCollection
    @JoinTable(name = "us_mob_nu")
    private Set<Long> ids = new HashSet<>();

    @ElementCollection
    @JoinTable(name = "user_bank_infos")
    private Set<BankInfo> bankInfoSet = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @OneToMany(mappedBy = "user")
    private List<Article> articleList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Address> addressList = new ArrayList<>();

    public User() {
    }

    public User(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public User(String firstName, String lastName, Integer age, Wallet wallet) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.wallet = wallet;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Long> getIds() {
        return ids;
    }

    public void setIds(Set<Long> ids) {
        this.ids = ids;
    }

    public Set<BankInfo> getBankInfoSet() {
        return bankInfoSet;
    }

    public void setBankInfoSet(Set<BankInfo> bankInfoSet) {
        this.bankInfoSet = bankInfoSet;
    }

    public Set<String> getMobileNumbers() {
        return mobileNumbers;
    }

    public void setMobileNumbers(Set<String> mobileNumbers) {
        this.mobileNumbers = mobileNumbers;
    }

    public BankInfo getBankInfo() {
        return bankInfo;
    }

    public void setBankInfo(BankInfo bankInfo) {
        this.bankInfo = bankInfo;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + getId() + '\'' +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }
}
