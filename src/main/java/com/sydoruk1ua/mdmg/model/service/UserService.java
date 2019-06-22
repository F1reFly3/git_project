package com.sydoruk1ua.mdmg.model.service;

import com.sydoruk1ua.mdmg.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    boolean create(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);

    List<User> findAll();

    void update(User user);

    void deleteById(Integer id);
}
