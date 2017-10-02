package com.skuniv.QuickPollSocketServer;

import com.google.gson.Gson;

public class MessageVO {
	private int quickpoll_question_id;
	private String course_id;
	private String message;
	private int id;
	/******* message type ********/
	private String type; 
	/*
	 "connect" : "enter to student"
	 "create" : "create to room from professor"
	 
	 */
	/*****************************/

	private int question_type; // 1->direct(subjective) 2-> objective 3->subjective
	private String question_content;
	private String name;
	private String question_anwser;
	private String example1, example2, example3, example4;
	
	public String getExample1() {
		return example1;
	}

	public void setExample1(String example1) {
		this.example1 = example1;
	}

	public String getExample2() {
		return example2;
	}

	public void setExample2(String example2) {
		this.example2 = example2;
	}

	public String getExample3() {
		return example3;
	}

	public void setExample3(String example3) {
		this.example3 = example3;
	}

	public String getExample4() {
		return example4;
	}

	public void setExample4(String example4) {
		this.example4 = example4;
	}

	public int getQuickpollQuestionId() {
		return quickpoll_question_id;
	}

	public void setQuickpollQuestionId(int quickpoll_question_id) {
		this.quickpoll_question_id = quickpoll_question_id;
	}
	public String getQuestionAnwser() {
		return question_anwser;
	}

	public void setQuestionAnwser(String question_anwser) {
		this.question_anwser = question_anwser;
	}

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
