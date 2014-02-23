package zhangdi.java.rest;

import java.io.IOException;

import javax.servlet.ServletException;

public class AbstractRESTfulHandler implements RESTfulHandler {

	@Override
	public void handleGet(RESTfulContext context) throws IOException,
			ServletException {
		HTTPErrorHelper.sendMethodNotAllowed(context);
	}

	@Override
	public void handlePost(RESTfulContext context) throws IOException,
			ServletException {
		HTTPErrorHelper.sendMethodNotAllowed(context);

	}

	@Override
	public void handlePut(RESTfulContext context) throws IOException,
			ServletException {
		HTTPErrorHelper.sendMethodNotAllowed(context);

	}

	@Override
	public void handleDelete(RESTfulContext context) throws IOException,
			ServletException {
		HTTPErrorHelper.sendMethodNotAllowed(context);

	}

}
