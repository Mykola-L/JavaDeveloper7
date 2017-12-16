package ua.spring.dao;

import ua.spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserDao extends JpaRepository<User, UUID> {
    User findByEmail(String username);
}
