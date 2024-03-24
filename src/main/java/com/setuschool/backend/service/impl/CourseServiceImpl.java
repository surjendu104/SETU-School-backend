package com.setuschool.backend.service.impl;

import com.setuschool.backend.entity.Course;
import com.setuschool.backend.entity.User;
import com.setuschool.backend.exceptions.ResourceNotFoundException;
import com.setuschool.backend.payload.CourseDto;
import com.setuschool.backend.repository.CourseRepo;
import com.setuschool.backend.repository.UserRepo;
import com.setuschool.backend.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CourseRepo courseRepo;
    @Override
    public CourseDto createCourse(CourseDto courseDto) {
        Course newCourse = this.courseDtoToCourse(courseDto);
        newCourse.setCreatedAt(new Date());
        newCourse.setUpdatedAt(new Date());
        Course savedCourse = this.courseRepo.save(newCourse);
        return this.courseToCourseDto(savedCourse);
    }

    @Override
    public List<CourseDto> getAllCourse() {
        List<Course> allCourses = this.courseRepo.findAll();
        List<CourseDto> courseDtos = allCourses.stream().map(course -> this.courseToCourseDto(course)).toList();
        return courseDtos;
    }

    @Override
    public CourseDto addUserToCourse(CourseDto courseDto, Integer courseId, Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User ", "user id", userId));
        Course course = this.courseRepo.findById(courseId).orElseThrow(()->new ResourceNotFoundException("Course", "Id", courseId));
        course.setName(courseDto.getName());
        course.setDescription(courseDto.getDescription());
        course.setUser(user);
        course.setUpdatedAt(new Date());
        this.courseRepo.save(course);
        return this.courseToCourseDto(course);
    }

    @Override
    public CourseDto updateCourse(CourseDto courseDto, Integer courseId) {
        Course course = this.courseRepo.findById(courseId).orElseThrow(()->new ResourceNotFoundException("Course", "Id", courseId));
        course.setName(courseDto.getName());
        course.setDescription(courseDto.getDescription());
        course.setUpdatedAt(new Date());
        this.courseRepo.save(course);
        return this.courseToCourseDto(course);
    }

    @Override
    public void deleteCourse(Integer courseId) {
        Course course = this.courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course", "Id", courseId));
        this.courseRepo.delete(course);
    }

    @Override
    public CourseDto getCourseByCourseId(Integer courseId) {
        Course course = this.courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course", "Id", courseId));
        return this.courseToCourseDto(course);
    }

    private CourseDto courseToCourseDto(Course course) {
        return this.modelMapper.map(course, CourseDto.class);
    }
    private Course courseDtoToCourse(CourseDto courseDto) {
        return this.modelMapper.map(courseDto, Course.class);
    }
}
