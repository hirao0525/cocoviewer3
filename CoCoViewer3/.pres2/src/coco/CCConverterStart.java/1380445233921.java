package coco;

import java.io.IOException;

import coco.controller.CCCompileErrorKindLoader;
import coco.controller.CCCompileErrorMaker;
import coco.model.CCCompileErrorManager;

public class CCConverterStart {

	/***********************
	 * 
	 * Converter Start
	 * 
	 * @throws IOException
	 */

	public static void main(String[] args) throws IOException {
		new CCConverterStart().run();
	}

	private void run() throws IOException {
		CCCompileErrorManager manager = new CCCompileErrorManager();
		CCCompileErrorKindLoader kindloader = new CCCompileErrorKindLoader(
				manager);
		kindloader.load("testbase/errorkinds.txt");

		CCCompileErrorMaker errormaker = new CCCompileErrorMaker(manager);
		errormaker.makeData("testbase/compileError.csv",
				"testbase/compileError.log");
	}
}