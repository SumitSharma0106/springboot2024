package com.example.springboot_restful_webservice.service;

import com.example.springboot_restful_webservice.dto.UserDto;
import com.example.springboot_restful_webservice.entity.User;
import com.example.springboot_restful_webservice.exception.EmailAlreadyExistInDb;
import com.example.springboot_restful_webservice.exception.ResourceNotFoundException;
import com.example.springboot_restful_webservice.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springboot_restful_webservice.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {
    
    @Autowired
    UserRepository  userRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    public UserDto createUser(UserDto createUserDto){

        Optional<User> byEmail = userRepository.findByEmail(createUserDto.getEmail());
        if (byEmail.isPresent()){
            throw new EmailAlreadyExistInDb("Email found in db");
        }

        User saveUser=null;
        if(createUserDto !=null){
            User user=modelMapper.map(createUserDto,User.class);
            log.info("User request is : {}", user);
            saveUser=userRepository.save(user);
        }
        return modelMapper.map(saveUser, UserDto.class);
    }

    public UserDto getUserById(long id){
        User byId = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","id",id));
        return modelMapper.map(byId, UserDto.class);

    }

    public List<UserDto> getAllUsers(){
        List<User> all = userRepository.findAll();
            return all.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    public UserDto updateUser(long id,UserDto userDto){
        User byId = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","id",id));
            byId.setFirstName(userDto.getFirstName());
            byId.setLastName(userDto.getLastName());
            byId.setEmail(userDto.getEmail());
            return modelMapper.map(userRepository.save(byId), UserDto.class);
    }

    public Boolean deleteUser(long id){
        boolean isDelete=false;
        User byId = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","id",id));
            if(byId!=null){
                userRepository.deleteById(id);
                isDelete=true;
        }
     return isDelete;
    }
}
