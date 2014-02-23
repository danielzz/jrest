package zhangdi.java.rest;

import javax.servlet.http.HttpServlet;

/**
 * @author Di Zhang (Daniel)
 *
 */
@SuppressWarnings("serial")
public abstract class RESTfulDispatcherServlet extends HttpServlet implements RESTfulDispatcher {
	
	RESTfulDispatcher dispatcher;

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

}
