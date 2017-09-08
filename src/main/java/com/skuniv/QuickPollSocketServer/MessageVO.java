package com.skuniv.QuickPollSocketServer;

import com.google.gson.Gson;

public class MessageVO {
	private String course_id;
	private String message;
	/******* message type ********/
	private String type; 
	/*
	 "connect" : "enter to student"
	 "create" : "create to room from professor"
	 
	 */
	/*****************************/
	private String id;
	
	public String getCourse_id() {
		return course_id;
	}

	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



	public static MessageVO converMessage(String source) {
	    MessageVO message = new MessageVO();
	    Gson gson = new Gson();
	    message = gson.fromJson(source, MessageVO.class);
	 
	    return message;
	}

}
