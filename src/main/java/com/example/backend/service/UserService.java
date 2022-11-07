package com.example.backend.service;

import com.example.backend.dao.RoleDao;
import com.example.backend.dao.UserDao;
import com.example.backend.entity.Role;
import com.example.backend.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleDao.save(userRole);

        User adminUser = new User();
        adminUser.setUserName("admin123");
        adminUser.setUserPassword(getEncodedPassword("admin@pass"));
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);

        User adminUser2 = new User();
        adminUser2.setUserName("admin");
        adminUser2.setUserPassword(getEncodedPassword("admin123"));
        adminUser2.setUserFirstName("Superadmin");
        adminUser2.setUserLastName("Ultradmin");
        Set<Role> adminRoles2 = new HashSet<>();
        adminRoles2.add(adminRole);
        adminUser2.setRole(adminRoles2);
        userDao.save(adminUser2);

        User user = new User();
        user.setUserName("lanplay");
        user.setUserPassword(getEncodedPassword("lanplay123"));
        user.setUserFirstName("Alan");
        user.setUserLastName("Zarate");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        user.setRole(userRoles);
        userDao.save(user);
    }

    public User registerNewUser(User user) {
        Role role = roleDao.findById("User").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        return userDao.save(user);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
