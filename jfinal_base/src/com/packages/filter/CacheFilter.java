package com.packages.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class CacheFilter
 */
public class CacheFilter implements Filter {

	private String[][] replyHeaders = { {} };
    /**
     * Default constructor. 
     */
    public CacheFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse httpResponse = (HttpServletResponse) response;
	    for (int n = 0; n < replyHeaders.length; n++) {
	      String name = replyHeaders[n][0];
	      String value = replyHeaders[n][1];
	      httpResponse.addHeader(name, value);
	    }
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		Enumeration<?> names = fConfig.getInitParameterNames();
	    ArrayList<String[]> tmp = new ArrayList<String[]>();
	    while (names.hasMoreElements()) {
	      String name = (String) names.nextElement();
	      String value = fConfig.getInitParameter(name);
	      String[] pair = { name, value };
	      tmp.add(pair);
	    }
	    replyHeaders = new String[tmp.size()][2];
	    tmp.toArray(replyHeaders);
	}

}
