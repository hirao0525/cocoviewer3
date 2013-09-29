package coco.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CCCompileErrorManager {

	private HashMap<Integer, CCCompileErrorList> lists = new HashMap<Integer, CCCompileErrorList>();
	private HashMap<String, Integer> ids = new HashMap<String, Integer>();
	private int totalErrorCount = 0;

	public CCCompileErrorManager() {

	}

	public void put(int id, String message) {
		CCCompileErrorList list = new CCCompileErrorList();
		list.setMessage(message);
		lists.put(id, list);
		ids.put(message, id);
	}

	public CCCompileErrorList getList(int id) {
		totalErrorCount++;
		if (!lists.containsKey(id)) {
			put(id, "dummy");
		}
		return lists.get(id);
	}

	public int getTotalErrorCount() {
		return totalErrorCount;
	}

	public List<CCCompileErrorList> getAllLists() {
		return new ArrayList<CCCompileErrorList>(lists.values());
	}

	public int getMessagesID(String message) {
		return ids.get(message);
	}
}