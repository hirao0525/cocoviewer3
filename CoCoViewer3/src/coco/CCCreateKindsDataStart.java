package coco;

import java.io.IOException;

import coco.controller.CCCompileErrorKindConverter;
import coco.model.CCCompileErrorManager;

public class CCCreateKindsDataStart {

	/**
	 * errorkinds.txtを手動で書くのがめんどくさかったのでCompileError.csvから自動生成するプログラム
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
