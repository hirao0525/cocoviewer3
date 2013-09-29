package coco.controller;

import java.io.FileWriter;
import java.io.IOException;

import coco.model.CCCompileErrorManager;

public class CCCompileErrorKindConverter extends CCFileLoader {

	CCCompileErrorManager manager;
	private FileWriter out;
	int errorID = 1;

	public static void main(String args[]) throws IOException {
		CCCompileErrorManager manager = new CCCompileErrorManager();
		CCCompileErrorKindConverter kindconverter = new CCCompileErrorKindConverter(
				manager);
		kindconverter.convertData("testbase/CompileError.csv",
				"testbase/errorkindtest.txt");
	}

	public CCCompileErrorKindConverter(CCCompileErrorManager manager) {
		this.manager = manager;
	}

	public void convertData(String infile, String outfile) throws IOException {
		out = new FileWriter(outfile);
		out.write("ErrorID,エラーメッセージ\n");
		loadData(infile);
		out.close();
	}

	@Override
	protected void separeteData(String line) throws IOException {
		String[] tokanizer = line.split(",");
		if (manager.getMessagesID(tokanizer[3])) {
			manager.put(errorID, tokanizer[3]);
			errorID++;
			out.write(errorID + "," + tokanizer[3] + "\n");
		}
	}
}