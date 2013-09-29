package coco.model;

import java.util.ArrayList;

public class CCCompileErrorList {
	private String message = "no name";
	private ArrayList<CCCompileError> errors = new ArrayList<CCCompileError>();

	public CCCompileErrorList() {

	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void addError(CCCompileError error) {
		errors.add(error);
	}

	public ArrayList<CCCompileError> getErrors() {
		return errors;
	}
}