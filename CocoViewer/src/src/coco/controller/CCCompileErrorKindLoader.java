package src.coco.controller;

import java.util.List;

import src.coco.model.CCCompileErrorManager;

public class CCCompileErrorKindLoader extends CCCsvFileLoader {

	private CCCompileErrorManager manager;
	private int linesNumber = 1;

	public CCCompileErrorKindLoader(CCCompileErrorManager manager) {
		this.manager = manager;
	}

	public void load(String filename) {
		loadData(filename);
	}

	@Override
	protected void separeteData(List<String> lines) {
		int index = Integer.parseInt(lines.get(0));
		int rare = Integer.parseInt(lines.get(1));
		String message = lines.get(2);

		manager.put(index, rare, message);
		linesNumber++;
	}

	// �ŏ��̏�ԂŒǉ������G���[�̐���ۑ����Ă���
	public int getLines() {
		return linesNumber;
	}

	// protected void separeteData(String line) {
	// // TODO Auto-generated method stub
	// StringTokenizer st = new StringTokenizer(line, ",");
	//
	// int index = Integer.parseInt(st.nextToken().toString());
	// String message = st.nextToken().toString();
	// // System.out.println(index + " : " + message);
	// manager.put(index, message);
	// }

}