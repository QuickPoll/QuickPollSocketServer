package com.skuniv.QuickPollSocketServer.dbservice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skuniv.QuickPollSocketServer.MessageVO;
import com.skuniv.QuickPollSocketServer.dbdao.QuickPollDao;

@Service("QuickPollService")
public class QuickPollService {
	@Resource(name = "QuickPollDao")
	private QuickPollDao quickPollDao;

	public Object good() {
		// TODO Auto-generated method stub
		return "quickpoll";
	}

	public int selectCountQuickPollAnswer(int quickpoll_question_id) throws Exception {
		return quickPollDao.selectCountQuickPollAnswer(quickpoll_question_id);
	}

	public void insertQuickPollAnwser(MessageVO messageVO) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		int quickpoll_question_id = messageVO.getQuickpollQuestionId();
		String question_answer = messageVO.getQuestionAnwser();
		map.put("quickpoll_question_id", quickpoll_question_id);
		map.put("id", messageVO.getId());
		map.put("question_answer", question_answer);
		map.put("answer_state", getAnswerState(quickpoll_question_id, question_answer, messageVO.getType()));
		quickPollDao.insertQuickPollAnswer(map);
	}

	public int getAnswerState(int quickpoll_question_id, String input_answer, String question_type) {
		System.out.println("question_type : " + question_type);
		int type = 0;
		if (question_type.equals("objectiveQuestionAnswer")) {
			type = 2;
		} else if (question_type.equals("subjectiveQuestionAnswer")) {
			type = 3;
		}
		switch (type) {
		case 1:
			return 0;
		case 2:
			int answer = (Integer) quickPollDao.selectAnswerForObjectiveQuestion(quickpoll_question_id);
			System.out.println("answer : " + answer);
			return answer == Integer.parseInt(input_answer) ? 1 : -1;
		case 3:
			String subjective_answer = (String) quickPollDao.selectAnswerForSubjectiveQuestion(quickpoll_question_id);
			System.out.println("s_answer : " + subjective_answer);
			return subjective_answer.equals(input_answer) == true ? 1 : -1;
		default:
			return 0;
		}
	}

	public int insertQuickPollQuestion(MessageVO messageVO) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("course_id", messageVO.getCourse_id());
		map.put("question_id", messageVO.getQuestionId());
		map.put("question_type", messageVO.getQuestion_type());
		map.put("question_content", messageVO.getQuestion_content());
		map.put("question_answer_percent", null);
		if (messageVO.getQuestion_type() != 1) {
			map.put("question_answer", messageVO.getQuestionAnwser());
		} else {
			map.put("question_answer", null);
		}
		map.put("question_date", today());
		return quickPollDao.insertQuickPollQuestion(map);
	}

	public String today() {
		Date date = new Date();
		String today_date = "";
		int month = date.getMonth() + 1;
		int day = date.getDate();
		int hours = date.getHours();
		int minutes = date.getMinutes();
		int year = 1900 + date.getYear();
		today_date = "" + year + ". ";
		today_date += month / 10 > 0 ? "" + month + ". " : "0" + month + ". ";
		today_date += day / 10 > 0 ? "" + day + ". " : "0" + day + ". ";
		// today_date += hours / 10 > 0 ? "" + hours + "�� " : "0" + hours +
		// "�� ";
		// today_date += minutes / 10 > 0 ? "" + minutes + "��" : "0" + minutes
		// + "��";
		return today_date;
	}
}
