package src.coco.controller;

import src.coco.model.CCCompileError;
import src.coco.model.CCCompileErrorManager;

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
		String[] tokenizer = line.split(",");
		int errorID;
		String projectname = "";
		String filename;
		long beginTime;
		int correctTime;

		// TODO �����������ꂢ�ɕ`��
		if (tokenizer.length == 5) {
			// �_�v������ file�܂ł��t���p�X�̑z��
			errorID = Integer.parseInt(tokenizer[0]);
			projectname = tokenizer[1];
			filename = tokenizer[2];
			beginTime = Long.parseLong(tokenizer[3]);
			correctTime = Integer.parseInt(tokenizer[4]);
		} else {
			// CocoViewerStart����
			errorID = Integer.parseInt(tokenizer[0]);
			filename = tokenizer[1];
			beginTime = Long.parseLong(tokenizer[2]);
			correctTime = Integer.parseInt(tokenizer[3]);
		}

		error.setData(errorID, projectname, filename, beginTime, correctTime);
		manager.getList(errorID).addError(error);
		manager.totalErrorCountUp();
	}
}