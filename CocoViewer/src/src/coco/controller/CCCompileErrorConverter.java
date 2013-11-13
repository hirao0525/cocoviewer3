package src.coco.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;

import src.coco.model.CCCompileErrorManager;

public class CCCompileErrorConverter extends CCCsvFileLoader {

	private CCCompileErrorManager manager;
	private File out;
	private PrintWriter pw;
	private int addErrorID;
	private String CAMMA = ",";

	public CCCompileErrorConverter(CCCompileErrorManager manager) {
		this.manager = manager;
		addErrorID = manager.getAllLists().size() + 1;
	}

	public void convertData(String infile, String outfile) throws IOException {
		out = new File(outfile);
		pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(out), "sjis")));
		inputHeader(out);
		loadData(infile);
		pw.flush();
		pw.close();
	}

	private void inputHeader(File outFile) throws IOException {
		StringBuffer buf = new StringBuffer();

		buf.append("ErrorID");
		buf.append(CAMMA);
		buf.append("ファイル名");
		buf.append(CAMMA);
		buf.append("発生時刻");
		buf.append(CAMMA);
		buf.append("修正時間");
		// buf.append("ErrorID,ファイル名,発生時刻,修正時間");
		pw.println(buf.toString());
	}

	protected void separeteData(List<String> lines) throws IOException {
		StringBuffer buf = new StringBuffer();

		// errorIDはmessageListをmanagerに作ってindexOfメソッドで解決
		// 存在していないerrorIDの場合、新しくエラーメッセージを記録する
		// 先にシンボルなどのチェックをしてからgetMessageIDをする形にし、（シンボル）などに対応した
		int errorID = 0;
		String element = "";
		if (lines.get(7) != null) {
			element = "（" + lines.get(7) + "）";
		}

		String message = lines.get(5) + element;

		try {
			errorID = manager.getMessagesID(message);
		} catch (Exception e) {
			errorID = addErrorID;
			manager.put(errorID, 6, message);
			addErrorID++;
		}

		String filename = lines.get(4);

		long beginTime = calculationBeginTime(lines.get(14));
		// long beginTime = 0;

		// 修正時間は取り出して時間を計算することに成功した
		int correctTime = calculationCorrectTime(lines.get(16));

		// debug
		// System.out.println(errorID + "," + filename + "," + beginTime + ","
		// + correctTime);

		buf.append(String.valueOf(errorID));
		buf.append(CAMMA);
		buf.append(filename);
		buf.append(CAMMA);
		buf.append(String.valueOf(beginTime));
		buf.append(CAMMA);
		buf.append(String.valueOf(correctTime));
		pw.println(buf.toString());
		// out.write(errorID + "," + filename + "," + beginTime + ","
		// + correctTime + "\n");
	}

	private long calculationBeginTime(String data) {
		String[] tokenizer = data.split(" ");
		String[] dates = tokenizer[0].split("/");
		String[] times = tokenizer[1].split(":");

		int year = Integer.parseInt(dates[0]);
		int month = Integer.parseInt(dates[1]);
		int day = Integer.parseInt(dates[2]);
		int hour = Integer.parseInt(times[0]);
		int minute = Integer.parseInt(times[1]);
		// int second = Integer.parseInt(times[2]);
		int second = 0;

		Calendar calender = Calendar.getInstance();
		calender.set(year, month, day, hour, minute, second);

		return calender.getTimeInMillis();
	}

	private int calculationCorrectTime(String time) {
		String[] tokanizer = time.split(":");
		int hour = Integer.parseInt(tokanizer[0]) * 3600;
		int minute = Integer.parseInt(tokanizer[1]) * 60;
		int second = Integer.parseInt(tokanizer[2]);
		return hour + minute + second;
	}
}