package ir.maktab56.jpa.domain;

import ir.maktab56.jpa.base.domain.BaseEntity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import java.util.Set;

@MappedSuperclass
public class Category<E extends Category<E>> extends BaseEntity<Long> {

    private String title;

    @ManyToOne
    @JoinColumn(name = "perent_id")
    private E parent;

    @OneToMany
    @JoinColumn(name = "perent_id")
    private Set<E> childrenCategories;

    public E getParent() {
        return parent;
    }

    public void setParent(E parent) {
        this.parent = parent;
    }

    public Set<E> getChildrenCategories() {
        return childrenCategories;
    }

    public void setChildrenCategories(Set<E> childrenCategories) {
        this.childrenCategories = childrenCategories;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
