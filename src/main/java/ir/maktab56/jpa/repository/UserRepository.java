package ir.maktab56.jpa.repository;

import ir.maktab56.jpa.base.repository.BaseEntityRepository;
import ir.maktab56.jpa.domain.User;
import ir.maktab56.jpa.service.dto.UserSearch;

import java.util.List;

public interface UserRepository extends BaseEntityRepository<User, Long> {

    List<User> searchOnUsers(UserSearch userSearch);
}
