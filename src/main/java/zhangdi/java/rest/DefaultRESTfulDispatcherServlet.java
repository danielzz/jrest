package zhangdi.java.rest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zhangdi.java.rest.template.TemplateEngine;

/**
 * Default HttpServlet implementation for RESTfulDispatcher.
 * @author Di Zhang (Daniel)
 *
 */
@SuppressWarnings("serial")
public class DefaultRESTfulDispatcherServlet extends HttpServlet implements RESTfulDispatcher {

	RESTfulDispatcher dispatcher;
	boolean usePostWorkaround = false;

	public DefaultRESTfulDispatcherServlet(RESTfulDispatcher dispatcher) {
		super();
		this.dispatcher = dispatcher;
	}

	public RESTfulDispatcher getDispatcher() {
		return this.dispatcher;
	}
	
	public void registerHandler(String path, RESTfulHandler handler) {
		if (this.dispatcher.getHandlerRegistry() != null) {
			this.dispatcher.getHandlerRegistry().register(path, handler);
		}
	}
	
	public void deregisterHandler(String path) {
		if (this.dispatcher != null) {
			this.dispatcher.getHandlerRegistry().deregister(path);
		}
	}
	
	public boolean usePostAsWorkarounds() {
		return this.usePostWorkaround;
	}
	
	public void setUsePostAsWorkarounds(boolean usePost) {
		this.usePostWorkaround = usePost;
	}
	
	protected RequestMethod getUnderlyingMethodForPost(HttpServletRequest req, 
			HttpServletResponse res) {
		String m = req.getParameter("__jrest_request_method__");
		if (m == null || m.trim().equals("")) {
			return RequestMethod.POST;
		}
		
		try {
			RequestMethod method = RequestMethod.valueOf(m);
			return method;
		} catch (IllegalArgumentException e) {
			return RequestMethod.POST;
		}
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
		if (this.usePostAsWorkarounds()) {
			RequestMethod method = this.getUnderlyingMethodForPost(req, res);
			this.dispatch(req, res, method);
		} else {
			this.dispatch(req, res, RequestMethod.POST);
		}
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
