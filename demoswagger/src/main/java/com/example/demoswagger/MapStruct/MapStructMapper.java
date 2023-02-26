package com.example.demoswagger.MapStruct;

import org.mapstruct.Mapper;

import com.example.demoswagger.Account.*;
import com.example.demoswagger.User.*;
import com.example.demoswagger.Notification.*;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    AccountDto accountDto(Account account);

    UserDto userDto(User user);

    NotificationDto notificationDto(Notification notification);
}
