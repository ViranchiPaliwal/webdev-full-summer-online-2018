package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import webdev.models.Course;
import webdev.repositories.CourseRepository;

@CrossOrigin(origins="*") 
@RestController
public class CourseService {
	@Autowired
	CourseRepository courseRepository;	
	@GetMapping("/api/course")
	public Iterable<Course> findAllCourses() {
		return courseRepository.findAll(); 
	}
}
