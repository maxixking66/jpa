package ir.maktab56.jpa.domain;

import ir.maktab56.jpa.base.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "article_category")
@Entity
public class ArticleCategory extends BaseEntity<Long> {

    private String title;

    public ArticleCategory() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
