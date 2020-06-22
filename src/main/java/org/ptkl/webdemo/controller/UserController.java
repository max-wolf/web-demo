package org.ptkl.webdemo.controller;

import org.ptkl.webdemo.entity.UserDto;
import org.ptkl.webdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public Map<String, String> login(String name, String password, HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();

        UserDto userDto = userService.getUserByName(name);
        if (userDto.getPassword().equals(password)) {
            map.put("result", "success");
            request.getSession().setAttribute("user", userDto);
        } else {
            map.put("result", "fail");
        }
        return map;
    }

    @RequestMapping("logout")
    public void logout(HttpSession session, SessionStatus sessionStatus){
        session.invalidate();
        sessionStatus.setComplete();
    }
}
