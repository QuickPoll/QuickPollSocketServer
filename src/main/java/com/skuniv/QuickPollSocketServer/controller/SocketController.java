package com.skuniv.QuickPollSocketServer.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.skuniv.QuickPollSocketServer.Student.StudentSocketService;
import com.skuniv.QuickPollSocketServer.model.LectureVO;

@Controller
public class SocketController {
	@Resource(name = "StudentSocketService")
	private StudentSocketService studentSocketService;
	
	@RequestMapping(value = "/checkOpenLecture", method = RequestMethod.GET, produces="text/plain;charset=UTF-8")
	public @ResponseBody String isOpenLecture(HttpServletRequest request) throws Exception {
		String course_id = request.getParameter("course_id");
		System.out.println("checkOpenLecture ---- course_id : " + course_id);
		boolean checkState = studentSocketService.isLecture(course_id);
		LectureVO lectureVO = studentSocketService.setState(checkState);
		Gson gson = new Gson(); 
		return gson.toJson(lectureVO);
	}
	
}
