package com.setuschool.backend.service;

import com.setuschool.backend.payload.CourseDto;

import java.util.List;

public interface CourseService {
    CourseDto createCourse(CourseDto courseDto);
    List<CourseDto> getAllCourse();
    CourseDto addUserToCourse(CourseDto courseDto, Integer courseId, Integer userId);
    CourseDto updateCourse(CourseDto courseDto, Integer courseId);
    void deleteCourse(Integer courseId);
    CourseDto getCourseByCourseId(Integer courseId);
}
