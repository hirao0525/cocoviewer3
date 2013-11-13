package src.coco.sourcefinder;

import src.coco.model.CCCompileErrorManager;
import clib.common.filesystem.CDirectory;
import clib.common.filesystem.CPath;

public class CCSourceFinder {
	CCCompileErrorManager manager;

	public CCSourceFinder(CCCompileErrorManager manager) {
		this.manager = manager;
	}

	public void findSource(CDirectory baseDir) {
		CPath path = new CPath(
				"ppv.data\\cash\\hoge\\lecture05\\1384325415026\\ProjectBase\\TurtleRace.java");
		baseDir.findOrCreateDirectory(path);
	}
}
