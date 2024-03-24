package com.setuschool.backend.controller;

import com.setuschool.backend.payload.ApiResponse;
import com.setuschool.backend.payload.CourseDto;
import com.setuschool.backend.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("/create-course")
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto courseDto) {
        CourseDto course = this.courseService.createCourse(courseDto);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }

    @PostMapping("/{courseId}/add-instructor/{userId}")
    public ResponseEntity<CourseDto> addUserToCourse(@RequestBody CourseDto courseDto, @PathVariable Integer courseId, @PathVariable Integer userId) {
        CourseDto courseDto1 = this.courseService.addUserToCourse(courseDto, courseId, userId);
        return ResponseEntity.ok(courseDto1);
    }
    @PutMapping("/{courseId}")
    public ResponseEntity<CourseDto> updateCourse(@RequestBody CourseDto courseDto, @PathVariable Integer courseId) {
        CourseDto courseDto1 = this.courseService.updateCourse(courseDto, courseId);
        return ResponseEntity.ok(courseDto1);
    }
    @DeleteMapping("/{courseId}")
    public ResponseEntity<ApiResponse> deleteCourse(@PathVariable Integer courseId) {
        this.courseService.deleteCourse(courseId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Course deleted successfully",true,200),HttpStatus.OK);
    }
    @GetMapping("/get-all-course")
    public ResponseEntity<List<CourseDto>> getAllCourse() {
        List<CourseDto> allCourse = this.courseService.getAllCourse();
        return ResponseEntity.ok(allCourse);
    }
}
