package com.example.demoswagger.mapstruct;

import com.example.demoswagger.account.Account;
import com.example.demoswagger.account.AccountDto;
import com.example.demoswagger.notification.Notification;
import com.example.demoswagger.notification.NotificationDto;
import com.example.demoswagger.user.User;
import com.example.demoswagger.user.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    AccountDto accountDto(Account account);

    UserDto userDto(User user);

    NotificationDto notificationDto(Notification notification);
}
