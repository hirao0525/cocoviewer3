package coco;

import java.io.IOException;

import coco.controller.CCAddCompileErrorKinds;
import coco.controller.CCCompileErrorConverter;
import coco.controller.CCCompileErrorKindLoader;
import coco.model.CCCompileErrorManager;

public class CCConverterStart {

	/**
	 * Converter Start
	 * 
	 * @throws IOException
	 */

	public static void main(String[] args) throws IOException {
		new CCConverterStart().run();
	}

	public void run() throws IOException {
		CCCompileErrorManager manager = new CCCompileErrorManager();
		CCCompileErrorKindLoader kindloader = new CCCompileErrorKindLoader(
				manager);
		kindloader.load("ErrorKinds.csv");

		CCCompileErrorConverter errorconverter = new CCCompileErrorConverter(
				manager);
		errorconverter.convertData("CompileError.csv", "CompileErrorLog.csv");

		CCAddCompileErrorKinds addcompileerrorkinds = new CCAddCompileErrorKinds(
				manager, kindloader.getLines());
		addcompileerrorkinds.addKinds("ErrorKinds.csv", "MyErrorKinds.csv");
	}
}