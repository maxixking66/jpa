package ir.maktab56.jpa;

import com.github.javafaker.Faker;
import ir.maktab56.jpa.domain.Address;
import ir.maktab56.jpa.domain.User;
import ir.maktab56.jpa.service.UserService;
import ir.maktab56.jpa.service.dto.UserFirstNameLastName;
import ir.maktab56.jpa.service.dto.UserSearch;
import ir.maktab56.jpa.util.ApplicationContext;
import ir.maktab56.jpa.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.IntStream;

public class JpaApplication {
    public static void main(String[] args) {
    }

    private static void testProjectionWithTuple() {
        EntityManager entityManager = HibernateUtil.getEntityMangerFactory().createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
        Root<User> userRoot = criteriaQuery.from(User.class);

        criteriaQuery.multiselect(
                userRoot.get("firstName").alias("fn"),
                userRoot.get("lastName").alias("ln"),
                userRoot.get("age").alias("ag")
        );

        criteriaQuery.where(
                criteriaBuilder.like(
                        userRoot.get("firstName"), "%m%"
                )
        );

        List<Tuple> resultList = entityManager.createQuery(criteriaQuery).getResultList();

        resultList.forEach(data ->
                System.out.println(
                        "firstName : " + data.get("fn") + "\tlastName : " + data.get("ln") +
                                "\tage : " + data.get("ag")
                )
        );
    }


    private static void testProjectionWithArrayObject() {
        EntityManager entityManager = HibernateUtil.getEntityMangerFactory().createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<User> userRoot = criteriaQuery.from(User.class);

        criteriaQuery.multiselect(
                userRoot.get("firstName"),
                userRoot.get("lastName")
        );

        List<Object[]> resultList = entityManager.createQuery(criteriaQuery).getResultList();

        resultList.forEach(data ->
                System.out.println(
                        "firstName : " + data[0] + "\t" + "lastName : " + data[1]
                )
        );
    }

    private static void countQuery() {
        EntityManager entityManager = HibernateUtil.getEntityMangerFactory().createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.distinct(true);
        criteriaQuery.select(criteriaBuilder.count(userRoot.get("age")));

        Long count = entityManager.createQuery(criteriaQuery).getSingleResult();
        System.out.println(count);
    }

    private static void testSimpleProjection() {
        EntityManager entityManager = HibernateUtil.getEntityMangerFactory().createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Integer> criteriaQuery = criteriaBuilder.createQuery(Integer.class);
        Root<User> userRoot = criteriaQuery.from(User.class);

        criteriaQuery.select(userRoot.get("age"));

        List<Integer> resultList = entityManager.createQuery(criteriaQuery).getResultList();
        resultList.forEach(System.out::println);

        List<UserFirstNameLastName> projectionList = entityManager.createQuery(
                "select new ir.maktab56.jpa.service.dto" +
                        ".UserFirstNameLastName(u.firstName, u.lastName) from User u",
                UserFirstNameLastName.class
        ).getResultList();

        projectionList.forEach(System.out::println);
    }

    private static void testSearchOnUser() {
        UserService userService = ApplicationContext.getUserService();

        UserSearch userSearch = new UserSearch();
        userSearch.setFirstName("m");
        userSearch.setLastName("in");
        userSearch.setAge(37);

        List<User> userList = userService.searchOnUsers(userSearch);
        userList.forEach(System.out::println);
    }

    private static void insertUser() {
        UserService userService = ApplicationContext.getUserService();
        Faker faker = new Faker();
        IntStream.range(0, 50).forEach(i -> {
            User user = new User();
            user.setFirstName(
                    faker.name().firstName()
            );
            user.setLastName(
                    faker.name().lastName()
            );
            user.setUsername(
                    faker.name().username()
            );
            user.setAge(
                    faker.number().numberBetween(5, 100)
            );
            user.setEmail(
                    faker.internet().emailAddress()
            );
            userService.save(user);
        });
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
