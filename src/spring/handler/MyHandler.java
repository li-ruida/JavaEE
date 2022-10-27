package spring.handler;

import javax.servlet.http.*;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;



public class MyHandler extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("in preHandle");
		return true;
	}
	
	public void postHandle(HttpServletRequest request,
            HttpServletResponse response,
            Object handler, @Nullable ModelAndView modelAndView) throws Exception {
		
		System.out.println(modelAndView.getViewName());
		
	}
     
}
