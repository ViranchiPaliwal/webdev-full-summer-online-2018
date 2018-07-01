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
import webdev.models.EssayQuestion;
import webdev.repositories.ExamRepository;
import webdev.repositories.EssayQuestionRepository;

@RestController
@CrossOrigin(origins = "*")
public class EssayService {
	@Autowired
	ExamRepository examRepository;
	@Autowired
	EssayQuestionRepository essayRepository;

	@PostMapping("/api/exam/{examId}/essay")
	public void createFillInTheBlankQuestion(@PathVariable("examId") int id, @RequestBody EssayQuestion essay) {
		Optional<Exam> optionalExam = examRepository.findById(id);
		if(optionalExam.isPresent()) {
			Exam exam = optionalExam.get();
			essay.setExam(exam);
			essayRepository.save(essay);
		}
	}

	@PutMapping("/api/essay/{questionId}")
	public EssayQuestion updateEssayQuestion(@PathVariable("questionId") int questionId, @RequestBody EssayQuestion updatedFillInTheBlank) {
		Optional<EssayQuestion> optionalEssay = essayRepository.findById(questionId);
		if(optionalEssay.isPresent()) {
			EssayQuestion essay = optionalEssay.get();
			essay.setTitle(updatedFillInTheBlank.getTitle());
			essay.setSubtitle(updatedFillInTheBlank.getSubtitle());
			essay.setPoints(updatedFillInTheBlank.getPoints());
			essay.setDescription(updatedFillInTheBlank.getDescription());
			return essayRepository.save(essay);
		}
		return null;
	}

	@DeleteMapping("/api/essay/{questionId}")
	public void deleteEssayQuestion(@PathVariable("questionId") int questionId) {
		essayRepository.deleteById(questionId);
	}
}