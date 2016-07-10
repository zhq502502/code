package com.seegle.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class Paging extends TagSupport{
	private static final long serialVersionUID = 4646399007324236478L;
	/**action地址*/
	private String action;
	/**分页模版地址*/
	private String model;
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	@Override
	public int doStartTag() throws JspException {
		String hrefUrl = action;
		this.getRequest().setAttribute("hrefUrl", hrefUrl);
		try {
			if(model==null||model.equals("")){
				model="/page/util/paging.jsp";
			}
			this.pageContext.include(model);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return super.doStartTag();
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
	 */
	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		return super.doEndTag();
	}
	
	public HttpServletRequest getRequest(){
		return (HttpServletRequest) pageContext.getRequest();
	}
}
