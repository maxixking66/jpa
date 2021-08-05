package ir.maktab56.jpa;

import ir.maktab56.jpa.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class JpaApplication {

    public static void main(String[] args) {
        EntityManagerFactory entityMangerFactory =
                HibernateUtil.getEntityMangerFactory();

        EntityManager entityManager = entityMangerFactory.createEntityManager();
    }
}
