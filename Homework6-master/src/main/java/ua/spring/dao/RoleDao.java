package ua.spring.dao;

import ua.spring.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleDao extends JpaRepository<Role, UUID> {
}
