package ir.maktab56.jpa.domain;

import ir.maktab56.jpa.base.domain.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "article")
public class Article extends BaseEntity<Long> {

    private String title;

    @Lob
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Article() {
    }

    public Article(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
