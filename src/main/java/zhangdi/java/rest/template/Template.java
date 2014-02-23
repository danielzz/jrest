package zhangdi.java.rest.template;

import java.io.InputStream;
import java.io.Reader;

public interface Template {
	void add(String key, Object value);
	
	String getResult();
	
	InputStream getInputStream();
	
	Reader getReader();
}
