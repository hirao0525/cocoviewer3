package coco.model;

public class CCCompileError {

	private int errorID;
	private String filename;
	private int beginTime;
	private int correctTime;

	public CCCompileError() {

	}

	public void setData(int errorID, String filename, int beginTime,
			int correctTime) {
		this.errorID = errorID;
		this.filename = filename;
		this.beginTime = beginTime;
		this.correctTime = correctTime;
	}

	public int getBeginTime() {
		return beginTime;
	}

	public String getFilename() {
		return filename;
	}

	public int getErrorID() {
		return errorID;
	}

	public int getCorrectTime() {
		return correctTime;
	}
}