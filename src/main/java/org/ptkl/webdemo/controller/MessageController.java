package org.ptkl.webdemo.controller;

import org.ptkl.webdemo.entity.MessageDto;
import org.ptkl.webdemo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @RequestMapping("/getMessageById")
    public MessageDto getMessageById(@RequestParam("id") int id) {
        return messageService.getMessageById(id);
    }

    @RequestMapping("getMessage")
    public List<MessageDto> getMessage() {
        return messageService.getMessage();
    }
}
