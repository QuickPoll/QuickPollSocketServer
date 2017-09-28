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
	
	public void insertQuickPollQuestion(MessageVO messageVO)  throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(messageVO.getCourse_id()+", " + messageVO.getQuestion_content() + " , " + messageVO.getQuestion_type() + " , "+ today());
		map.put("course_id", messageVO.getCourse_id());
		map.put("question_content", messageVO.getQuestion_content());
		map.put("question_type", messageVO.getQuestion_type());
		map.put("question_answer", null);
		map.put("question_answer_percent", null);
		map.put("question_date", today());
		quickPollDao.insertQuickPollQuestion(map);
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
//		today_date += hours / 10 > 0 ? "" + hours + "�� " : "0" + hours + "�� ";
//		today_date += minutes / 10 > 0 ? "" + minutes + "��" : "0" + minutes + "��";
		return today_date;
	}
}