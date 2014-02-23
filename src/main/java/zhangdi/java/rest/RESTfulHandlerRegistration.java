package zhangdi.java.rest;

public interface RESTfulHandlerRegistration {
	RESTfulHandler getHandler();
	
	void setHandler(RESTfulHandler handler);
	
	RESTfulPath getPath();
	
	void setPath(RESTfulPath path);
}
