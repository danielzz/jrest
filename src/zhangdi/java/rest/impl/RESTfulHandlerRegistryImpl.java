package zhangdi.java.rest.impl;

import java.util.HashMap;
import java.util.Map;

import zhangdi.java.rest.RESTfulHandler;
import zhangdi.java.rest.RESTfulHandlerRegistration;
import zhangdi.java.rest.RESTfulHandlerRegistry;
import zhangdi.java.rest.RESTfulPath;

public class RESTfulHandlerRegistryImpl implements RESTfulHandlerRegistry {
	
	TokenTree tree;
	
	Map<RESTfulPath, RESTfulHandlerRegistration> cache;
	
	public RESTfulHandlerRegistryImpl() {
		tree = new TokenTree();
		cache = new HashMap<RESTfulPath, RESTfulHandlerRegistration>();
	}

	@Override
	public RESTfulHandlerRegistration register(RESTfulHandlerRegistration registration) {
		return this.register(registration.getPath(), registration.getHandler());
	}

	@Override
	public RESTfulHandlerRegistration register(String path,
			RESTfulHandler handler) {
		return register(new RESTfulPath(path), handler);
	}

	@Override
	public RESTfulHandlerRegistration register(RESTfulPath path,
			RESTfulHandler handler) {
		tree.addNode(path, handler);
		RESTfulHandlerRegistration reg = new RESTfulHandlerRegistrationImpl(path, handler);
		this.cache.put(path, reg);
		return reg;
	}
	
	@Override
	public void deregister(RESTfulHandlerRegistration registration) {
		this.deregister(registration.getPath());
	}

	@Override
	public void deregister(String path) {
		this.deregister(new RESTfulPath(path));
	}

	@Override
	public void deregister(RESTfulPath path) {
		TokenTreeNode node = this.tree.findNode(path);
		
		if (node != null && node.getParent() != null) {
			node.setHandler(node.getParent().getHandler());
		} else if (node != null) {
			tree.removeDefaultHandler();
		}
		
		this.cache.remove(path);
	}

	@Override
	public RESTfulHandlerRegistration getHandlerRegistration(RESTfulPath path) {
		RESTfulHandlerRegistration reg = cache.get(path);
		if (reg != null) {
			return reg;
		}
		
		RESTfulHandler handler = tree.findHandler(path);
		if (handler == null) {
			return null;
		}
		
		reg = new RESTfulHandlerRegistrationImpl(path, handler);
		this.cache.put(path, reg);
		
		return reg;
	}

	@Override
	public RESTfulHandlerRegistration getHandlerRegistration(String path) {
		return this.getHandlerRegistration(new RESTfulPath(path));
	}

	@Override
	public RESTfulHandlerRegistration find(RESTfulPath path) {
		return this.getHandlerRegistration(path);
	}
	
	@Override
	public void clear() {
		this.tree.clear();
		this.cache.clear();
	}

}
