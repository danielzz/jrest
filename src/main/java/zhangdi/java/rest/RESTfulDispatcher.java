package zhangdi.java.rest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zhangdi.java.rest.template.TemplateEngine;

public interface RESTfulDispatcher {
	void dispatch(HttpServletRequest req, HttpServletResponse res, 
			RequestMethod method) throws IOException, ServletException;
	
	void setTemplateEngine(TemplateEngine templateEngine);
	
	TemplateEngine getTemplateEngine();
	
	RESTfulHandlerRegistry getHandlerRegistry();
	
	void setHandlerRegistry(RESTfulHandlerRegistry registry);
}
