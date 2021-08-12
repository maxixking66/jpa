package ir.maktab56.jpa.util;

import ir.maktab56.jpa.repository.UserRepository;
import ir.maktab56.jpa.repository.impl.UserRepositoryImpl;
import ir.maktab56.jpa.service.UserService;
import ir.maktab56.jpa.service.impl.UserServiceImpl;

import javax.persistence.EntityManager;

public class ApplicationContext {

    private static final UserRepository userRepository;

    private static final UserService userService;

    private ApplicationContext() {
    }

    static {
        EntityManager entityManager = HibernateUtil.getEntityMangerFactory().createEntityManager();
        userRepository = new UserRepositoryImpl(entityManager);
        userService = new UserServiceImpl(userRepository);
    }

    public static UserService getUserService() {
        return userService;
    }
}
