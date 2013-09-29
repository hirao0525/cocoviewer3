package coco;

import coco.controller.CCCompileErrorKindConverter;
import coco.model.CCCompileErrorManager;

public class CCMakeKindsDataStart {

	public static void main(String args[]) {

		CCCompileErrorManager manager = new CCCompileErrorManager();
		CCCompileErrorKindConverter kindconverter = new CCCompileErrorKindConverter(
				manager);
		kindconverter.convertData("testbase/CompileError.csv",
				"testbase/errorkindstest.txt");
	}
}
