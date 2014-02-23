package zhangdi.java.rest;

public interface Template {
	void addParameter(String key, Object value);
	
	String getResult();
}
