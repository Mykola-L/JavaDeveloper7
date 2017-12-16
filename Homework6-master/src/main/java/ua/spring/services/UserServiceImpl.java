package ua.spring.services;

import ua.spring.dao.RoleDao;
import ua.spring.dao.UserDao;
import ua.spring.model.Role;
import ua.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{
    private UserDao userDao;
    private RoleDao roleDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getOne(UUID.randomUUID()));
        user.setRoles(roles);
        userDao.save(user);
    }

    public User findByUsername(String username) {
        return userDao.findByEmail(username);
    }
}
