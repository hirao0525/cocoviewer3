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
		int errorID = Integer.parseInt(tokenizer[0]);
		String filename = tokenizer[1];
		long beginTime = Long.parseLong(tokenizer[2]);
		int correctTime = Integer.parseInt(tokenizer[3]);

		error.setData(errorID, filename, beginTime, correctTime);
		manager.getList(errorID).addError(error);
		manager.totalErrorCountUp();
	}
}