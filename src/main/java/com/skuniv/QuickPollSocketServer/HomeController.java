package com.skuniv.QuickPollSocketServer;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/chat", method = RequestMethod.GET)
	public ModelAndView chat(HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView("chat");
		String id = request.getParameter("id");
		mv.addObject("userInfo", id);
		
		return mv;
	}
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView main(HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView("home");
		return mv;
	}
}
