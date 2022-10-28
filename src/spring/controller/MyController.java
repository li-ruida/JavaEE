package spring.controller;

import Bean.NumberGuessBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MyController {
	@RequestMapping(path = { "/spring", "/springnumguess" })
	public ModelAndView index(HttpSession session,HttpServletRequest request) {
		System.out.println("springnumguess");

		ModelAndView m = new ModelAndView();
		ModelMap map = m.getModelMap();
		NumberGuessBean numberguess=null;
		if(session.getAttribute("numberguess")==null){
			numberguess=new NumberGuessBean();
			session.setAttribute("numberguess",numberguess);
		}
		else {
			numberguess=(NumberGuessBean) session.getAttribute("numberguess");
		}
		String guess=request.getParameter("guess");
		if(guess==null){
			guess="";
		}
		if(numberguess.getSuccess()){
			numberguess.reset();
			session.setAttribute("numberguess",numberguess);
		}
		if(guess.length()>0){
			numberguess.setGuess(guess);
			session.setAttribute("numberguess",numberguess);
		}
		map.addAttribute("gethint", numberguess.getHint());
		map.addAttribute("getnumguesse", numberguess.getNumGuesses());
		if(numberguess.getSuccess()){
			m.setViewName("success");
			return m;
		} else if (numberguess.getNumGuesses()==0) {
			m.setViewName("welcome");
			return m;
		}else {
			m.setViewName("continue");
			return m;
		}

	}
}
