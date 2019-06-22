package com.sydoruk1ua.mdmg.model.service.impl;

import com.sydoruk1ua.mdmg.model.dao.UserDao;
import com.sydoruk1ua.mdmg.model.entity.Role;
import com.sydoruk1ua.mdmg.model.entity.User;
import com.sydoruk1ua.mdmg.model.service.UserService;
import com.sydoruk1ua.mdmg.util.PasswordEncryptor;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean create(User user) {
        String encodedPassword = PasswordEncryptor.encrypt(user.getPassword());
        User userToDb = User.builder()
                .withEmail(user.getEmail())
                .withFirstName(user.getFirstName())
                .withLastName(user.getLastName())
                .withPassword(encodedPassword)
                .withRole(getRoleWithIdByType(user.getRole().getType()))
                .build();
        return userDao.create(userToDb).isPresent();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        String encodedPassword = PasswordEncryptor.encrypt(password);
        Optional<User> user = findByEmail(email);
        if (user.isPresent() && user.get().getPassword().equals(encodedPassword)) {
            return user;
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void update(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException();
    }

    private Role getRoleWithIdByType(String type) {
        switch (type) {
            case "admin":
                return new Role(1, "admin");
            case "judge":
                return new Role(3, "judge");
            default:
                return new Role(2, "user");
        }
    }
}
