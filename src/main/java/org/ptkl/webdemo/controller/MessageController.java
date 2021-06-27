package org.ptkl.webdemo.controller;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.ptkl.webdemo.entity.MessageDto;
import org.ptkl.webdemo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @RequestMapping("/getMessageById")
    public Map<String, Object> getMessageById(String id, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        if (ObjectUtils.isEmpty(session.getAttribute("user"))) {
            System.out.println("**********not login*********");
            map.put("result", "fail");
            return map;
        }

        MessageDto messageDto = messageService.getMessageById(Integer.valueOf(id));

        map.put("result", "success");
        map.put("messageDto", messageDto);
        return map;
    }

    @RequestMapping("getMessage")
    public List<MessageDto> getMessage() {
        return messageService.getMessage();
    }

    @RequestMapping("/saveMessage")
    public Map<String, Object> saveMessage(@RequestBody MessageDto messageDto) {
        Map<String, Object> map = new HashMap<>();
        int res = messageService.saveMessage(messageDto);
        if (res > 0) {
            map.put("result", "success");
        } else {
            map.put("result", "fail");
        }
        return map;
    }

    @RequestMapping("/saveMessageFilterScript")
    public Map<String, Object> saveMessageFilterScript(@RequestBody MessageDto messageDto) {
        Map<String, Object> map = new HashMap<>();
        System.out.println("**************"+messageDto);
        String message = messageDto.getMessage();
        if (StringUtils.contains(message, "script")) {
            message = StringUtils.remove(message, "script");
        }
        if (StringUtils.contains(message, "SCRIPT")) {
            message = StringUtils.remove(message, "SCRIPT");
        }
        messageDto.setMessage(message);
        int res = messageService.saveMessage(messageDto);
        if (res > 0) {
            map.put("result", "success");
        } else {
            map.put("result", "fail");
        }
        return map;
    }

    @RequestMapping("/saveMessageFilterScript1")
    public Map<String, Object> saveMessageFilterScript1(@RequestBody MessageDto messageDto) {
        Map<String, Object> map = new HashMap<>();
        System.out.println("**************"+messageDto);
        String message = messageDto.getMessage();
        message = StringUtils.removeStart(message, "<script>");
        message = StringUtils.removeEnd(message, "</script>");
        messageDto.setMessage(message);
        int res = messageService.saveMessage(messageDto);
        if (res > 0) {
            map.put("result", "success");
        } else {
            map.put("result", "fail");
        }
        return map;
    }

    @RequestMapping("/reflectXSS")
    public Map<String, Object> reflectXSS(@RequestParam("message") String message) {
        Map<String, Object> map = new HashMap<>();
        System.out.println("**************" + message);
        map.put("message", message);
        return map;
    }
}
