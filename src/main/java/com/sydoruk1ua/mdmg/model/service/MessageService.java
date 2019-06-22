package com.sydoruk1ua.mdmg.model.service;

import com.sydoruk1ua.mdmg.model.entity.Message;

import java.util.List;

public interface MessageService {

    boolean create(Message message);

    List<Message> findAll();
}
