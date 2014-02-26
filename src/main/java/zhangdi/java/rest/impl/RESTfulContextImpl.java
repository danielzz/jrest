package zhangdi.java.rest.impl;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zhangdi.java.rest.RESTfulContext;
import zhangdi.java.rest.RESTfulPath;
import zhangdi.java.rest.RESTfulPathToken;
import zhangdi.java.rest.RequestMethod;
import zhangdi.java.rest.template.Template;
import zhangdi.java.rest.template.TemplateEngine;

public class RESTfulContextImpl implements RESTfulContext {
	
	HttpServletRequest req;
	HttpServletResponse res;
	
	RESTfulPath path;
	int currentTokenIndex = 0;
	
	TemplateEngine te;
	
	public RESTfulContextImpl(HttpServletRequest req, HttpServletResponse res) {
		this.req = req;
		this.res = res;
		
		String uri = req.getRequestURI();
		path = new RESTfulPath(uri);
	}

	@Override
	public RESTfulPath getPath() {
		return path;
	}

	@Override
	public RESTfulPathToken getCurrentPathToken() {
		return path.get(currentTokenIndex);
	}

	@Override
	public void setCurrentPathTokenIndex(int index) {
		this.currentTokenIndex = index;
	}

	@Override
	public RESTfulPathToken getPreviousPathToken() {
		if (this.currentTokenIndex == 0) {
			return null;
		}
		return this.path.get(this.currentTokenIndex-1);
	}

	@Override
	public RESTfulPathToken[] getPathTokens() {
		return this.path.getTokens();
	}

	@Override
	public RequestMethod getMethod() {
		return RequestMethod.valueOf(req.getMethod().toUpperCase());
	}

	@Override
	public String getRequestParameter(String name) {
		return req.getParameter(name);
	}

	@Override
	public Object getSessionAttribute(String key) {
		return req.getSession().getAttribute(key);
	}

	@Override
	public void setSessionAttribute(String key, Object value) {
		req.getSession().setAttribute(key, value);
	}

	@Override
	public HttpServletRequest getRequest() {
		return req;
	}

	@Override
	public HttpServletResponse getResponse() {
		return res;
	}

	@Override
	public TemplateEngine getTemplateEngine() {
		return te;
	}

	@Override
	public void setTemplateEngine(TemplateEngine engine) {
		this.te = engine;
	}

	@Override
	public Template getTemplate() {
		return te.getTemplate(this.path.toString());
	}

	@Override
	public Template getTemplate(String key) {
		return te.getTemplate(key);
	}

	@Override
	public void sendHTML(String html) throws IOException {
		Writer writer = res.getWriter();
		writer.write(html);
	}

	@Override
	public void sendHTML(Template tpl, Map<String, Object> params) 
			throws IOException {
		if (params != null) {
			for (String key : params.keySet()) {
				tpl.add(key, params.get(key));
			}
		}
		
		Writer writer = res.getWriter();
		tpl.write(writer);
	}

	@Override
	public void sendJSON(String json) throws IOException {
		String contentType = res.getContentType();
		if (contentType == null) {
			contentType = "application/json;charset=UTF-8";
		}
		
		Writer writer = res.getWriter();
		writer.write(json);
	}

	@Override
	public void sendJSON(Template tpl, Map<String, Object> params) 
			throws IOException {
		String contentType = res.getContentType();
		if (contentType == null) {
			contentType = "applicatoin/json;charset=UTF-8";
		}
		
		Writer writer = res.getWriter();
		tpl.write(writer);
	}

	@Override
	public void sendError(int ec, String message) throws IOException {
		res.sendError(ec, message);
	}

	@Override
	public void sendRedirect(String url) throws IOException {
		res.sendRedirect(res.encodeRedirectURL(url));
	}

}
