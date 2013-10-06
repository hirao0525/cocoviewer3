package coco.controller;

import coco.model.CCCompileErrorManager;

public class CCCompileErrorKindLoader extends CCFileLoader {

	private CCCompileErrorManager manager;
	private int lines = 0;

	public CCCompileErrorKindLoader(CCCompileErrorManager manager) {
		this.manager = manager;
	}

	public void load(String filename) {
		loadData(filename);
	}

	@Override
	protected void separeteData(String line) {
		String[] tokenizer = line.split(",");
		int index = Integer.parseInt(tokenizer[0]);
		int rare = Integer.parseInt(tokenizer[1]);
		String message = tokenizer[2];

		System.out.println(index + " : " + rare + " : " + message);
		manager.put(index, rare, message);
		lines++;
	}

	public int getLines() {
		return lines;
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