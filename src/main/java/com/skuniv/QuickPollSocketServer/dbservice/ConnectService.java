package com.skuniv.QuickPollSocketServer.dbservice;

import java.io.IOException;
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
import com.skuniv.QuickPollSocketServer.model.ConnectVO;

@Service("ConnectService")
public class ConnectService {
	private LectureModel lectureModel = LectureModel.getIntstance();
	private Map<String, LinkedHashMap<WebSocketSession, LectureMember>> memberList = lectureModel.getMemberList();
	public Object good() {
		// TODO Auto-generated method stub
		return "quickpoll";
	}
	public void closeConnect(WebSocketSession session, String course_id) {
		memberList.get(course_id).remove(session);
	}
	public void disConnect(WebSocketSession session) throws IOException {
		String course_id = lectureModel.getDisconnectMap(session);
		System.out.println("course_id : " + course_id);
		if (course_id==null) return;
		LinkedHashMap<WebSocketSession, LectureMember> member = memberList.get(course_id);
		Iterator<WebSocketSession> sessionKey = member.keySet().iterator();
		WebSocketSession professorSession = sessionKey.next();  
		if (professorSession == session) {
			memberList.remove(course_id);
			closeSession(sessionKey);
			return ;
		} else {
			while (sessionKey.hasNext()) {
				WebSocketSession sessionId = sessionKey.next();
				if (sessionId == session) {					
					sendCloseMessageToProfessor(professorSession, member.get(sessionId).getId());
					member.remove(sessionId);
					return ;
				}
			}	
		}
	}
	public void sendCloseMessageToProfessor(WebSocketSession professorSession, int student_id) throws IOException {
		ConnectVO connectVO = new ConnectVO();
		connectVO.setType("closeStudent");
		connectVO.setStudentId(student_id);
		String sendData = toJson(connectVO);
		professorSession.sendMessage(new TextMessage(sendData));
	}
	public void closeSession(Iterator<WebSocketSession> sessionKey) throws IOException {
		while (sessionKey.hasNext()) {
			sessionKey.next().sendMessage(new TextMessage("close"));
		}
	}
	public String toJson(ConnectVO connectVO) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		return gson.toJson(connectVO);
	}
}
