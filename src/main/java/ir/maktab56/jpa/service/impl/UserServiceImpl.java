package ir.maktab56.jpa.service.impl;

import ir.maktab56.jpa.base.service.impl.BaseEntityServiceImpl;
import ir.maktab56.jpa.domain.User;
import ir.maktab56.jpa.repository.UserRepository;
import ir.maktab56.jpa.service.UserService;
import ir.maktab56.jpa.service.dto.UserSearch;

import java.util.List;

public class UserServiceImpl extends BaseEntityServiceImpl<User, Long, UserRepository>
        implements UserService {

    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

    @Override
    public List<User> searchOnUsers(UserSearch userSearch) {
        return repository.searchOnUsers(userSearch);
    }
}
