package com.setuschool.backend.payload;

import com.setuschool.backend.entity.User;
import jakarta.persistence.ManyToMany;
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
public class CourseDto {
    private int id;
    private String name;
    private String description;
    private Date createdAt;
    private Date UpdatedAt;
//    private UserDto user;
}
