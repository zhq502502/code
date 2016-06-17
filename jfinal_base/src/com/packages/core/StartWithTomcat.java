package com.packages.core;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.taglibs.standard.tag.common.sql.UpdateTagSupport;

import com.packages.util.PathUtil;
import com.packages.util.PropUtil;

/**
 * Servlet implementation class StartWithTomcat
 */
@WebServlet("/StartWithTomcat")
public class StartWithTomcat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartWithTomcat() {
        super();
    }

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		//将项目路劲保存在内存中
		PathUtil.setProjectPath(this.getServletContext().getRealPath("/"));
		//将页面中常量字符串保存在application中
		ServletContext application = this.getServletContext();
		List<String> jspKeys = PropUtil.getInstance().getKeyStartWith("jsp.");
		for(String key:jspKeys){
			//System.out.println(PropUtil.getInstance().getValue(key));
			application.setAttribute(key.replace("jsp.", ""), PropUtil.getInstance().getValue(key));
		}
		SystemInfo.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
		PropUtil.getInstance().initProp();
	}
	
    

}
