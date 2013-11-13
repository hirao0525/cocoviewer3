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

		// Œ©‚Ã‚ç‚¢‚½‚ßA’¼Úˆø”‚É“n‚·¨ˆêŸ•Ï”‚Éˆê’UŠi”[‚µ‚Ä‚©‚çˆø”‚ğ“n‚·A‚É•ÏX
		String[] tokenizer = line.split(",");
		int errorID;
		String projectname = "";
		String filename;
		long beginTime;
		int correctTime;

		// TODO ‚à‚¤­‚µ‚«‚ê‚¢‚É•`‚­
		if (tokenizer.length == 5) {
			errorID = Integer.parseInt(tokenizer[0]);
			projectname = tokenizer[1];
			filename = tokenizer[2];
			beginTime = Long.parseLong(tokenizer[3]);
			correctTime = Integer.parseInt(tokenizer[4]);
		} else {
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