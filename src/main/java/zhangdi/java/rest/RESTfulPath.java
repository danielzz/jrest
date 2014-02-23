package zhangdi.java.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class RESTfulPath implements List<RESTfulPathToken> {
	
	String path;
	ArrayList<RESTfulPathToken> tokens;
	int size;
	
	public RESTfulPath(String path) {
		this.path = path;
		parse(path);
	}
	
	private void parse(String path) {
		this.tokens = new ArrayList<RESTfulPathToken>();
		
		String str = path.trim();
		if (str.startsWith("/")) {
			str = str.substring(1);
		}
		
		String[] arr = str.split("/");
		size = arr.length;
		for (int i = 0; i < arr.length; i++) {
			RESTfulPathToken token = new RESTfulPathToken(this, arr[i], i);
			tokens.add(token);
		}
	}
	
	public RESTfulPathToken[] getTokens() {
		return tokens.toArray(new RESTfulPathToken[0]);
	}
	
	@Override
	public String toString() {
		return path;
	}
	
	@Override
	public int hashCode() {
		return path.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		
		if (!(o instanceof RESTfulPath)) {
			return false;
		}
		
		RESTfulPath a = (RESTfulPath)o;
		return this.path.equals(a.path);
	}

	@Override
	public boolean add(RESTfulPathToken arg0) {
		throw new UnsupportedOperationException("RESTfulPath is immutable.");
	}

	@Override
	public void add(int arg0, RESTfulPathToken arg1) {
		throw new UnsupportedOperationException("RESTfulPath is immutable.");
	}

	@Override
	public boolean addAll(Collection<? extends RESTfulPathToken> arg0) {
		throw new UnsupportedOperationException("RESTfulPath is immutable.");
	}

	@Override
	public boolean addAll(int arg0, Collection<? extends RESTfulPathToken> arg1) {
		throw new UnsupportedOperationException("RESTfulPath is immutable.");
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException("RESTfulPath is immutable.");
	}

	@Override
	public boolean contains(Object o) {
		return this.tokens.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return this.tokens.containsAll(c);
	}

	@Override
	public RESTfulPathToken get(int index) {
		return this.tokens.get(index);
	}

	@Override
	public int indexOf(Object o) {
		return this.tokens.indexOf(o);
	}

	@Override
	public boolean isEmpty() {
		return this.tokens.isEmpty();
	}

	@Override
	public Iterator<RESTfulPathToken> iterator() {
		return this.tokens.iterator();
	}

	@Override
	public int lastIndexOf(Object o) {
		return this.tokens.lastIndexOf(o);
	}

	@Override
	public ListIterator<RESTfulPathToken> listIterator() {
		return this.tokens.listIterator();
	}

	@Override
	public ListIterator<RESTfulPathToken> listIterator(int index) {
		return this.tokens.listIterator(index);
	}

	@Override
	public boolean remove(Object o) {
		throw new UnsupportedOperationException("RESTfulPath is immutable.");
	}

	@Override
	public RESTfulPathToken remove(int index) {
		throw new UnsupportedOperationException("RESTfulPath is immutable.");
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException("RESTfulPath is immutable.");
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException("RESTfulPath is immutable.");
	}

	@Override
	public RESTfulPathToken set(int index, RESTfulPathToken token) {
		throw new UnsupportedOperationException("RESTfulPath is immutable.");
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public List<RESTfulPathToken> subList(int fromIndex, int toIndex) {
		return this.tokens.subList(fromIndex, toIndex);
	}

	@Override
	public Object[] toArray() {
		return this.tokens.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return this.tokens.toArray(a);
	}
}
