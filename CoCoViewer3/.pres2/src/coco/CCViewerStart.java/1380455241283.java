package coco;

import coco.controller.CCCompileErrorKindLoader;
import coco.controller.CCCompileErrorLoader;
import coco.model.CCCompileErrorManager;
import coco.view.CCMainFrame2;

public class CCViewerStart {

	/********************************
	 * Viewer main method
	 ********************************/

	public static void main(String[] args) {
		new CCViewerStart().run();
	}

	public void run() {
		CCCompileErrorManager manager = new CCCompileErrorManager();
		CCCompileErrorKindLoader kindloader = new CCCompileErrorKindLoader(
				manager);
		kindloader.load("testbase/errorkinds.txt");

		CCCompileErrorLoader errorloader = new CCCompileErrorLoader(manager);
		errorloader.load("testbase/compileError.log");

		CCMainFrame2 frame = new CCMainFrame2(manager);
		frame.setVisible(true);
	}
}