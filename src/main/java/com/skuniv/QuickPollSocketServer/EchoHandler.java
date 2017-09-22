package com.skuniv.QuickPollSocketServer;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.skuniv.QuickPollSocketServer.Professor.ProfessorSocketService;
import com.skuniv.QuickPollSocketServer.Student.StudentSocketService;

public class EchoHandler extends TextWebSocketHandler {
	@Resource(name = "ProfessorSocketService")
	private ProfessorSocketService professorSocketService;
	@Resource(name = "StudentSocketService")
	private StudentSocketService studentSocketService;

	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();

    @Override

    public void afterConnectionEstablished(WebSocketSession session)

            throws Exception {
    	
//        sessionList.add(session);
//        System.out.println("{} connect " + session.getId() );


    }



    @Override

    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    	MessageVO messageVO = MessageVO.converMessage(message.getPayload());
    	if (messageVO.getType().equals("create")) {
    		professorSocketService.createLecture(session, message, messageVO);
    		System.out.println("create");
    	} else if (messageVO.getType().equals("connect")) {
    		studentSocketService.enterLecture(session, message, messageVO);
    		WebSocketSession sess = professorSocketService.sendConnectStudent(messageVO.getCourse_id());
    		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    		final String json = gson.toJson(messageVO);
    		sess.sendMessage(new TextMessage(json));
    		System.out.println("connect");
    	} else if (messageVO.getType().equals("sendDirectQuestion")) {
    		// insert db 
    		
    		// sendall to student
    		
    	}
    	professorSocketService.printMemberList(messageVO);
//    	System.out.println("{}�� ���� {} ����" + session.getId() + ", "+ message.getPayload());
//
//        for(WebSocketSession sess : sessionList){
//
//            sess.sendMessage(new TextMessage(session.getId() +" : "+ message.getPayload()));
//
		
//        }

    }


    @Override
    

    public void afterConnectionClosed(WebSocketSession session,
            CloseStatus status) throws Exception {
    	System.out.println(" disconnect : " + session.getId());
    	
//        sessionList.remove(session);
//    	Collection<LinkedList<HashMap<String, Object>>> collection = list.values();    	
//    	Iterator<LinkedList<HashMap<String, Object>>> iterator = collection.iterator();
//    	while (iterator.hasNext()) {
//    		LinkedList<HashMap<String, Object>> memberList = iterator.next();
//    		for (int i = 0 ; i < memberList.size(); i++) {
//    			if (memberList.get(i).get("session").equals(session)) {
//    				System.out.println("end");
//    				memberList.remove(i);
//    				return;
//    			} 
//    		}
//    	}
        
    }

}
