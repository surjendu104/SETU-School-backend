package com.setuschool.backend.payload;

import com.setuschool.backend.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer id;
    private String name;
    private String email;
    private String linkedIn;
    private String bio;
    private String profileImage;
    private String category;
    private List<CourseDto> courseList;
}
