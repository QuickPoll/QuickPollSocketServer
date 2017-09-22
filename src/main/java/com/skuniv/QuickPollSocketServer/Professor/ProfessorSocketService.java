package com.skuniv.QuickPollSocketServer.Professor;

import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.skuniv.QuickPollSocketServer.LectureMember;
import com.skuniv.QuickPollSocketServer.LectureModel;
import com.skuniv.QuickPollSocketServer.MessageVO;

@Service("ProfessorSocketService")
public class ProfessorSocketService {
	private LectureModel lectureModel = new LectureModel().getIntstance();
	private Map<String, LinkedHashMap<WebSocketSession, LectureMember>> memberList = lectureModel.getMemberList();
	public void createLecture(WebSocketSession session, TextMessage message, MessageVO messageVO) {
		LinkedHashMap<WebSocketSession, LectureMember> map = new LinkedHashMap<WebSocketSession, LectureMember>();
		map.put(session, new LectureMember(messageVO.getName(), messageVO.getId(), session));
		memberList.put(messageVO.getCourse_id(), map);
	}
	public WebSocketSession sendConnectStudent(String course_id) {
		return memberList.get(course_id).keySet().iterator().next();
	}
	public void removeLecture() {
		
	}
	public void printMemberList(MessageVO messageVO) {
		HashMap<WebSocketSession, LectureMember> map = lectureModel.getLectureMap(messageVO);
		Iterator<WebSocketSession> key = map.keySet().iterator();
		while (key.hasNext()) {
			WebSocketSession sessionKey = key.next();
			System.out.println("name : " + map.get(sessionKey).getName() + " id : " + map.get(sessionKey).getId());
		}
	}
	public void sendAllDirectQuestionToStudent(MessageVO messageVO) {
		HashMap<WebSocketSession, LectureMember> map = lectureModel.getLectureMap(messageVO);
		Iterator<WebSocketSession> key = map.keySet().iterator();
		key.next(); // not student -> professor (index = 0)
		while (key.hasNext()) {
			WebSocketSession sessionKey = key.next();
//			sessionKey.sendMessage(new TextMessage);
			System.out.println("name : " + map.get(sessionKey).getName() + " id : " + map.get(sessionKey).getId());
		}
	}
}