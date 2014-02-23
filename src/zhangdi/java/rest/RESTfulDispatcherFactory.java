package zhangdi.java.rest;

import zhangdi.java.rest.impl.RESTfulDispatcherImpl;
import zhangdi.java.rest.impl.RESTfulDispatcherServletImpl;

public class RESTfulDispatcherFactory {
	public static RESTfulDispatcherServlet createServlet() {
		return new RESTfulDispatcherServletImpl(createDispatcher());
	}
	
	public static RESTfulDispatcher createDispatcher() {
		return new RESTfulDispatcherImpl();
	}
}
