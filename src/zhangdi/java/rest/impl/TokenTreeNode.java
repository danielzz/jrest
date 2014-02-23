package zhangdi.java.rest.impl;

import java.util.HashMap;
import java.util.Map;

import zhangdi.java.rest.RESTfulHandler;
import zhangdi.java.rest.RESTfulPathToken;

public class TokenTreeNode {
	RESTfulPathToken token;
	
	RESTfulHandler handler;
	
	TokenTreeNode parent;
	Map<RESTfulPathToken, TokenTreeNode> children;
	
	public TokenTreeNode(TokenTreeNode parent) {
		this.parent = parent;
		this.children = new HashMap<RESTfulPathToken, TokenTreeNode>();
	}
	
	public TokenTreeNode(RESTfulPathToken token, RESTfulHandler handler) {
		this(null);
		this.token = token;
		this.handler = handler;
	}
	
	public TokenTreeNode getParent() {
		return this.parent;
	}
	
	public void setParent(TokenTreeNode parent) {
		this.parent = parent;
	}
	
	public RESTfulPathToken getToken() {
		return token;
	}
	
	public void setToken(RESTfulPathToken token) {
		this.token = token;
	}
	
	public RESTfulHandler getHandler() {
		return handler;
	}
	
	public void setHandler(RESTfulHandler handler) {
		this.handler = handler;
	}
	
	public TokenTreeNode findChild(RESTfulPathToken token) {
		TokenTreeNode node = this.children.get(token);
		if (node == null) {
			for (RESTfulPathToken childToken : this.children.keySet()) {
				if (childToken.matches(token)) {
					return this.children.get(childToken);
				}
			}
		}
		
		return node;
	}
	
	public void addChild(RESTfulPathToken token, TokenTreeNode node) {
		this.children.put(token, node);
		node.setParent(this);
	}
	
	public void removeChild(RESTfulPathToken token) {
		this.children.remove(token);
	}
	
	public void removeChild(TokenTreeNode node) {
		this.children.remove(node.getToken());
	}
}
