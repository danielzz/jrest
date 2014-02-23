package zhangdi.java.rest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

public class HTTPErrorHelper {
	public static void sendError(RESTfulContext context, int ec, String msg) throws IOException, ServletException {
		HttpServletResponse res = context.getResponse();
		res.sendError(ec, msg);
	}
	
	public static void send404(RESTfulContext context) throws IOException, ServletException {
		sendError(context, 404, "Not Found");
	}
	
	public static void sendNotFound(RESTfulContext context) throws IOException, ServletException {
		send404(context);
	}
	
	public static void send405(RESTfulContext context) throws IOException, ServletException {
		sendError(context, 405, "Method Not Allowed");
	}
	
	public static void sendMethodNotAllowed(RESTfulContext context) throws IOException, ServletException {
		send405(context);
	}
}
