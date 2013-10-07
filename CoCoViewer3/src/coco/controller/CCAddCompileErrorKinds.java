package coco.controller;

import java.io.FileWriter;
import java.io.IOException;

import coco.model.CCCompileErrorManager;

public class CCAddCompileErrorKinds {
	private CCCompileErrorManager manager;
	private int lines;

	public CCAddCompileErrorKinds(CCCompileErrorManager manager, int lines) {
		this.manager = manager;
		this.lines = lines;
	}

	public void addKinds(String filename) throws IOException {
		FileWriter writer = new FileWriter(filename, true);
		while (manager.getAllLists().size() >= lines) {
			String errorID = Integer.toString(lines);
			String message = manager.getList(lines).getMessage();

			writer.write(errorID + "," + 6 + "," + message + "\n");
			lines++;
		}

		writer.close();
	}
}