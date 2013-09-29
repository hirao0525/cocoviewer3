package coco.controller;

import java.io.FileWriter;
import java.io.IOException;

import coco.model.CCCompileErrorManager;

public class CCCompileErrorMaker extends CCFileLoader {

	private CCCompileErrorManager manager;
	private FileWriter out;

	public CCCompileErrorMaker() {

	}

	public CCCompileErrorMaker(CCCompileErrorManager manager) {
		this.manager = manager;
	}

	public void makeData(String infile, String outfile) throws IOException {
		out = new FileWriter(outfile);
		out.write("ErrorID,ファイル名,発生時刻,修正時間\n");
		loadData(infile);
		out.close();
	}

	protected void separeteData(String line) throws IOException {

		String[] tokenizer = line.split(",");
		// TODO: CSVを扱うことが出来るライブラリを用いた形に変更する
		// errorIDはmessageListをmanagerに作ってindexOfメソッドで解決
		int errorID = manager.getMessagesID(tokenizer[3]);
		String filename = tokenizer[2];

		// TODO: 変換方式を考えること
		// play(java.util.ArrayList<java.lang.String>,java.util.ArrayList<java.lang.Integer>)
		// が','のときに引っかかる なんとかして対策すること
		// 配列の長さから無理やり持ってくることで対応した

		// 2010/06/30 18:59:04 形式を int 型に
		// TODO: 後で
		// long beginTime = calculationBeginTime(tokenizer[tokenizer.length -
		// 8]);
		int beginTime = 0;
		// 修正時間は取り出して時間を計算することに成功した
		int correctTime = calculationCorrectTime(tokenizer[tokenizer.length - 6]);

		// debug
		System.out.println(errorID + "," + filename + "," + beginTime + ","
				+ correctTime);

		out.write(errorID + "," + filename + "," + beginTime + ","
				+ correctTime + "\n");
	}

	// private int calculationBeginTime(String data) {
	// String[] tokenizer = data.split(" ");
	// String[] dates = tokenizer[0].split("/");
	// String[] times = tokenizer[1].split(":");
	//
	// int year = Integer.parseInt(dates[0]);
	// int month = Integer.parseInt(dates[1]);
	// int day = Integer.parseInt(dates[2]);
	// int hour = Integer.parseInt(times[0]);
	// int minute = Integer.parseInt(times[1]);
	// int second = Integer.parseInt(times[2]);
	//
	// Calendar calender = Calendar.getInstance();
	// calender.set(year, month, day, hour, minute, second);
	//
	// return (int) calender.getTimeInMillis();
	// }

	private int calculationCorrectTime(String time) {
		String[] tokanizer = time.split(":");
		int hour = Integer.parseInt(tokanizer[0]) * 3600;
		int minute = Integer.parseInt(tokanizer[1]) * 60;
		int second = Integer.parseInt(tokanizer[2]);
		return hour + minute + second;
	}
}