package coco.controller;

import java.io.IOException;

import coco.model.CCCompileErrorManager;

public class CCCompileErrorKindConverter extends CCFileLoader {

	CCCompileErrorManager manager;

	public CCCompileErrorKindConverter(CCCompileErrorManager manager) {
		this.manager = manager;
	}

	@Override
	protected void separeteData(String line) throws IOException {

	}

}
