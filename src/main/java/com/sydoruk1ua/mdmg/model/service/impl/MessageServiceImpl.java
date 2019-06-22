package com.sydoruk1ua.mdmg.model.service.impl;

import com.sydoruk1ua.mdmg.model.dao.MessageDao;
import com.sydoruk1ua.mdmg.model.entity.Message;
import com.sydoruk1ua.mdmg.model.service.MessageService;

import java.util.List;

public class MessageServiceImpl implements MessageService {
    private final MessageDao messageDao;

    public MessageServiceImpl(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @Override
    public boolean create(Message message) {
        return messageDao.create(message).isPresent();
    }

    @Override
    public List<Message> findAll() {
        return messageDao.findAll();
    }
}
