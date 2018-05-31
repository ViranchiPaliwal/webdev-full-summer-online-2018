package webdev.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import webdev.models.Lesson;
import webdev.models.Module;
import webdev.repositories.LessonRepository;
import webdev.repositories.ModuleRepository;

@CrossOrigin(origins="*") 
@RestController
public class LessonService {

	@Autowired
	ModuleRepository moduleRepository;
	@Autowired
	LessonRepository lessonRepository;

	@GetMapping("/api/lesson")
	public Iterable<Lesson> findAllLessons() {
		return lessonRepository.findAll(); 
	}

	@PostMapping("/api/course/{courseId}/module/{moduleId}/lesson")
	public Lesson createLesson (@PathVariable("courseId") int courseId, @PathVariable("moduleId") int moduleId, @RequestBody Lesson lesson)  {
		Optional<Module> data = moduleRepository.findById(moduleId);
		if(data.isPresent()) {
			Module module = data.get();
			lesson.setModule(module);
			return lessonRepository.save(lesson);
		}
		return null;		
	}

	@GetMapping("/api/course/{courseId}/module/{moduleId}/lesson")
	public List<Lesson> findAllLessonsForModule(@PathVariable("courseId") int courseId, @PathVariable("moduleId") int moduleId) {
		Optional<Module> data = moduleRepository.findById(moduleId);
		if(data.isPresent()) {
			Module module = data.get();
			return module.getLessons();
		}
		return null;		
	}

	@DeleteMapping("/api/lesson/{lessonId}")
	public void deleteLesson(@PathVariable("lessonId") int id) {
		lessonRepository.deleteById(id);
	}

}
