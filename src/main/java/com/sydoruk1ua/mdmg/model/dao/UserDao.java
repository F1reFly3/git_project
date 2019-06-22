package com.sydoruk1ua.mdmg.model.dao;

import com.sydoruk1ua.mdmg.model.entity.User;

import java.util.Optional;

public interface UserDao extends GenericDao<User> {
    Optional<User> findByEmail(String email);
}
