package zhangdi.java.rest.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zhangdi.java.rest.RESTfulContext;
import zhangdi.java.rest.RESTfulDispatcher;
import zhangdi.java.rest.RESTfulHandler;
import zhangdi.java.rest.RESTfulHandlerRegistration;
import zhangdi.java.rest.RESTfulHandlerRegistry;
import zhangdi.java.rest.RESTfulPath;
import zhangdi.java.rest.RequestMethod;
import zhangdi.java.rest.template.TemplateEngine;

public class RESTfulDispatcherImpl implements RESTfulDispatcher {
	
	RESTfulHandlerRegistry handlers;
	TemplateEngine templateEngine;
	
	public RESTfulDispatcherImpl() {
		
	}

	@Override
	public void dispatch(HttpServletRequest req, HttpServletResponse res,
			RequestMethod method) throws IOException, ServletException {
		RESTfulContext context = buildContext(req, res, method);
		RESTfulPath path = context.getPath();
		RESTfulHandlerRegistration reg = handlers.find(path.toString());
		
		if (reg == null) {
			// 404
			return;
		}
		
		// Set the path token index. The found handler's path is shorter or same as the 
		// request's path.
		context.setCurrentPathTokenIndex(reg.getPath().size()-1);
		RESTfulHandler handler = reg.getHandler();
		
		if (handler == null) {
			// 404
			return;
		}
		
		switch (method) {
		case GET:
			handler.handleGet(context);
			break;
		case POST:
			handler.handlePost(context);
			break;
		case PUT:
			handler.handlePut(context);
			break;
		case DELETE:
			handler.handleDelete(context);
			break;
		default:
			// unsupported method.
		}
	}

	@Override
	public void setTemplateEngine(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}

	@Override
	public TemplateEngine getTemplateEngine() {
		return this.templateEngine;
	}

	private RESTfulContext buildContext(HttpServletRequest req, 
			HttpServletResponse res, RequestMethod method) {
		return null;
	}

	@Override
	public RESTfulHandlerRegistry getHandlerRegistry() {
		return handlers;
	}

	@Override
	public void setHandlerRegistry(RESTfulHandlerRegistry registry) {
		this.handlers = registry;
	}
}
