package webdev.models;

import javax.persistence.Entity;

@Entity
public class FillInTheBlankQuestion extends Question {
	private String variables;
	private String values;
	public String getVariables() {
		return variables;
	}
	public void setVariables(String variables) {
		this.variables = variables;
	}
	public String getValues() {
		return values;
	}
	public void setValues(String values) {
		this.values = values;
	}
}