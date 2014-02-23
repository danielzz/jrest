package zhangdi.java.rest.impl;

import java.util.HashMap;
import java.util.Map;

import zhangdi.java.rest.RESTfulHandler;
import zhangdi.java.rest.RESTfulHandlerRegistration;
import zhangdi.java.rest.RESTfulPath;
import zhangdi.java.rest.RESTfulPathToken;

public class RESTfulHandlerRegistrationImpl implements
		RESTfulHandlerRegistration {
	
	RESTfulHandler handler;
	RESTfulPath path;
	
	Map<RESTfulPathToken, RESTfulHandlerRegistrationImpl> children;
	
	public RESTfulHandlerRegistrationImpl() {
		this.children = new HashMap<RESTfulPathToken, RESTfulHandlerRegistrationImpl>();
	}
	
	public RESTfulHandlerRegistrationImpl(RESTfulPath path, RESTfulHandler handler) {
		this();
		this.path = path;
		this.handler = handler;
	}
	
	public RESTfulHandlerRegistration findChild(RESTfulPathToken token) {
		return null;
	}

	@Override
	public RESTfulHandler getHandler() {
		return handler;
	}

	@Override
	public void setHandler(RESTfulHandler handler) {
		this.handler = handler;
	}

	@Override
	public RESTfulPath getPath() {
		return path;
	}

	@Override
	public void setPath(RESTfulPath path) {
		this.path = path;
	}

}
