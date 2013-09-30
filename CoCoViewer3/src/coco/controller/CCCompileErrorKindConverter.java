package coco.controller;

import java.io.FileWriter;
import java.io.IOException;

import coco.model.CCCompileErrorManager;

/**
 * CompileError.csvからerrorKinds.txtを作るプログラム本体
 * 
 * @author student
 * 
 */
public class CCCompileErrorKindConverter extends CCFileLoader {

	CCCompileErrorManager manager;
	private FileWriter out;
	int errorID = 1;

	public CCCompileErrorKindConverter(CCCompileErrorManager manager) {
		this.manager = manager;
	}

	public void convertData(String infile, String outfile) throws IOException {
		out = new FileWriter(outfile);
		out.write("ErrorID,エラーメッセージ\n");
		loadData(infile);
		out.close();
	}

	@Override
	protected void separeteData(String line) throws IOException {
		String[] tokanizer = line.split(",");

		// getMessageIDしてnullなら未登録なので登録して書き込む
		try {
			if (manager.getMessagesID(tokanizer[5]) <= 0) {
				System.out.println("error!");
			}
		} catch (Exception e) {
			manager.put(errorID, tokanizer[5]);
			out.write(errorID + "," + tokanizer[5] + "\n");
			errorID++;
		}
	}
}