package coco.controller;

import java.io.FileWriter;
import java.io.IOException;

import coco.model.CCCompileErrorManager;

public class CCCompileErrorKindConverter extends CCFileLoader {

	CCCompileErrorManager manager;
	private FileWriter out;

	public CCCompileErrorKindConverter(CCCompileErrorManager manager) {
		this.manager = manager;
	}

	public void convertData(String in, String out) {
		out = new FileWriter(outfile);
		out.write("ErrorID,ファイル名,発生時刻,修正時間\n");
		loadData(infile);
		out.close();
	}

	@Override
	protected void separeteData(String line) throws IOException {

	}

}
