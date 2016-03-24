package com.rlglsys.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public final class Context<T extends Loginable> implements Serializable {
	public static final String CONTEXT = "CONTEXT";
	public static enum ScopeType {REQUEST, SESSION, APPLICATION}
	private static final ThreadLocal<HttpServletRequest> REQ = new ThreadLocal<HttpServletRequest>();
	private static final ThreadLocal<Map<ScopeType, Map<String, Object>>> SCOPES =
	    	new ThreadLocal<Map<ScopeType, Map<String, Object>> >() {
			@Override
			protected Map<ScopeType, Map<String, Object>> initialValue() {
				Map<ScopeType, Map<String, Object>> scopes = new HashMap<ScopeType, Map<String, Object>>();
				scopes.put(ScopeType.APPLICATION, new HashMap<String, Object>());
				scopes.put(ScopeType.SESSION, new HashMap<String, Object>());
				scopes.put(ScopeType.REQUEST, new HashMap<String, Object>());
				return scopes;
			}
		};
    private Map<String, Object> repository = new HashMap<String, Object>();

	public static <T extends Loginable> Context<T> getCurrent() {
        Context<T> ctx = (Context<T>) getAttribute(ScopeType.SESSION, CONTEXT);
        if (ctx == null) {
            ctx = new Context<T>();
            setAttribute(ScopeType.SESSION, CONTEXT, ctx);
        }
        return ctx;
    }
	private static Map<String, Object> getScope(ScopeType type) {
		return SCOPES.get().get(type);
	}
    public void setValue(String key, Object value) {
        repository.put(key, value);
        setAttribute(ScopeType.SESSION, CONTEXT, this);
    }
	public void setValue(String key1, String key2, Object value) {
		if (key1 != null && key2 != null){

			Object obj = getValue(key1);
			if (obj instanceof Map) {
				((Map) obj).put(key2, value);
			} else {
				Map<String, Object> tmp = new HashMap<String, Object>();
				tmp.put(key2, value);
				obj = tmp;
			}
			setValue(key1, obj);
		}
	}
    public Object getValue(String key) {
        return repository.get(key);
    }
	public Map<String, Object> getValues() {
		 return repository;
	}
	public Object getValue(String key1, String key2) {
		if (key1 == null || key2 == null) return null;

		Object obj = getValue(key1);
		if (obj instanceof Map) {
			return ((Map)obj).get(key2);
		} else {
			return null;
		}
	}
    private static Object getAttribute(ScopeType type, String key) {
    	if (isStandalone()) return getScope(type).get(key);

        switch (type) {
        case REQUEST:
            return getRequest().getAttribute(key);
        case SESSION:
            return getSession().getAttribute(key);
        default:
            return getApplication().getAttribute(key);
        }
    }
	private static boolean isStandalone() {
		return getRequest() == null;
	}
    private static HttpServletRequest getRequest() {
        return Context.REQ.get();
    }

    private static HttpSession getSession() {
        return getRequest().getSession();
    }

    private static ServletContext getApplication() {
        return getSession().getServletContext();
    }
    private static void setAttribute(ScopeType type, String key, Object value) {
    	if (isStandalone()) {
    		getScope(type).put(key, value);
    	} else {
	    	switch (type) {
	        case REQUEST:
	            getRequest().setAttribute(key, value);
	            break;
	        case SESSION:
	            getSession().setAttribute(key, value);
	            break;
	        default:
	            getApplication().setAttribute(key, value);
	        }
    	}
    }
}
