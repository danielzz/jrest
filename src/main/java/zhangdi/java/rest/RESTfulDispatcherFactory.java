package zhangdi.java.rest;

import zhangdi.java.rest.impl.RESTfulDispatcherImpl;

public class RESTfulDispatcherFactory {
	public static DefaultRESTfulDispatcherServlet createServlet() {
		return new DefaultRESTfulDispatcherServlet(createDispatcher());
	}
	
	public static RESTfulDispatcher createDispatcher() {
		return new RESTfulDispatcherImpl();
	}
}
