package zhangdi.java.rest;

import java.io.IOException;

import javax.servlet.ServletException;

public interface RESTfulHandler {
	void handleGet(RESTfulContext context) throws IOException, ServletException;
	
	void handlePost(RESTfulContext context) throws IOException, ServletException;
	
	void handlePut(RESTfulContext context) throws IOException, ServletException;
	
	void handleDelete(RESTfulContext context) throws IOException, ServletException;
}
