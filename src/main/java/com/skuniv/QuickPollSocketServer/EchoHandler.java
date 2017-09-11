package com.skuniv.QuickPollSocketServer;

import java.util.*;

import javax.annotation.Resource;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class EchoHandler extends TextWebSocketHandler {
	@Resource(name = "SocketService")
	private SocketService socketService;
	// ������ �������� �ؽ�Ʈ �޽����� �����Ǹ� ȣ��Ǵ� �޼ҵ�
	private Map<String, LinkedList<HashMap<String, Object>>> list = new LectureModel().getIntstance().getList();
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	 /**
	
     * Ŭ���̾�Ʈ ���� ���Ŀ� ����Ǵ� �޼ҵ�

     */

    @Override

    public void afterConnectionEstablished(WebSocketSession session)

            throws Exception {
    	
//        sessionList.add(session);
//        System.out.println("{} ����� " + session.getId() );


    }

    /**

     * Ŭ���̾�Ʈ�� �����ϼ����� �޽����� �������� �� ����Ǵ� �޼ҵ�

     */

    @Override

    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    	MessageVO messageVO = MessageVO.converMessage(message.getPayload());
    	if (messageVO.getType().equals("create")) {
    		socketService.createLecture(session, message, messageVO);
    		System.out.println("create");
    	} else if (messageVO.getType().equals("connect")) {
    		socketService.enterLecture(session, message, messageVO);
    		System.out.println("connect");
    	} else if (messageVO.getType().equals("sendDirectQuestion")) {
    		
    	}
		for (int i = 0 ; i < list.get(messageVO.getCourse_id()).size(); i++)
			System.out.println("id : " + list.get(messageVO.getCourse_id()).get(i).get("id"));
    	
//    	System.out.println("{}�� ���� {} ����" + session.getId() + ", "+ message.getPayload());
//
//        for(WebSocketSession sess : sessionList){
//
//            sess.sendMessage(new TextMessage(session.getId() +" : "+ message.getPayload()));
//
//        }

    }

    /**

     * Ŭ���̾�Ʈ�� ������ ������ �� ����Ǵ� �޼ҵ�

     */

    @Override
    

    public void afterConnectionClosed(WebSocketSession session,
            CloseStatus status) throws Exception {
    	System.out.println("{} ���� ����" + session.getId());

//        sessionList.remove(session);
    	Collection<LinkedList<HashMap<String, Object>>> collection = list.values();    	
    	Iterator<LinkedList<HashMap<String, Object>>> iterator = collection.iterator();
    	while (iterator.hasNext()) {
    		LinkedList<HashMap<String, Object>> memberList = iterator.next();
    		for (int i = 0 ; i < memberList.size(); i++) {
    			if (memberList.get(i).get("session").equals(session)) {
    				System.out.println("end");
    				memberList.remove(i);
    				return;
    			} 
    		}
    	}
        
    }

}
