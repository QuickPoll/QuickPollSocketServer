package com.skuniv.QuickPollSocketServer;

import com.google.gson.Gson;

public class MessageVO {
	private String course_id;
	private String message;
	private int question_type; // 1->direct(subjective) 2-> objective 3->subjective
	private String question_content;
	private String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuestion_type() {
		return question_type;
	}

	public void setQuestion_type(int question_type) {
		this.question_type = question_type;
	}

	public String getQuestion_content() {
		return question_content;
	}

	public void setQuestion_content(String question_content) {
		this.question_content = question_content;
	}



	/******* message type ********/
	private String type; 
	/*
	 "connect" : "enter to student"
	 "create" : "create to room from professor"
	 
	 */
	/*****************************/
	private int id;
	
	public String getCourse_id() {
		return course_id;
	}

	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
