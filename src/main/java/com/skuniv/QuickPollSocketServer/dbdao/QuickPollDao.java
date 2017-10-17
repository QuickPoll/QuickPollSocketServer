package com.skuniv.QuickPollSocketServer.dbdao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.skuniv.QuickPollSocketServer.MessageVO;
import com.skuniv.QuickPollSocketServer.connectdb.ConnectDB;

@SuppressWarnings("unchecked")
@Repository("QuickPollDao")
public class QuickPollDao extends ConnectDB {

	public int insertQuickPollQuestion(Map<String, Object> map) {
		insert("quickpoll.insertQuickPollQuestion", map);
		return (Integer)map.get("quickpoll_question_id");
	}
	
	public void insertQuickPollAnswer(Map<String, Object> map) {
		
		insert("quickpoll.insertQuickPollAnswer", map);
	}
	public int selectCountQuickPollAnswer(int quickpoll_question_id) {
		return (Integer)selectOne("quickpoll.selectCountQuickPollAnswer", quickpoll_question_id);
	}
	public int selectAnswerForObjectiveQuestion(int quickpoll_question_id) {
		return (Integer)selectOne("quickpoll.selectAnswerForObjectiveQuestion", quickpoll_question_id);
	}
	public String selectAnswerForSubjectiveQuestion(int quickpoll_question_id) {
		return (String)selectOne("quickpoll.selectAnswerForSubjectiveQuestion", quickpoll_question_id);
	}
}
