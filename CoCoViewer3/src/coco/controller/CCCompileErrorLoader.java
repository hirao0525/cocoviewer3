package coco.controller;

import coco.model.CCCompileError;
import coco.model.CCCompileErrorManager;

public class CCCompileErrorLoader extends CCFileLoader {

	private CCCompileErrorManager manager;

	public CCCompileErrorLoader(CCCompileErrorManager manager) {
		this.manager = manager;
	}

	public void load(String filename) {
		loadData(filename);
	}

	@Override
	protected void separeteData(String line) {
		CCCompileError error = new CCCompileError();

		// 見づらいため、直接引数に渡す→一次変数に一旦格納してから引数を渡す、に変更
		// データ形式もこの順番に変更
		String[] tokenizer = line.split(",");
		int errorID = Integer.parseInt(tokenizer[0]);
		String filename = tokenizer[1];
		int beginTime = Integer.parseInt(tokenizer[2]);
		int correctTime = Integer.parseInt(tokenizer[3]);

		// System.out.println(beginTime + " " + filename + " " + errorID + " "
		// + correctTime);
		error.setData(errorID, filename, beginTime, correctTime);
		manager.getList(errorID).addError(error);
	}
}