package zhangdi.java.rest.impl;

import java.io.IOException;

import javax.servlet.ServletException;

import zhangdi.java.rest.HTTPErrorHelper;
import zhangdi.java.rest.RESTfulContext;
import zhangdi.java.rest.RESTfulHandler;

public class ResourceNotFoundHandler implements RESTfulHandler {

	@Override
	public void handleGet(RESTfulContext context) throws IOException,
			ServletException {
		HTTPErrorHelper.send404(context);
	}

	@Override
	public void handlePost(RESTfulContext context) throws IOException,
			ServletException {
		HTTPErrorHelper.send404(context);
	}

	@Override
	public void handlePut(RESTfulContext context) throws IOException,
			ServletException {
		HTTPErrorHelper.send404(context);
	}

	@Override
	public void handleDelete(RESTfulContext context) throws IOException,
			ServletException {
		HTTPErrorHelper.send404(context);
	}
	
}
