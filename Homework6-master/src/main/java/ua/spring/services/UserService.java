package ua.spring.services;

import ua.spring.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
