package com.example.springboot_restful_webservice.controller;

import com.example.springboot_restful_webservice.dto.UserDto;
import com.example.springboot_restful_webservice.entity.User;
import com.example.springboot_restful_webservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user/v1")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping()
    public ResponseEntity<UserDto> createUser(@Validated @RequestBody UserDto createUserDto){
        return new ResponseEntity<>(userService.createUser(createUserDto),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUseById(@Validated @PathVariable long id){
        return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@Validated @PathVariable long id,@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.updateUser(id,userDto),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@Validated @PathVariable long id){
        Boolean b = userService.deleteUser(id);
        if(b){
            return new ResponseEntity<>(id + " deleted successfully.",HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(id + " not found in DB.",HttpStatus.NOT_FOUND);
        }
    }
}
