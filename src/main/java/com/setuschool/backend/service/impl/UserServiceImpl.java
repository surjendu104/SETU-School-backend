package com.setuschool.backend.service.impl;

import com.setuschool.backend.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import com.setuschool.backend.entity.User;
import com.setuschool.backend.payload.UserDto;
import com.setuschool.backend.repository.UserRepo;
import com.setuschool.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDto createUser(UserDto userDto) {
        User newUser = userDtoToUser(userDto);
        User savedUser = this.userRepo.save(newUser);
        return this.userToUserDto(savedUser);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> userList = this.userRepo.findAll();
        List<UserDto> userDtos = userList.stream().map(user -> userToUserDto(user)).toList();
        return userDtos;
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        // Retrieve the existing user from the database
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setBio(userDto.getBio());
        user.setLinkedIn(userDto.getLinkedIn());
        user.setProfileImage(userDto.getProfileImage());
        User savedUser = this.userRepo.save(user);

        // Convert the updated user to UserDto and return
        UserDto updatedUserDto = this.userToUserDto(savedUser);
        return updatedUserDto;
    }


    @Override
    public void deleteUser(int userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User"," Id ",userId));
        this.userRepo.delete(user);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User"," Id ",userId));
        return this.userToUserDto(user);
    }

    private User userDtoToUser(UserDto userDto) {
        return this.modelMapper.map(userDto, User.class);
    }
    private UserDto userToUserDto(User user) {
        return this.modelMapper.map(user, UserDto.class);
    }
}
