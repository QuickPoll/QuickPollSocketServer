package com.skuniv.QuickPollSocketServer.Student;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.skuniv.QuickPollSocketServer.LectureMember;
import com.skuniv.QuickPollSocketServer.LectureModel;
import com.skuniv.QuickPollSocketServer.MessageVO;

@Service("StudentSocketService")
public class StudentSocketService {
	private LectureModel lectureModel = new LectureModel().getIntstance();
	private Map<String, LinkedHashMap<WebSocketSession, LectureMember>> memberList = lectureModel.getMemberList();
	public void enterLecture(WebSocketSession session, TextMessage message, MessageVO messageVO) {
		if (isLecture(messageVO)) {
			LinkedHashMap<WebSocketSession, LectureMember> map = lectureModel.getLectureMap(messageVO);
    		map.put(session, new LectureMember(messageVO.getName(), messageVO.getId(), session));
    		memberList.put(messageVO.getCourse_id(), map);
		} else {
			// can not connect. not exist lecture
		}
	}
	public boolean isLecture(MessageVO messageVO) {
		if (memberList.containsKey(messageVO.getCourse_id())) return true;
		else return false;
	}
	// send to directquestion answer to professor from student.
	public void sendDirectQuestionAnwserToProfessor(MessageVO messageVO) throws IOException {
		HashMap<WebSocketSession, LectureMember> map = lectureModel.getLectureMap(messageVO);
		Iterator<WebSocketSession> key = map.keySet().iterator();
		String sendData = toJson(messageVO);
		WebSocketSession sessionKey = key.next();
		sessionKey.sendMessage(new TextMessage(sendData));
	}
	public void sendSummitedObjectiveQuestionToProfessor(MessageVO messageVO, int count) throws IOException {
		messageVO.setSummitedCount(count);
		HashMap<WebSocketSession, LectureMember> map = lectureModel.getLectureMap(messageVO);
		Iterator<WebSocketSession> key = map.keySet().iterator();
		String sendData = toJson(messageVO);
		WebSocketSession sessionKey = key.next();
		sessionKey.sendMessage(new TextMessage(sendData));
	}
	public String toJson(MessageVO messageVO) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		return gson.toJson(messageVO);
	}
}
