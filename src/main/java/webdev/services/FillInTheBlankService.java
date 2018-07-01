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
import webdev.models.FillInTheBlankQuestion;
import webdev.repositories.ExamRepository;
import webdev.repositories.FillInTheBlankQuestionRepository;

@RestController
@CrossOrigin(origins = "*")
public class FillInTheBlankService {
	@Autowired
	ExamRepository examRepository;
	@Autowired
	FillInTheBlankQuestionRepository fillInTheBlankRepository;

	@PostMapping("/api/exam/{examId}/blanks")
	public void createFillInTheBlankQuestion(@PathVariable("examId") int id, @RequestBody FillInTheBlankQuestion fillInTheBlank) {
		Optional<Exam> optionalExam = examRepository.findById(id);
		if(optionalExam.isPresent()) {
			Exam exam = optionalExam.get();
			fillInTheBlank.setExam(exam);
			fillInTheBlankRepository.save(fillInTheBlank);
		}
	}

	@PutMapping("/api/blanks/{questionId}")
	public FillInTheBlankQuestion updateFillInTheBlankQuestion(@PathVariable("questionId") int questionId, @RequestBody FillInTheBlankQuestion updatedFillInTheBlank) {
		Optional<FillInTheBlankQuestion> optionalFillInTheBlank = fillInTheBlankRepository.findById(questionId);
		if(optionalFillInTheBlank.isPresent()) {
			FillInTheBlankQuestion fillInTheBlank = optionalFillInTheBlank.get();
			fillInTheBlank.setTitle(updatedFillInTheBlank.getTitle());
			fillInTheBlank.setSubtitle(updatedFillInTheBlank.getSubtitle());
			fillInTheBlank.setPoints(updatedFillInTheBlank.getPoints());
			fillInTheBlank.setValues(updatedFillInTheBlank.getValues());
			fillInTheBlank.setVariables(updatedFillInTheBlank.getVariables());
			return fillInTheBlankRepository.save(fillInTheBlank);
		}
		return null;
	}

	@DeleteMapping("/api/blanks/{questionId}")
	public void deleteFillInTheBlankQuestion(@PathVariable("questionId") int questionId) {
		fillInTheBlankRepository.deleteById(questionId);
	}
}