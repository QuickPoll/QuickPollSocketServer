package com.skuniv.QuickPollSocketServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class EchoHandler extends TextWebSocketHandler {
	
	// 웹소켓 서버측에 텍스트 메시지가 접수되면 호출되는 메소드
	private Map<String, ArrayList<HashMap<String, Object>>> list = new HashMap<String, ArrayList<HashMap<String, Object>>>();
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	 /**
	
     * 클라이언트 연결 이후에 실행되는 메소드

     */

    @Override

    public void afterConnectionEstablished(WebSocketSession session)

            throws Exception {
    	
//        sessionList.add(session);
//        System.out.println("{} 연결됨 " + session.getId() );


    }

    /**

     * 클라이언트가 웹소켓서버로 메시지를 전송했을 때 실행되는 메소드

     */

    @Override

    protected void handleTextMessage(WebSocketSession session,

        TextMessage message) throws Exception {

    	MessageVO messageVO = MessageVO.converMessage(message.getPayload());
    	if (messageVO.getType().equals("connect")) {
    		HashMap<String, Object> map = new HashMap<String, Object>();
    		map.put("session", session);
    		map.put("id", messageVO.getId());
    		if (list.containsKey(messageVO.getCourse_id())) {
    			list.get(messageVO.getCourse_id()).add(map);
    		} else {
    			ArrayList<HashMap<String, Object>> arr = new ArrayList<HashMap<String, Object>>();
    			arr.add(map);
    			list.put(messageVO.getCourse_id(), arr);
    		}
    		for (int i = 0 ; i < list.get(messageVO.getCourse_id()).size(); i++)
    			System.out.println("id : " + list.get(messageVO.getCourse_id()).get(i).get("id"));
    	}
    	
//    	System.out.println("{}로 부터 {} 받음" + session.getId() + ", "+ message.getPayload());
//
//        for(WebSocketSession sess : sessionList){
//
//            sess.sendMessage(new TextMessage(session.getId() +" : "+ message.getPayload()));
//
//        }

    }

    /**

     * 클라이언트가 연결을 끊었을 때 실행되는 메소드

     */

    @Override

    public void afterConnectionClosed(WebSocketSession session,

            CloseStatus status) throws Exception {

        sessionList.remove(session);

        System.out.println("{} 연결 끊김" + session.getId());

    }

}
