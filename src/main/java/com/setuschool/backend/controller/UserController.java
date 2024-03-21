package com.setuschool.backend.controller;

import com.setuschool.backend.payload.ApiResponse;
import com.setuschool.backend.payload.ImageResponse;
import com.setuschool.backend.payload.UserDto;
import com.setuschool.backend.service.ImageUpload;
import com.setuschool.backend.service.UserService;
import com.setuschool.backend.service.impl.ImageUploadImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ImageUpload imageUpload;
    @Value("${project.image}")
    private String path;

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto createdUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }

    //PUT-update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable Integer userId) {
        UserDto updatedUser =this.userService.updateUser(userDto, userId);
        return ResponseEntity.ok(updatedUser);
    }

    //DELETE-delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {
        this.userService.deleteUser(userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully",true,200),HttpStatus.OK);
    }

    //GET-get All user
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(this.userService.getAllUser());
    }

    //GET-get Single user
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }

    @PostMapping("/image/upload/{userId}")
    public ResponseEntity<ImageResponse> uploadPostImage(@RequestParam("image") MultipartFile image, @PathVariable Integer userId){
         UserDto user = this.userService.getUserById(userId);
        String fileName="";
        try {
            fileName = this.imageUpload.uploadImage(path, image);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        user.setProfileImage(fileName);
        UserDto updatedUser = this.userService.updateUser(user, userId);
        ImageResponse imageResponse = new ImageResponse();
        imageResponse.setUserDto(user);
        imageResponse.setMessage("Image uploaded successfully");
        imageResponse.setSuccess(true);
        imageResponse.setResponseCode(200);
        return new ResponseEntity<ImageResponse>(imageResponse,HttpStatus.OK);
    }
}
