package com.skuniv.QuickPollSocketServer;

import org.springframework.web.socket.WebSocketSession;

public class LectureMember {
	private String name;
	private int id;
	private WebSocketSession session;
	public LectureMember(String name, int id, WebSocketSession session) {
		this.name = name;
		this.id = id;
		this.session = session;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public WebSocketSession getSession() {
		return session;
	}
	public void setSession(WebSocketSession session) {
		this.session = session;
	}
	
}
