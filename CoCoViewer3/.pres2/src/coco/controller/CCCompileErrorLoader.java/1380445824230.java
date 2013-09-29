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

		// ���Â炢���߁A���ڈ����ɓn�����ꎟ�ϐ��Ɉ�U�i�[���Ă��������n���A�ɕύX
		// �f�[�^�`�������̏��ԂɕύX
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

	// protected void separeteData(String line) {
	// StringTokenizer tokenizer = new StringTokenizer(line, ",");
	// CCCompileError error = new CCCompileError();
	// int tokenlength = tokenizer.countTokens();
	//
	// // ���Â炢���߁A���ڈ����ɓn�����ꎟ�ϐ��Ɉ�U�i�[���Ă��������n���A�ɕύX
	// int beginTime = Integer.parseInt(tokenizer.nextToken().toString());
	// String filename = tokenizer.nextToken().toString();
	// int errorID = Integer.parseInt(tokenizer.nextToken().toString());
	// int correctTime = Integer.parseInt(tokenizer.nextToken().toString());
	//
	// // System.out.println(beginTime + " " + filename + " " + errorID + " "
	// // + correctTime);
	// for (int i = 0; i < tokenlength; i++) {
	// error.setData(errorID, filename, beginTime, correctTime);
	// }
	//
	// manager.getList(errorID).addError(error);
	// }
}