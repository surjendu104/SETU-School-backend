package com.setuschool.backend.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer id;
    @NotEmpty
    private String name;
    private String email;
    private String linkedIn;
    private String bio;
    private String profileImage;
    private String category;
    private Date createdAt;
    private Date updatedAt;
    private List<CourseDto> courseList;
}
