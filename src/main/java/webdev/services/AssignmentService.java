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

import webdev.models.Topic;
import webdev.models.Assignment;
import webdev.models.Widget;
import webdev.models.Question;
import webdev.repositories.TopicRepository;
import webdev.repositories.AssignmentRepository;
import webdev.repositories.MultipleChoiceQuestionRepository;
import webdev.repositories.TrueFalseQuestionRepository;


@RestController
@CrossOrigin(origins = "*")
public class AssignmentService {
	@Autowired
	TopicRepository topicRepository;
	@Autowired
	AssignmentRepository assignmentRepository;

	@GetMapping("/api/assignment")	
	public List<Assignment> findAllUsers() {
		return (List<Assignment>) assignmentRepository.findAll();
	}


	@GetMapping("/api/assignment/{assgnId}")
	public Assignment findAssignmentById(@PathVariable("assgnId") int assgnId){
		Optional<Assignment> assignment = assignmentRepository.findById(assgnId);
		if(assignment.isPresent()) {
			return assignment.get();
		}
		throw new NullPointerException("Exam with this id not found.");
	}

	@GetMapping("/api/topic/{topicId}/assignment")
	public List<Assignment> findAllAssignmentForTopic(@PathVariable("topicId") int topicId) {
		Optional<Topic> data = topicRepository.findById(topicId);
		if(data.isPresent()) {
			Topic topic=data.get();
			List<Widget> widgetList = topic.getWidgets();
			List<Assignment> assignments = new ArrayList<Assignment>();
			for (Widget widget: widgetList) {
				if (widget.getWidgetType().equals("Assignment"))
				{
					assignments.add((Assignment) widget);
				}
			}
			return assignments;
		}
		return null;		
	}

	@PostMapping("/api/topic/{topicId}/assignment")
	public Assignment createExam(@PathVariable("topicId") int topicId,@RequestBody Assignment assignment) {
		Optional<Topic> data = topicRepository.findById(topicId);
		if(data.isPresent()) {
			Topic topic=data.get();
			assignment.setTopic(topic);
			return assignmentRepository.save(assignment);
		}
		return null;		
	}

	@DeleteMapping("/api/exam/{assignmentId}")
	public void deleteExam(@PathVariable("assignmentId") int assignmentId) {
		assignmentRepository.deleteById(assignmentId);
	}
}