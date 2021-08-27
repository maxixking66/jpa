package ir.maktab56.jpa.service;

import ir.maktab56.jpa.base.service.BaseEntityService;
import ir.maktab56.jpa.domain.User;
import ir.maktab56.jpa.service.dto.UserSearch;

import java.util.List;

public interface UserService extends BaseEntityService<User, Long> {

    List<User> searchOnUsers(UserSearch userSearch);
}
