package webdev.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.Exam;
import webdev.models.TrueFalseQuestion;
import webdev.repositories.ExamRepository;
import webdev.repositories.TrueFalseQuestionRepository;

@RestController
@CrossOrigin(origins = "*")
public class TrueFalseService {
	@Autowired
	ExamRepository examRepository;
	@Autowired
	TrueFalseQuestionRepository trueFalseRepository;

	@PostMapping("/api/exam/{examId}/truefalse")
	public void createTrueFalseQuestion(@PathVariable("examId") int id, @RequestBody TrueFalseQuestion trueFalse) {
		Optional<Exam> optionalExam = examRepository.findById(id);
		if(optionalExam.isPresent()) {
			Exam exam = optionalExam.get();
			trueFalse.setExam(exam);
			trueFalseRepository.save(trueFalse);
		}
	}

	@PutMapping("/api/truefalse/{questionId}")
	public TrueFalseQuestion updateTrueFalseQuestion(@PathVariable("questionId") int questionId, @RequestBody TrueFalseQuestion updatedTrueFalse) {
		Optional<TrueFalseQuestion> optionaltrueFalse = trueFalseRepository.findById(questionId);
		if(optionaltrueFalse.isPresent()) {
			TrueFalseQuestion trueFalse = optionaltrueFalse.get();
			trueFalse.setTitle(updatedTrueFalse.getTitle());
			trueFalse.setSubtitle(updatedTrueFalse.getSubtitle());
			trueFalse.setPoints(updatedTrueFalse.getPoints());
			trueFalse.setIsTrue(updatedTrueFalse.getIsTrue());
			return trueFalseRepository.save(trueFalse);
		}
		return null;
	}

	@DeleteMapping("/api/truefalse/{questionId}")
	public void deleteTrueFalseQuestion(@PathVariable("questionId") int questionId) {
		trueFalseRepository.deleteById(questionId);
	}
}