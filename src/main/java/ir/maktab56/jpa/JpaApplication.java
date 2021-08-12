package ir.maktab56.jpa;

import ir.maktab56.jpa.util.HibernateUtil;

public class JpaApplication {
    public static void main(String[] args) {
        HibernateUtil.getEntityMangerFactory().createEntityManager();
    }
}
