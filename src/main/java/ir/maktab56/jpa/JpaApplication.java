package ir.maktab56.jpa;

import ir.maktab56.jpa.domain.User;
import ir.maktab56.jpa.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class JpaApplication {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityMangerFactory();

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<User> users = entityManager.createQuery("from User", User.class).getResultList();

        System.out.println(users);

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        entityManager.persist(
                new User("ali", true)
        );

        transaction.commit();

        users = entityManager.createQuery("from User", User.class).getResultList();

        System.out.println(users);
    }
}
