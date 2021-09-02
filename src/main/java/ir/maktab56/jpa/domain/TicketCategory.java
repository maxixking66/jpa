package ir.maktab56.jpa.domain;

import javax.persistence.Entity;
import java.util.Set;

@Entity
public class TicketCategory extends Category<TicketCategory> {

    @Override
    public Set<TicketCategory> getChildrenCategories() {
        return super.getChildrenCategories();
    }

    @Override
    public TicketCategory getParent() {
        return super.getParent();
    }
}
