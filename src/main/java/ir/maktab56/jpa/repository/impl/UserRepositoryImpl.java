package ir.maktab56.jpa.repository.impl;

import ir.maktab56.jpa.base.repository.BaseEntityRepositoryImpl;
import ir.maktab56.jpa.domain.User;
import ir.maktab56.jpa.repository.UserRepository;

import javax.persistence.EntityManager;

public class UserRepositoryImpl extends BaseEntityRepositoryImpl<User, Long> implements UserRepository {

    public UserRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }
}
