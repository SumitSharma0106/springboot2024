package com.example.springboot_restful_webservice.mapper;

import com.example.springboot_restful_webservice.dto.UserDto;
import com.example.springboot_restful_webservice.entity.User;

public class UserMapper {

    public static User userDtoToUser(UserDto createUserDto){
        User user=new User();
        if(createUserDto !=null){
            user.setId(createUserDto.getId());
            user.setFirstName(createUserDto.getFirstName());
            user.setLastName(createUserDto.getLastName());
            user.setEmail(createUserDto.getEmail());
        }
        return user;
    }

    public static UserDto userToDto(User user){
        UserDto createUserDto=new UserDto();
        if(user !=null){
            createUserDto.setId(user.getId());
            createUserDto.setFirstName(user.getFirstName());
            createUserDto.setLastName(user.getLastName());
            createUserDto.setEmail(user.getEmail());
        }
        return createUserDto;
    }

}
