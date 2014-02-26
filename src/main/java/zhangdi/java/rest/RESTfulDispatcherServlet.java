package zhangdi.java.rest;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Di Zhang (Daniel)
 *
 */
@SuppressWarnings("serial")
public abstract class RESTfulDispatcherServlet extends HttpServlet implements RESTfulDispatcher {
	
	RESTfulDispatcher dispatcher;
	boolean usePostWorkaround = false;

	public RESTfulDispatcherServlet(RESTfulDispatcher dispatcher) {
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
	
	public boolean usePostWorkaround() {
		return this.usePostWorkaround;
	}
	
	public void setUsePostWorkaround(boolean usePost) {
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

}
