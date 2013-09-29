package coco;

import coco.controller.CCCompileErrorKindLoader;
import coco.controller.CCCompileErrorLogLoader;
import coco.model.CCCompileErrorManager;
import coco.view.CCMainFrame;

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

		CCCompileErrorLogLoader errorlogloader = new CCCompileErrorLogLoader(
				manager);
		errorlogloader.load("testbase/compileError.log");

		CCMainFrame frame = new CCMainFrame(manager);
		frame.setVisible(true);
	}
}