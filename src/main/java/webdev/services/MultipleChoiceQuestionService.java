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
import webdev.models.MultipleChoiceQuestion;
import webdev.repositories.ExamRepository;
import webdev.repositories.MultipleChoiceQuestionRepository;

@RestController
@CrossOrigin(origins = "*")
public class MultipleChoiceQuestionService {
	@Autowired
	ExamRepository examRepository;
	@Autowired
	MultipleChoiceQuestionRepository mcqRepository;

	@PostMapping("/api/exam/{examId}/choice")
	public void createMultipleChoiceQuestion(@PathVariable("examId") int id, @RequestBody MultipleChoiceQuestion mcq) {
		Optional<Exam> optionalExam = examRepository.findById(id);
		if(optionalExam.isPresent()) {
			Exam exam = optionalExam.get();
			mcq.setExam(exam);
			mcqRepository.save(mcq);
		}
	}

	@PutMapping("/api/choice/{questionId}")
	public MultipleChoiceQuestion updateMultipleChoiceQuestion(@PathVariable("questionId") int questionId, @RequestBody MultipleChoiceQuestion updatedMultipleChoice) {
		Optional<MultipleChoiceQuestion> optionalMcq = mcqRepository.findById(questionId);
		if(optionalMcq.isPresent()) {
			MultipleChoiceQuestion mcq = optionalMcq.get();
			mcq.setTitle(updatedMultipleChoice.getTitle());
			mcq.setSubtitle(updatedMultipleChoice.getSubtitle());
			mcq.setPoints(updatedMultipleChoice.getPoints());
			mcq.setCorrectOption(updatedMultipleChoice.getCorrectOption());
			mcq.setOptions(updatedMultipleChoice.getOptions());
			mcq.setDescription(updatedMultipleChoice.getDescription());
			return mcqRepository.save(mcq);
		}
		return null;
	}

	@DeleteMapping("/api/choice/{questionId}")
	public void deleteMultipleChoiceQuestion(@PathVariable("questionId") int questionId) {
		mcqRepository.deleteById(questionId);
	}
}