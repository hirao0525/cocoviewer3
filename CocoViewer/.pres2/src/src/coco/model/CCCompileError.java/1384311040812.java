package src.coco.model;

public class CCCompileError {

	private int errorID;
	private String filename;
	private long beginTime;
	private int correctTime;

	public CCCompileError() {

	}

	public void setData(int errorID, String filename, long beginTime,
			int correctTime) {
		this.errorID = errorID;
		this.filename = filename;
		this.beginTime = beginTime;
		this.correctTime = correctTime;
	}

	public long getBeginTime() {
		return beginTime;
	}

	public String getFilename() {
		return filename;
	}

	public int getErrorID() {
		return errorID;
	}

	public long getCorrectTime() {
		return correctTime;
	}
}