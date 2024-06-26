package com.example.springboot_restful_webservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
}
