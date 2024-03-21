package com.setuschool.backend.service;

import com.setuschool.backend.payload.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);
    List<UserDto> getAllUser();
    UserDto updateUser(UserDto userDto, Integer userId);
    void deleteUser(int userId);
    UserDto getUserById(Integer userId);
}
