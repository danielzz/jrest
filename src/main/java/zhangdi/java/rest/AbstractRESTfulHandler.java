package zhangdi.java.rest;

import java.io.IOException;

import javax.servlet.ServletException;

/**
 * Base class for easy implementation of RESTfulHandler.
 * 
 * Instead of implementing four different methods for RESTfulHandler interface,
 * only <tt>handle(RESTfulContext, RequestMethod)</tt> method need to be implemented.
 * 
 * It's still possible that overrides the original methods but not recommended.
 * 
 * @author Di Zhang (Daniel)
 *
 */
public abstract class AbstractRESTfulHandler implements RESTfulHandler {
	
	abstract protected void handle(RESTfulContext context, RequestMethod method) 
			throws IOException, ServletException;

	@Override
	public void handleGet(RESTfulContext context) throws IOException,
			ServletException {
		handle(context, RequestMethod.GET);
	}

	@Override
	public void handlePost(RESTfulContext context) throws IOException,
			ServletException {
		handle(context, RequestMethod.POST);
	}

	@Override
	public void handlePut(RESTfulContext context) throws IOException,
			ServletException {
		handle(context, RequestMethod.PUT);
	}

	@Override
	public void handleDelete(RESTfulContext context) throws IOException,
			ServletException {
		handle(context, RequestMethod.DELETE);
	}

}
