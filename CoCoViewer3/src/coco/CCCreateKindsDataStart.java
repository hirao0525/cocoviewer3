package coco;

import java.io.IOException;

import coco.controller.CCCompileErrorKindConverter;
import coco.model.CCCompileErrorManager;

public class CCCreateKindsDataStart {

	/**
	 * errorkinds.txt‚ğè“®‚Å‘‚­‚Ì‚ª‚ß‚ñ‚Ç‚­‚³‚©‚Á‚½‚Ì‚ÅCompileError.csv‚©‚ç©“®¶¬‚·‚éƒvƒƒOƒ‰ƒ€
	 * 
	 * @throws IOException
	 */

	public static void main(String args[]) throws IOException {
		CCCompileErrorManager manager = new CCCompileErrorManager();
		CCCompileErrorKindConverter kindconverter = new CCCompileErrorKindConverter(
				manager);
		kindconverter.convertData("testbase/CompileError.csv",
				"testbase/errorkinds.txt");
		System.out.println("Debug Message : errorkinds.txt created!");
	}
}
