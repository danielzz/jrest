package zhangdi.java.rest;

/**
 * This enum represents the HTTP methods. Currently, it supports the most used 
 * four methods: GET, POST, PUT, DELETE.
 * 
 * @author Di Zhang (Daniel)
 *
 */
public enum RequestMethod {
	GET("GET"),
	POST("POST"),
	PUT("PUT"),
	DELETE("DELETE");
	
	private final String method;
	
	private RequestMethod(String method) {
		this.method = method;
	}
	
	public String toString() {
		return this.method;
	}
	
	public RequestMethod eval(String given) {
		if (given == null) {
			throw new NullPointerException("Method cannot be null.");
		}

		if (given.equalsIgnoreCase(GET.method)) {
			return GET;
		} else if (given.equalsIgnoreCase(POST.method)) {
			return POST;
		} else if (given.equalsIgnoreCase(PUT.method)) {
			return PUT;
		} else if (given.equalsIgnoreCase(DELETE.method)) {
			return DELETE;
		}

		throw new IllegalArgumentException("Method is not supported: " + given);
	}
}
