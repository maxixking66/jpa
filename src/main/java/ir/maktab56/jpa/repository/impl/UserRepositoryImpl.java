package ir.maktab56.jpa.repository.impl;

import ir.maktab56.jpa.base.repository.BaseEntityRepositoryImpl;
import ir.maktab56.jpa.domain.User;
import ir.maktab56.jpa.repository.UserRepository;
import ir.maktab56.jpa.service.dto.UserSearch;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class UserRepositoryImpl extends BaseEntityRepositoryImpl<User, Long> implements UserRepository {

    public UserRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public List<User> searchOnUsers(UserSearch userSearch) {
        return searchOnUsersFirstMethod(userSearch);
    }

    private List<User> searchOnUsersFirstMethod(UserSearch userSearch) {
        String query = "from User ";
        boolean isFirst = true;

        if (StringUtils.isNotBlank(userSearch.getFirstName())) {
            if (isFirst) {
                query = query.concat("where firstName like '%" + userSearch.getFirstName() + "%' ");
            } else {
                query = query.concat("and firstName like '%" + userSearch.getFirstName() + "%' ");
            }
            isFirst = false;
        }

        if (StringUtils.isNotBlank(userSearch.getLastName())) {
            if (isFirst) {
                query = query.concat("where lastName like '%" + userSearch.getLastName() + "%' ");
            } else {
                query = query.concat("and lastName like '%" + userSearch.getLastName() + "%' ");
            }
            isFirst = false;
        }

        if (userSearch.getAge() != null) {
            if (isFirst) {
                query = query.concat("where age = " + userSearch.getAge() + " ");
            } else {
                query = query.concat("and age = " + userSearch.getAge() + " ");
            }
            isFirst = false;
        }

        if (StringUtils.isNotBlank(userSearch.getUsername())) {
            if (isFirst) {
                query = query.concat("where username like '%" + userSearch.getUsername() + "%' ");
            } else {
                query = query.concat("and username like '%" + userSearch.getUsername() + "%' ");
            }
            isFirst = false;
        }

        if (StringUtils.isNotBlank(userSearch.getEmail())) {
            if (isFirst) {
                query = query.concat("where email like '%" + userSearch.getEmail() + "%' ");
            } else {
                query = query.concat("and email like '%" + userSearch.getEmail() + "%' ");
            }
            isFirst = false;
        }

        return entityManager.createQuery(query, User.class).getResultList();
    }
}
