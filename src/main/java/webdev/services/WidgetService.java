package webdev.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import webdev.models.Widget;


import webdev.repositories.WidgetRepository;



@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class WidgetService {
	@Autowired
	WidgetRepository widgetRepository;

	@GetMapping("/api/widget")
	public Iterable<Widget> findAllWidgets() {
		return widgetRepository.findAll(); 
	}

	@PostMapping("api/widget/save")
	public void saveAllWidgets(@RequestBody List<Widget> widgets) {
		widgetRepository.deleteAll();
		for(Widget widget:widgets) {
			widgetRepository.save(widget);
		}
	}
}
