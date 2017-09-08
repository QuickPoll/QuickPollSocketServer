package com.skuniv.QuickPollSocketServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LectureModel {
	private Map<String, LinkedList<HashMap<String, Object>>> list = new HashMap<String, LinkedList<HashMap<String, Object>>>();
	private static LectureModel lectureModel = null;
	public static LectureModel getIntstance() {
		if (lectureModel == null) {
			lectureModel = new LectureModel(); 
		}
		return lectureModel;
	}
	public Map<String, LinkedList<HashMap<String, Object>>> getList() {
		return list;
	}
	
}
