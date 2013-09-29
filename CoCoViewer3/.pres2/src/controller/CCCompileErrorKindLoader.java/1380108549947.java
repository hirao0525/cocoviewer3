package coco.controller;


public class CCCompileErrorKindLoader extends CCFileLoader {

	private CCCompileErrorManager manager;

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
		String message = tokenizer[1];

		manager.put(index, message);
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