package zhangdi.java.rest.template;

import java.io.OutputStream;
import java.io.Writer;

public interface Template {
	void add(String key, Object value);
	
	String getResult();
	
	void write(Writer writer);
	
	void write(OutputStream os, String encoding);
}
