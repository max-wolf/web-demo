package org.ptkl.webdemo.controller;

import org.apache.commons.lang3.StringUtils;
import org.ptkl.webdemo.entity.UserDto;
import org.ptkl.webdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    public Map<String, String> login(@RequestBody UserDto user, HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();

        System.out.println(user.getName());
        System.out.println(user.getPassword());
        UserDto userDto = userService.getUserByName(user.getName());
        System.out.println(userDto);
        if (userDto != null && StringUtils.equals(userDto.getPassword(), user.getPassword())) {
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
