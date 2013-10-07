package coco.controller;

import java.io.FileWriter;
import java.io.IOException;

import coco.model.CCCompileErrorManager;

public class CCCompileErrorConverter extends CCFileLoader {

	private CCCompileErrorManager manager;
	private FileWriter out;
	private int addErrorID;

	public CCCompileErrorConverter(CCCompileErrorManager manager) {
		this.manager = manager;
		addErrorID = manager.getAllLists().size() + 1;
	}

	public void convertData(String infile, String outfile) throws IOException {
		out = new FileWriter(outfile);
		out.write("ErrorID,�t�@�C����,��������,�C������\n");
		loadData(infile);
		out.close();
	}

	protected void separeteData(String line) throws IOException {

		String[] tokenizer = line.split(",");
		// TODO: CSV���������Ƃ��o���郉�C�u������p�����`�ɕύX����
		// errorID��messageList��manager�ɍ����indexOf���\�b�h�ŉ���
		// ���݂��Ă��Ȃ�errorID�̏ꍇ�A�V�����G���[���b�Z�[�W���L�^����
		int errorID = 0;
		try {
			errorID = manager.getMessagesID(tokenizer[5]);
		} catch (Exception e) {
			errorID = addErrorID;
			System.out.println(tokenizer[5]);
			manager.put(errorID, 6, tokenizer[5]);
			System.out.println(tokenizer[5] + "  "
					+ manager.getMessagesID(tokenizer[5]));
			addErrorID++;
		}

		String filename = tokenizer[4];

		// TODO: �ϊ��������l���邱��
		// play(java.util.ArrayList<java.lang.String>,java.util.ArrayList<java.lang.Integer>)
		// ��','�̂Ƃ��Ɉ��������� �Ȃ�Ƃ����đ΍􂷂邱��
		// �z��̒������疳����莝���Ă��邱�ƂőΉ�����

		// 2010/06/30 18:59:04 �`���� int �^��
		// TODO: ���
		// long beginTime = calculationBeginTime(tokenizer[tokenizer.length -
		// 8]);
		int beginTime = 0;
		// �C�����Ԃ͎��o���Ď��Ԃ��v�Z���邱�Ƃɐ�������
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