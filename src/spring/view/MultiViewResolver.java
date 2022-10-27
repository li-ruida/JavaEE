package spring.view;

import java.util.*;
import javax.servlet.http.*;

import org.springframework.core.Ordered;
import org.springframework.web.context.support.WebApplicationObjectSupport;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

public class MultiViewResolver extends WebApplicationObjectSupport implements ViewResolver, Ordered {
	private int order = Ordered.HIGHEST_PRECEDENCE;

	public void setOrder(int order) {
		this.order = order;
	}

	public int getOrder() {
		return this.order;
	}

	public View resolveViewName(String viewName, Locale locale) throws Exception {
		System.out.println("in resolveViewName method");
		ViewResolver jspViewResolver = (ViewResolver) getApplicationContext().getBean("viewResolver");
		View view = jspViewResolver.resolveViewName(viewName, locale);
		if (view != null) {
			return view;
		}

		return NOT_ACCEPTABLE_VIEW;
	}

	private static final View NOT_ACCEPTABLE_VIEW = new View() {
		public String getContentType() {
			return null;
		}

		public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
		}
	};
}