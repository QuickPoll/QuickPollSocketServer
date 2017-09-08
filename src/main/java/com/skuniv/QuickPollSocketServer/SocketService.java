package com.skuniv.QuickPollSocketServer;

import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@Service("SocketService")
public class SocketService {
	private Map<String, LinkedList<HashMap<String, Object>>> list = new LectureModel().getIntstance().getList();
	public void createLecture(WebSocketSession session, TextMessage message, MessageVO messageVO) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("session", session);
		map.put("id", messageVO.getId());
		LinkedList<HashMap<String, Object>> arr = new LinkedList<HashMap<String, Object>>();
		arr.add(map);
		list.put(messageVO.getCourse_id(), arr);
	}
	public void enterLecture(WebSocketSession session, TextMessage message, MessageVO messageVO) {
		if (isLecture(messageVO)) {
			HashMap<String, Object> map = new HashMap<String, Object>();
    		map.put("session", session);
    		map.put("id", messageVO.getId());
    		list.get(messageVO.getCourse_id()).add(map);
		} else {
			// can not connect. not exist lecture
		}
	}
	public boolean isLecture(MessageVO messageVO) {
		if (list.containsKey(messageVO.getCourse_id())) return true;
		else return false;
	}
	public void removeLecture() {
		
	}
}
