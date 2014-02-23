package zhangdi.java.rest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RESTfulPathToken {
	
	static final String TOKEN_NUMBER    = "[number]";
	static final String TOKEN_UUID      = "[uuid]";
	static final Pattern PATTERN_NUMBER = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+");
	static final Pattern PATTERN_UUID   = Pattern.compile("[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}");
	
	String value;
	int index;
	RESTfulPath path;
	
	Pattern pattern;
	
	public RESTfulPathToken(RESTfulPath path, String token, int index) {
		this.setPath(path);
		this.setValue(token);
		this.setIndex(index);
	}
	
	private void initPattern() {
		if (TOKEN_NUMBER.equals(value)) {
			this.pattern = PATTERN_NUMBER;
		} else if (TOKEN_UUID.equals(value)) {
			this.pattern = PATTERN_UUID;
		} else {
			this.pattern = Pattern.compile(value);
		}		
	}
	
	public RESTfulPathToken previous() {
		if (index == 0) {
			return null;
		}
		
		return path.get(index-1);
	}
	
	public RESTfulPathToken next() {
		if (index == path.size()-1) {
			return null;
		}
		
		return path.get(index+1);
	}
	
	public RESTfulPath getPath() {
		return path;
	}
	
	public void setPath(RESTfulPath path) {
		if (path == null) throw new NullPointerException("Path cannot be null.");
		
		this.path = path;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String token) {
		if (token == null) throw new NullPointerException("Token cannot be null.");
		this.value = token;
		// reset the pattern, pattern will be re-compiled when method "match" is called.
		this.pattern = null;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		if (index < 0) {
			throw new IllegalArgumentException("Index cannot be negtive number: " + index);
		}

		if (index >= path.size()) {
			throw new IllegalArgumentException(
					"Index cannot be bigger than the length of path (" + 
					path.size() + 
					"): " + index);
		}
		
		this.index = index;
	}
	
	public boolean matches(RESTfulPathToken input) {
		if (this.pattern == null) {
			initPattern();
		}

		Matcher matcher = this.pattern.matcher(input.getValue());
		return matcher.matches();
	}
	
	@Override
	public String toString() {
		return value;
	}
	
	@Override
	public int hashCode() {
		return value.hashCode();
	}
}
