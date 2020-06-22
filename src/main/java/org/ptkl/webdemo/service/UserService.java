package org.ptkl.webdemo.service;

import org.ptkl.webdemo.entity.UserDto;
import org.ptkl.webdemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public UserDto getUserByName(String name) {
        return userMapper.getUserByName(name);
    }
}
