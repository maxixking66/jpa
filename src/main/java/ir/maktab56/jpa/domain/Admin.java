package ir.maktab56.jpa.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "ad")
public class Admin extends Profile {

    private Boolean isSuperAdmin;

    public Admin() {
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + getId() +
                "firstName=" + getFirstName() + "\t" +
                "isSuperAdmin=" + isSuperAdmin +
                '}';
    }

    public Boolean getSuperAdmin() {
        return isSuperAdmin;
    }

    public void setSuperAdmin(Boolean superAdmin) {
        isSuperAdmin = superAdmin;
    }
}
