package ir.maktab56.jpa;

import ir.maktab56.jpa.domain.Address;
import ir.maktab56.jpa.domain.User;
import ir.maktab56.jpa.service.UserService;
import ir.maktab56.jpa.util.ApplicationContext;
import ir.maktab56.jpa.util.HibernateUtil;

import javax.persistence.EntityManager;

public class JpaApplication {
    public static void main(String[] args) {
        UserService userService = ApplicationContext.getUserService();
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

    private static void testMergeCascade() {
        EntityManager entityManager = HibernateUtil.getEntityMangerFactory().createEntityManager();

        entityManager.getTransaction().begin();

        User user = entityManager.find(User.class, 2L);
        user.setFirstName("John");
//        user.setLastName("Doe2");

        Address address = user.getAddressList().get(0);
        address.setAddress("Address....");
        address.setPostalCode("PostalCode....");

        System.out.println("before merge - em contains user: " + entityManager.contains(user));
        entityManager.merge(user);
        System.out.println("after merge - em contains user: " + entityManager.contains(user));

        user.setFirstName("mohsen");

        entityManager.getTransaction().commit();
    }

    private static void testMergeWithNewInstance() {
        EntityManager entityManager = HibernateUtil.getEntityMangerFactory().createEntityManager();

        entityManager.getTransaction().begin();

        User user = new User();
        user.setFirstName("John");

        System.out.println("before merge - em contains user: " + entityManager.contains(user));
        User mergedUser = entityManager.merge(user);
        System.out.println("after merge - em contains user: " + entityManager.contains(user));

        user.setFirstName("mohsen");
        mergedUser.setFirstName("mohsen");

        entityManager.getTransaction().commit();
    }

    private static void testDetachCascade() {
        EntityManager entityManager = HibernateUtil.getEntityMangerFactory().createEntityManager();

        User user = entityManager.find(User.class, 2L);

        System.out.println("addressList size: " + user.getAddressList().size());

        Address address = user.getAddressList().get(0);

        System.out.println("before detach - em contains user: " + entityManager.contains(user));
        System.out.println("before detach - em contains address: " + entityManager.contains(address));

        entityManager.detach(user);

        System.out.println("after detach - em contains user: " + entityManager.contains(user));
        System.out.println("after detach - em contains address: " + entityManager.contains(address));

    }

    private static void testRefreshCascade() {
        EntityManager entityManager = HibernateUtil.getEntityMangerFactory().createEntityManager();

        User user = entityManager.find(User.class, 2L);
        Address address = user.getAddressList().get(0);

        System.out.println("1. user firstName : " + user.getFirstName());
        System.out.println("1. address : " + address.getAddress());

        user.setFirstName("John");

        address.setAddress("address...5");

        System.out.println("2. user firstName : " + user.getFirstName());
        System.out.println("2. address : " + address.getAddress());

        entityManager.refresh(user);

        System.out.println("3. user firstName : " + user.getFirstName());
        System.out.println("3. address : " + address.getAddress());

    }

    private static void testRemoveCascade() {
        EntityManager entityManager = HibernateUtil.getEntityMangerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, 2L);
        entityManager.remove(user);
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
