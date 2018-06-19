package webdev.models;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Widget implements Comparable<Widget>{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String text;
	private String widgetType;
	private int size;
	private String widgetName;
	private String imageLink;
	private String listContent;
	private String listType;
	private String linkContent;
	private String linkUrl;
	private int widgetPos; 
	@ManyToOne
	@JsonIgnore
	private Topic topic;
	
	public int getWidgetPos() {
		return widgetPos;
	}
	public void setWidgetPos(int widgetPos) {
		this.widgetPos = widgetPos;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	public String getWidgetName() {
		return widgetName;
	}
	public void setWidgetName(String widgetName) {
		this.widgetName = widgetName;
	}
	public String getImageLink() {
		return imageLink;
	}
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	public String getListContent() {
		return listContent;
	}
	public void setListContent(String listContent) {
		this.listContent = listContent;
	}
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	public String getLinkContent() {
		return linkContent;
	}
	public void setLinkContent(String linkContent) {
		this.linkContent = linkContent;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public String getParaContent() {
		return paraContent;
	}
	public void setParaContent(String paraContent) {
		this.paraContent = paraContent;
	}
	private String paraContent;
	
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	
	public int getSize() {
		return size;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getWidgetType() {
		return widgetType;
	}
	public void setWidgetType(String widgetType) {
		this.widgetType = widgetType;
	}
	
	@Override
	public int compareTo(Widget o) {
		return this.getWidgetPos()-o.getWidgetPos();
	}
}

