package coco;

import java.io.IOException;

public class CCConverterStart {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
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