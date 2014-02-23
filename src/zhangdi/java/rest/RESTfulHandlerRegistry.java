package zhangdi.java.rest;

/**
 * <p>A registry that storing the path and handler bindings. Only one handler allowed 
 * for one path.</p>
 * 
 * <p>A path is an instance of RESTfulPath. Generally, it represents a URI. A path may 
 * have ascendant paths and descendant paths. An ascendant path means the path 
 * doesn't have the last one or more tokens of the current path. For example:
 * <pre>
 *   "/user" is an ascendant path of "/user/[number]"
 *   "/user" is an ascendant path of "/user/[number]/profile"
 * </pre>
 * </p>
 * 
 * <p>A descendant path means the opposite. It contains more tokens at the end of 
 * the current path. For example,
 * <pre>
 *   "/user/[number]" is a descendant path of "/user"
 *   "/user/[number]/profile" is a descendant path of "/user/[number]"
 *   "/user/[number]/profile" is also a descendant path of "/user"
 * </pre>
 * </p>
 * 
 * <p>A registered handler might serve the descendant paths of its own specified path
 * when registered if there is no registered handler for those descendant paths.
 * But, it cannot serve the ascendant paths.</p>
 * 
 * <p>For example, the handler registered at <tt>"/user/[number]"</tt> can serve 
 * the path <tt>"/user/32"</tt>. It also might serve the path <tt>"/user/32/profile"</tt> 
 * if there is no handler registered at <tt>"/user/[number]/profile"</tt>. But, 
 * this handler cannot serve <tt>"/user"</tt>. A default handler will be called 
 * if there is no handler registered for <tt>"/user"</tt>.</p>
 * 
 * <p>When a handler is removed from that registered path, the handler of the nearest 
 * ascendant path will serve the path.</p>
 * 
 * <p>For example, if a handler is removed from <tt>"/user/[number]/profile"</tt>, 
 * the handler registered at <tt>"/user/[number]"</tt> will serve this path. If 
 * there is neither a handler registered, the handler at <tt>"/user"</tt> will be used.
 * The default handler (registered at <tt>"/"</tt>) will be used if no registered 
 * paths found.</p>
 * 
 * <p>A default handler can be registered. It's simply the handler registered at 
 * <tt>"/"</tt>.</p>
 * 
 * <p>This implementation of this interface is used in class 
 * <tt>RESTfulDispatcherServlet</tt> for handler registration and dispatch.</p>
 * 
 * @see RESTfulDispatcherServlet
 * @see RESTfulPath
 * @See RESTfulHandler
 * @See RESTfulRegistration
 * 
 * @author Di Zhang (Daniel)
 *
 */
public interface RESTfulHandlerRegistry {
	RESTfulHandlerRegistration register(RESTfulHandlerRegistration registration);
	
	/**
	 * This is a convenient method for registration. See 
	 * register(RESTfulPath, RESTfulHandler) for more details.
	 * 
	 * @param path
	 * @param handler
	 * @return
	 */
	RESTfulHandlerRegistration register(String path, RESTfulHandler handler);
	
	/**
	 * Register a RESTfulHandler to the given path (RESTfulPath).
	 * 
	 * @param path The RESTfulPath that the handler will be registered
	 * @param handler The RESTfulHandler that will serve the path
	 * @return The registration instance of the path and handler pair
	 */
	RESTfulHandlerRegistration register(RESTfulPath path, RESTfulHandler handler);
	
	void deregister(RESTfulHandlerRegistration registration);
	
	void deregister(String path);
	
	void deregister(RESTfulPath path);
	
	RESTfulHandlerRegistration getHandlerRegistration(RESTfulPath path);
	
	RESTfulHandlerRegistration getHandlerRegistration(String path);
	
	/**
	 * Convenient method to find a handler for a given path.
	 * 
	 * @param path
	 * @return
	 */
	RESTfulHandlerRegistration find(RESTfulPath path);
	
	void clear();
}
