package zhangdi.java.rest.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zhangdi.java.rest.RESTfulDispatcher;
import zhangdi.java.rest.RESTfulDispatcherServlet;
import zhangdi.java.rest.RESTfulHandlerRegistry;
import zhangdi.java.rest.RequestMethod;
import zhangdi.java.rest.template.TemplateEngine;

@SuppressWarnings("serial")
public class RESTfulDispatcherServletImpl extends RESTfulDispatcherServlet {

	public RESTfulDispatcherServletImpl(RESTfulDispatcher dispatcher) {
		super(dispatcher);
	}

	@Override
	public void dispatch(HttpServletRequest req, HttpServletResponse res,
			RequestMethod method) throws IOException, ServletException {
		this.getDispatcher().dispatch(req, res, method);
	}

	@Override
	public void setTemplateEngine(TemplateEngine templateEngine) {
		this.getDispatcher().setTemplateEngine(templateEngine);
	}

	@Override
	public TemplateEngine getTemplateEngine() {
		return this.getDispatcher().getTemplateEngine();
	}

	@Override
	public RESTfulHandlerRegistry getHandlerRegistry() {
		return this.getDispatcher().getHandlerRegistry();
	}

	@Override
	public void setHandlerRegistry(RESTfulHandlerRegistry registry) {
		this.getDispatcher().setHandlerRegistry(registry);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		this.dispatch(req, res, RequestMethod.GET);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		this.dispatch(req, res, RequestMethod.POST);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		this.dispatch(req, res, RequestMethod.PUT);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		this.dispatch(req, res, RequestMethod.DELETE);
	}
}
