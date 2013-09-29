package coco;

import java.io.IOException;

import coco.controller.CCCompileErrorKindConverter;
import coco.model.CCCompileErrorManager;

public class CCCreateKindsDataStart {

	public static void main(String args[]) throws IOException {
		CCCompileErrorManager manager = new CCCompileErrorManager();
		CCCompileErrorKindConverter kindconverter = new CCCompileErrorKindConverter(
				manager);
		kindconverter.convertData("testbase/CompileError.csv",
				"testbase/errorkinds.txt");
		System.out.println("errorkinds.txt created!");
	}
}
