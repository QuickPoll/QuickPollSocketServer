package com.skuniv.QuickPollSocketServer.dbservice;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import com.skuniv.QuickPollSocketServer.LectureMember;
import com.skuniv.QuickPollSocketServer.LectureModel;

@Service("ConnectService")
public class ConnectService {
	private LectureModel lectureModel = new LectureModel().getIntstance();
	private Map<String, LinkedHashMap<WebSocketSession, LectureMember>> memberList = lectureModel.getMemberList();
	public Object good() {
		// TODO Auto-generated method stub
		return "quickpoll";
	}
	public void disConnect(WebSocketSession session) {
		String course_id = lectureModel.getDisconnectMap(session);
		
		LinkedHashMap<WebSocketSession, LectureMember> member = memberList.get(course_id);
		Iterator<WebSocketSession> sessionKey = member.keySet().iterator();
		int count = 1;
		while (sessionKey.hasNext()) {
			WebSocketSession sessionId = sessionKey.next();
			if (sessionId == session) {
				if (count == 1) {
					memberList.remove(course_id);
					System.out.println("disconnect professor");
					return ;
				} else {
					member.remove(sessionId);
					System.out.println("disconnect student");
					return ;
				}
			}
			count++;
		}
	}
}
