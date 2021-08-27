package ir.maktab56.jpa.repository.impl;

import ir.maktab56.jpa.base.repository.BaseEntityRepositoryImpl;
import ir.maktab56.jpa.domain.User;
import ir.maktab56.jpa.repository.UserRepository;
import ir.maktab56.jpa.service.dto.UserSearch;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.criteria.internal.OrderImpl;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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
//        return searchOnUsersFirstMethod(userSearch);
        return searchOnUsersWithCriteria(userSearch);
    }

    private List<User> searchOnUsersWithCriteria(UserSearch userSearch) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root).where(
                getPredicates(userSearch, criteriaBuilder, root)
        );
        criteriaQuery.orderBy(new OrderImpl(root.get("age")).reverse());
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    private Predicate getPredicates(UserSearch userSearch, CriteriaBuilder criteriaBuilder, Root<User> root) {
        List<Predicate> predicates = new ArrayList<>();
        setFirstNameForSearch(predicates, criteriaBuilder, root, userSearch.getFirstName());
        setLastNameForSearch(predicates, criteriaBuilder, root, userSearch.getLastName());
        setAgeForSearch(predicates, criteriaBuilder, root, userSearch.getAge());
        setUsernameForSearch(predicates, criteriaBuilder, root, userSearch.getUsername());
        setEmailForSearch(predicates, criteriaBuilder, root, userSearch.getEmail());
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setFirstNameForSearch(List<Predicate> predicates, CriteriaBuilder criteriaBuilder,
                                       Root<User> root, String firstName) {
        if (StringUtils.isNotBlank(firstName)) {
            predicates.add(
                    criteriaBuilder.like(
                            root.get("firstName"),
                            "%" + firstName + "%"
                    )
            );
        }
    }

    private void setLastNameForSearch(List<Predicate> predicates, CriteriaBuilder criteriaBuilder,
                                      Root<User> root, String lastName) {
        if (StringUtils.isNotBlank(lastName)) {
            predicates.add(
                    criteriaBuilder.like(
                            root.get("lastName"),
                            "%" + lastName + "%"
                    )
            );
        }
    }

    private void setAgeForSearch(List<Predicate> predicates, CriteriaBuilder criteriaBuilder,
                                 Root<User> root, Integer age) {
        if (age != null) {
            predicates.add(
                    criteriaBuilder.equal(
                            root.get("age"),
                            age
                    )
            );
        }
    }

    private void setUsernameForSearch(List<Predicate> predicates, CriteriaBuilder criteriaBuilder,
                                      Root<User> root, String username) {
        if (StringUtils.isNotBlank(username)) {
            predicates.add(
                    criteriaBuilder.like(
                            root.get("username"),
                            "%" + username + "%"
                    )
            );
        }
    }

    private void setEmailForSearch(List<Predicate> predicates, CriteriaBuilder criteriaBuilder,
                                   Root<User> root, String email) {
        if (StringUtils.isNotBlank(email)) {
            predicates.add(
                    criteriaBuilder.like(
                            root.get("email"),
                            "%" + email + "%"
                    )
            );
        }
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
