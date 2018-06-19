package webdev.services;

import java.util.List;
import java.util.Optional;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import webdev.models.Widget;
import webdev.models.Topic;

import webdev.repositories.WidgetRepository;
import webdev.repositories.TopicRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class WidgetService {
	@Autowired
	WidgetRepository widgetRepository;

	@Autowired
	private TopicRepository topicRepository;

	@GetMapping("/api/widget")
	public List<Widget> findAllWidgets() {
		return (List<Widget>) widgetRepository.findAll();
	}

	@GetMapping("/api/widget/{widgetId}")
	public Widget getWidgetById(@PathVariable("widgetId") int widgetId) {
		Optional<Widget> widget = widgetRepository.findById(widgetId);
		if (widget.isPresent()) {
			return widget.get();
		}
		return null;
	}

	@GetMapping("api/topic/{topicId}/widget")
	public List<Widget> getWidgetsByTopicId(@PathVariable("topicId") int topicId) {
		Optional<Topic> topic = topicRepository.findById(topicId);
		if (topic.isPresent()) {
			List<Widget> widgets = topic.get().getWidgets();
			Collections.sort(widgets);
			return widgets;
		}
		return null;
	}

	@PostMapping("api/topic/{topicId}/widget")
	public void saveAllWidgets(@PathVariable("topicId") int topicId, @RequestBody List<Widget> widgets) {
		Optional<Topic> topic = topicRepository.findById(topicId);
		if (topic.isPresent()) {
			List<Widget> oldWidgetlist = topic.get().getWidgets();
			for (Widget oldWidget : oldWidgetlist) {
				widgetRepository.deleteById(oldWidget.getId());
			}
			for (Widget newWidget : widgets) {
				newWidget.setTopic(topic.get());
				widgetRepository.save(newWidget);
			}
		}
	}

	@PutMapping("/api/widget/{widgetId}")
	public Widget updateWidget(@PathVariable("widgetId") int widgetId, @RequestBody Widget newWidget) {
		Optional<Widget> data = widgetRepository.findById(widgetId);
		if (data.isPresent()) {
			Widget widget = data.get();
			widget.setText(newWidget.getText());
			widget.setListType(newWidget.getListType());
			widget.setLinkUrl(newWidget.getLinkUrl());
			widget.setWidgetType(newWidget.getWidgetType());
			widget.setImageLink(newWidget.getImageLink());
			widget.setWidgetName(newWidget.getWidgetName());
			widget.setListContent(newWidget.getListContent());
			widget.setText(newWidget.getText());
			widget.setTopic(newWidget.getTopic());
			widget.setLinkContent(newWidget.getLinkContent());
			widget.setWidgetPos(newWidget.getWidgetPos());
			return widget;
		}
		return null;
	}

	@PostMapping("api/widget/save")
	public void saveAllWidgets(@RequestBody List<Widget> widgets) {
		widgetRepository.deleteAll();
		for (Widget widget : widgets) {
			widgetRepository.save(widget);
		}
	}

	@DeleteMapping("/api/widget/{widgetId}")
	public void deleteWidget(@PathVariable("widgetId") int widgetId) {
		widgetRepository.deleteById(widgetId);
	}
}
