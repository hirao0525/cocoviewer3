package src.coco.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class CCCompileErrorManager {
	// HashMapでは順序が保証されないのでLinkedHashMapに変更
	private LinkedHashMap<Integer, CCCompileErrorList> lists = new LinkedHashMap<Integer, CCCompileErrorList>();
	private LinkedHashMap<String, Integer> ids = new LinkedHashMap<String, Integer>();
	private int totalErrorCount = 0;

	public CCCompileErrorManager() {

	}

	public void put(int id, int rare, String message) {
		CCCompileErrorList list = new CCCompileErrorList();
		list.setMessageData(rare, message);
		lists.put(id, list);
		ids.put(message, id);
	}

	public CCCompileErrorList getList(int id) {
		if (!lists.containsKey(id)) {
			put(id, 6, "dummy");
		}
		return lists.get(id);
	}

	public void totalErrorCountUp() {
		totalErrorCount++;
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