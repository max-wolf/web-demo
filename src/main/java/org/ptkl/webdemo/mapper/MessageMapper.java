package org.ptkl.webdemo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.ptkl.webdemo.entity.MessageDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface MessageMapper {

    MessageDto getMessageById(@Param("id") int id);

    List<MessageDto> getMessage();
}
