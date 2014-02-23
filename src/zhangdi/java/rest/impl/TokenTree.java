package zhangdi.java.rest.impl;

import zhangdi.java.rest.RESTfulHandler;
import zhangdi.java.rest.RESTfulPath;
import zhangdi.java.rest.RESTfulPathToken;

public class TokenTree {
	
	static final RESTfulHandler notfound = new ResourceNotFoundHandler();
	
	TokenTreeNode root;
	
	RESTfulHandler notFoundHandler = notfound;

	public TokenTree() {
		init();
	}
	
	public void clear() {
		init();
	}
	
	public void setDefaultHandler(RESTfulHandler handler) {
		this.root.setHandler(handler);
	}
	
	public RESTfulHandler getDefaultHandler() {
		return this.root.getHandler();
	}
	
	public void removeDefaultHandler() {
		this.root.setHandler(this.notFoundHandler);
	}
	
	public void setNotFoundHandler(RESTfulHandler handler) {
		this.notFoundHandler = handler;
	}
	
	public void addNode(RESTfulPath path, RESTfulHandler handler) {
		if (path.size() == 0) {
			this.setDefaultHandler(handler);
			return;
		}
		
		TokenTreeNode node = root;
		for (int i = 0; i < path.size(); i++) {
			RESTfulPathToken pathToken = path.get(i);
			TokenTreeNode child = node.findChild(pathToken);
			if (child == null) {
				child = new TokenTreeNode(node);
				child.setToken(pathToken);
				node.addChild(pathToken, child);
				node = child;
				if (i == (path.size()-1)) {
					node.setHandler(handler);
					return;
				} else {
					node.setHandler(node.getParent().getHandler());
				}
				continue;
			} else if (i == (path.size()-1)) {
				child.setHandler(handler);
				return;
			} else {
				node = child;
			}
		}
	}
	
	public TokenTreeNode findNode(RESTfulPath path) {
		if (path.size() == 0) {
			return root;
		}
		
		TokenTreeNode node = root;
		for (int i = 0; i < path.size(); i++) {
			RESTfulPathToken token = path.get(i);
			TokenTreeNode child = node.findChild(token);
			if (child == null) {
				return null;
			} else if (i == (path.size()-1) || child.children.size() == 0) {
				return child;
			}
			node = child;
		}
		
		return null;
	}
	
	public RESTfulHandler findHandler(RESTfulPath path) {
		TokenTreeNode node = findNode(path);
		
		if (node == null) {
			return root.getHandler();
		}
		
		return node.getHandler();
	}
	
	private void init() {
		root = new TokenTreeNode(null);
		root.setHandler(notFoundHandler);
	}
}
