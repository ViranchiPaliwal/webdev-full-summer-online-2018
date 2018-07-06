package webdev.models;

import javax.persistence.Entity;

@Entity
public class FillInTheBlankQuestion extends Question {
	private String variables;
	private String expression;
	public String getVariables() {
		return variables;
	}
	public void setVariables(String variables) {
		this.variables = variables;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
}