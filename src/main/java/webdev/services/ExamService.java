package webdev.services;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.Exam;
import webdev.models.Topic;
import webdev.models.Widget;
import webdev.models.Question;
import webdev.repositories.ExamRepository;
import webdev.repositories.TopicRepository;
import webdev.repositories.MultipleChoiceQuestionRepository;
import webdev.repositories.TrueFalseQuestionRepository;


@RestController
@CrossOrigin(origins = "*")
public class ExamService {
	@Autowired
	ExamRepository examRepository;
	@Autowired
	TopicRepository topicRepository;

	@GetMapping("/api/exam")	
	public List<Exam> findAllExam() {
		return (List<Exam>) examRepository.findAll();
	}


	@GetMapping("/api/exam/{examId}")
	public Exam findExamById(@PathVariable("examId") int id){
		Optional<Exam> exam = examRepository.findById(id);
		if(exam.isPresent()) {
			return exam.get();
		}
		throw new NullPointerException("Exam with this id not found.");
	}

	@GetMapping("/api/topic/{topicId}/exam")
	public List<Exam> findAllExamForTopic( @PathVariable("topicId") int topicId) {
		Optional<Topic> data = topicRepository.findById(topicId);
		if(data.isPresent()) {
			Topic topic=data.get();
			List<Widget> widgetList = topic.getWidgets();
			List<Exam> exams = new ArrayList<Exam>();
			for (Widget widget: widgetList) {
				if (widget.getWidgetType().equals("Exam"))
				{
					exams.add((Exam) widget);
				}
			}
			return exams;
		}
		return null;		
	}

	@PostMapping("/api/topic/{topicId}/exam")
	public Exam createExam(@PathVariable("topicId") int topicId,@RequestBody Exam exam) {
		Optional<Topic> data = topicRepository.findById(topicId);
		if(data.isPresent()) {
			Topic topic=data.get();
			exam.setTopic(topic);
			return examRepository.save(exam);
		}
		return null;		
	}

	@DeleteMapping("/api/exam/{examId}")
	public void deleteExam(@PathVariable("examId") int examId) {
		examRepository.deleteById(examId);
	}
}