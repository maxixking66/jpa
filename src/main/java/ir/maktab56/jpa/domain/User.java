package ir.maktab56.jpa.domain;


import javax.persistence.*;

@Entity
@Table(name = User.TABLE_NAME)
public class User {

    public static final String TABLE_NAME = "user_table";
    public static final String FIRST_NAME = "first_name";
    public static final String IS_ACTIVE = "is_active";
    public static final String IS_VERIFY = "is_verify";

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = FIRST_NAME, length = 1024)
    private String firstName;

    @Column(name = IS_ACTIVE)
    private Boolean isActive;

    @Column(name = IS_VERIFY, columnDefinition = "TINYINT(1)")
    private Boolean isVerify;

    public User() {
    }

    public User(String firstName, Boolean isActive) {
        this.firstName = firstName;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getVerify() {
        return isVerify;
    }

    public void setVerify(Boolean verify) {
        isVerify = verify;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
