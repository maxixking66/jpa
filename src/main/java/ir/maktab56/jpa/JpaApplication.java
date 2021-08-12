package ir.maktab56.jpa;

import ir.maktab56.jpa.domain.User;
import ir.maktab56.jpa.service.UserService;
import ir.maktab56.jpa.util.ApplicationContext;

public class JpaApplication {
    public static void main(String[] args) {
        UserService userService = ApplicationContext.getUserService();
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
