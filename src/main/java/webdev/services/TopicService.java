package webdev.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import webdev.models.Module;
import webdev.models.Topic;
import webdev.models.Lesson;


import webdev.repositories.LessonRepository;
import webdev.repositories.ModuleRepository;
import webdev.repositories.TopicRepository;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TopicService {
	@Autowired
	LessonRepository lessonRepository;
	
	@Autowired
	TopicRepository topicRepository;
	
	@PostMapping("/api/course/{cid}/module/{mid}/lesson/{lid}/topic")
	public Topic createTopic(@PathVariable("lid") int lessonId,
			@RequestBody Topic newTopic) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if(data.isPresent()) {
			Lesson lesson = data.get();
			newTopic.setLesson(lesson);
			return topicRepository.save(newTopic);
		}
		return null;
	}
	
	@GetMapping("/api/course/{cid}/module/{mid}/lesson/{lid}/topic")
	public List<Topic> findAllTopicsForLesson(
			@PathVariable("lid") int lessonId) {
		Optional<Lesson> data =
				lessonRepository.findById(lessonId);
		if(data.isPresent()) {
			Lesson lesson = data.get();
			return lesson.getTopics();
		}
		return null;		
	}
	
	@DeleteMapping("/api/topic/{tid}")
	public void deleteTopic(
	  @PathVariable("tid") int topicId) {
		topicRepository.deleteById(topicId);
	}
	
	@GetMapping("/api/topic")
	public Iterable<Topic> findAllTopics() {
		return topicRepository.findAll(); 
	}

	
	
}
