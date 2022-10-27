package spring.controller;

import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import Bean.NumberGuessBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {
	@RequestMapping(path = { "/spring", "/springnumguess" })
	public ModelAndView index() {
		System.out.println("springnumguess");
		
		ModelAndView m = new ModelAndView();
//		ModelMap map = m.getModelMap();
//		map.addAttribute("username", "zs");
//		List list = new ArrayList();
//		list.add("zs");
//		list.add("ls");
//		list.add("ww3");
//		map.addAttribute("list", list);
//		hello ${username }
//	<br>
//				hello ${list[2] }
//	<br>
		
		m.setViewName("springnumguess");
		return m;
	}
}
