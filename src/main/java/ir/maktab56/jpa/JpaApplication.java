package ir.maktab56.jpa;

import ir.maktab56.jpa.domain.Address;
import ir.maktab56.jpa.domain.User;
import ir.maktab56.jpa.service.UserService;
import ir.maktab56.jpa.util.HibernateUtil;

import javax.persistence.EntityManager;

public class JpaApplication {
    public static void main(String[] args) {

    }

    private static void testPersistCascade() {
        EntityManager entityManager = HibernateUtil.getEntityMangerFactory().createEntityManager();

        entityManager.getTransaction().begin();

        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");

        Address address = new Address();
        address.setAddress("Address....");
        address.setPostalCode("PostalCode....");

        user.getAddressList().add(address);

        entityManager.persist(user);

        entityManager.getTransaction().commit();
    }

    private static void testQueries(UserService userService) {
        System.out.println(userService.countAll());
        System.out.println(userService.existsById(1L));
        System.out.println(
                userService.save(
                        new User(
                                "ali",
                                "erfagh",
                                25
                        )
                )
        );
        System.out.println(userService.countAll());
        System.out.println(userService.existsById(1L));
    }
}
