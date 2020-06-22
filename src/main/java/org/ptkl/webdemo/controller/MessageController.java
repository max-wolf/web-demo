package org.ptkl.webdemo.controller;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.ptkl.webdemo.entity.MessageDto;
import org.ptkl.webdemo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @RequestMapping("/getMessageById")
    public MessageDto getMessageById(String id, HttpSession session) {
        if (ObjectUtils.isEmpty(session.getAttribute("user"))) {
            System.out.println("**********not login*********");
            return new MessageDto();
        }

        return messageService.getMessageById(Integer.valueOf(id));
    }

    @RequestMapping("getMessage")
    public List<MessageDto> getMessage() {
        return messageService.getMessage();
    }
}
