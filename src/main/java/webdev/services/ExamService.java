package webdev.services;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.Exam;
import webdev.models.Lesson;
import webdev.models.MultipleChoiceQuestion;
import webdev.models.Question;
import webdev.models.Topic;
import webdev.models.TrueFalseQuestion;
import webdev.models.User;
import webdev.models.Widget;
import webdev.repositories.ExamRepository;
import webdev.repositories.TopicRepository;
import webdev.repositories.MultipleChoiceQuestionRepository;
import webdev.repositories.TrueFalseQuestionRepository;
import webdev.repositories.WidgetRepository;


@RestController
@CrossOrigin(origins = "*")
public class ExamService {
	@Autowired
	ExamRepository examRepository;
	@Autowired
	TrueFalseQuestionRepository trueFalseRepository;
	@Autowired
	MultipleChoiceQuestionRepository mutiRepo;
	@Autowired
	TopicRepository topicRepository;

	@GetMapping("/api/exam")	
	public List<Exam> findAllUsers() {
		return (List<Exam>) examRepository.findAll();
	}
	

	@GetMapping("/api/exam/{examId}")
	public Exam findUserById(@PathVariable("examId") int id){
		Optional<Exam> exam = examRepository.findById(id);
		if(exam.isPresent()) {
			return exam.get();
		}
		 throw new NullPointerException("Exam with this id not found.");
	}
	
	@GetMapping("/api/topic/{topicId}/exam")
	public List<Topic> findAllExamForTopic(
			@PathVariable("topicId") int topicId) {
		Optional<Topic> data =
				topicRepository.findById(topicId);
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
		}
		return null;		
	}
	
	@GetMapping("/api/multi/{questionId}")
	public MultipleChoiceQuestion findMultiQuestionById(@PathVariable("questionId") int questionId) {
		Optional<MultipleChoiceQuestion> optional = mutiRepo.findById(questionId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@GetMapping("/api/truefalse/{questionId}")
	public TrueFalseQuestion findTrueFalseQuestionById(@PathVariable("questionId") int questionId) {
		Optional<TrueFalseQuestion> optional = trueFalseRepository.findById(questionId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	@GetMapping("/api/exam/{examId}/question")
	public List<Question> findAllQuestionsForExam(@PathVariable("examId") int examId) {
		Optional<Exam> optionalExam = examRepository.findById(examId);
		if(optionalExam.isPresent()) {
			Exam exam = optionalExam.get();
			List<Question> questions = exam.getQuestions();
			int count = questions.size();
			return questions;
		}
		return null;
	}
	
	
}