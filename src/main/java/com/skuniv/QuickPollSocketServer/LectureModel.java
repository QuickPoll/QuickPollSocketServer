package com.skuniv.QuickPollSocketServer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.web.socket.WebSocketSession;

public class LectureModel {
	private Map<String, LinkedHashMap<WebSocketSession, LectureMember>> memberList = new HashMap<String, LinkedHashMap<WebSocketSession, LectureMember>>();
	private static LectureModel lectureModel = null;
	public static LectureModel getIntstance() {
		if (lectureModel == null) {
			lectureModel = new LectureModel(); 
		}
		return lectureModel;
	}
	public Map<String, LinkedHashMap<WebSocketSession, LectureMember>> getMemberList() {
		return memberList;
	}
	public LinkedHashMap<WebSocketSession, LectureMember> getLectureMap(MessageVO messageVO) {
		return memberList.get(messageVO.getCourse_id());
	}
	public String getDisconnectMap(WebSocketSession session) {
		Iterator<String> courseKey = memberList.keySet().iterator();
		while (courseKey.hasNext()) {
			String course_id = courseKey.next();
			LinkedHashMap<WebSocketSession, LectureMember> map = memberList.get(course_id);
			Iterator<WebSocketSession> sessionKey = map.keySet().iterator();
			while (sessionKey.hasNext()) {
				if (sessionKey.next() == session) {
					return course_id;
				}
			}
		}
		return null;
	}
	
}
