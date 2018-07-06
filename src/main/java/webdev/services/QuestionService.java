package webdev.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import webdev.models.Question;
import webdev.models.Exam;

import webdev.repositories.ExamRepository;
import webdev.repositories.QuestionRepository;

@RestController
@CrossOrigin(origins = "*")
public class QuestionService {
	@Autowired
	private ExamRepository examRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@GetMapping("/api/exam/{examId}/question")
	public List<Question> findAllQuestionsForExamId(@PathVariable("examId") int examId) {
		Optional<Exam> optionalExam = examRepository.findById(examId);
		if(optionalExam.isPresent()) {
			 Exam exam = optionalExam.get();
			 return exam.getQuestions();
		 }
		 return null;
	}
	
	@DeleteMapping("api/question/{questionId}")
	public void deleteQuestion(@PathVariable("questionId") int questionId) {
		questionRepository.deleteById(questionId);
	}
}