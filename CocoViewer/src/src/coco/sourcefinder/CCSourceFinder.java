package src.coco.sourcefinder;

import src.coco.model.CCCompileError;
import src.coco.model.CCCompileErrorManager;
import clib.common.filesystem.CDirectory;
import clib.common.filesystem.CPath;

public class CCSourceFinder {
	CCCompileErrorManager manager;

	public CCSourceFinder(CCCompileErrorManager manager) {
		this.manager = manager;
	}

	public void findSource(CDirectory baseDir, CCCompileError compileError) {
		String projectname = compileError.getProjectname();
		String beginTime = String.valueOf(compileError.getBeginTime());
		String filename = compileError.getFilename();
		CPath path = new CPath("ppv.data\\cash\\hoge\\" + projectname + "\\"
				+ beginTime + "\\ProjectBase\\" + filename);
		if (baseDir.findFile(path) != null) {
			System.out.println("find!");
		}
	}
}
