package org.ptkl.webdemo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.ptkl.webdemo.entity.UserDto;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {

    UserDto getUserByName(@Param("name") String name);
}
