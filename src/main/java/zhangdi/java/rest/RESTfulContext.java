package zhangdi.java.rest;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zhangdi.java.rest.template.Template;
import zhangdi.java.rest.template.TemplateEngine;

/**
 * This class wraps the HttpServletRequest and HttpServletResponse.
 * 
 * However, it only provides some convenient methods. For general usage, use
 * getRequest() or getResponse() to obtain the original HttpServletRequest or
 * HttpServletResponse instances.
 * 
 * We don't want to re-invent wheels here. So, just use the HttpServletRequest 
 * and HttpServletResponse. They are just good enough.
 * 
 * @author Di Zhang (Daniel)
 *
 */
public interface RESTfulContext {
	RESTfulPath getPath();
	
	RESTfulPathToken getCurrentPathToken();
	
	void setCurrentPathTokenIndex(int index);
	
	RESTfulPathToken getPreviousPathToken();
	
	RESTfulPathToken[] getPathTokens();
	
	RequestMethod getMethod();
	
	String getRequestParameter(String name);
	
	Object getSessionAttribute(String key);
	
	void setSessionAttribute(String key, Object value);
	
	HttpServletRequest getRequest();
	
	HttpServletResponse getResponse();
	
	TemplateEngine getTemplateEngine();
	
	void setTemplateEngine(TemplateEngine engine);
	
	/**
	 * Find the template based on current path.
	 * 
	 * @return The template, null if cannot find
	 */
	Template getTemplate();

	/**
	 * Find the template based on the given key.
	 * 
	 * @param key The key that represents the template
	 * @return The template, null if none found or there is no TemplateEngine set
	 */
	Template getTemplate(String key);
	
	void sendHTML(String html) throws IOException;
	
	void sendHTML(Template tpl, Map<String, Object> params) throws IOException;
	
	void sendJSON(String json) throws IOException;
	
	void sendJSON(Template tpl, Map<String, Object> params) throws IOException;
	
	void sendError(int ec, String message) throws IOException;
	
	void sendRedirect(String url) throws IOException;
}
