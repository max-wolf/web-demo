package org.ptkl.webdemo.service;

import org.ptkl.webdemo.entity.MessageDto;
import org.ptkl.webdemo.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageMapper messageMapper;

    public MessageDto getMessageById(Integer id) {
        return messageMapper.getMessageById(id);
    }

    public List<MessageDto> getMessage() {
        return messageMapper.getMessage();
    }

    public int saveMessage(MessageDto messageDto) {
        return messageMapper.saveMessage(messageDto);
    }
}
