package com.seegle.Servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.seegle.beans.PhoneFacility;
import com.seegle.data.HttpClient;
import com.seegle.util.CenterUtil;
import com.seegle.util.PathUtil;
import com.seegle.util.PropUtil;

import plugin.depart.DepartSyn;

@SuppressWarnings("serial")
public class StartWithTomcat extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public StartWithTomcat() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PropUtil.getInstance().initProp();
		initconfig();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		initconfig();
	}
	
	private void initconfig(){
		//将项目路劲保存在内存中
		PathUtil.setProjectPath(this.getServletContext().getRealPath("/"));
		//将页面中常量字符串保存在application中
		ServletContext application = this.getServletContext();
		List<String> jspKeys = PropUtil.getInstance().getKeyStartWith("constants.jsp.");
		for(String key:jspKeys){
			//System.out.println(PropUtil.getInstance().getValue(key));
			application.setAttribute(key.replace("constants.jsp.", ""), PropUtil.getInstance().getValue(key));
		}
		//错误代码提示信息存入application中
		application.setAttribute("errorCodeList", CenterUtil.getErrorList());
		application.setAttribute("errorCodeList_zh_tw", CenterUtil.getErrorList_zh_tw());
		application.setAttribute("errorCodeList_en", CenterUtil.getErrorList_en());
		initOnlyOneOrgid();
		if(PropUtil.getInstance().getValue("zh.syn").equals("1")){
			new DepartSyn().start();
		}
	}
	/**
	 * 检查中心是否只有一个企业，如果是默认填充orgid为该企业
	 * @author zhangqing
	 * @date 2014-11-7 下午04:23:42
	 */
	public void initOnlyOneOrgid(){
		new Thread(){
			public void run(){
				HttpClient hc = new HttpClient(PropUtil.getInstance().getValue("apiUrl"), "beforLogin");
				List<NameValuePair> params = new ArrayList<NameValuePair>(); 
				params.add(new BasicNameValuePair("begin", "0"));
				params.add(new BasicNameValuePair("count", "2"));
				JSONArray jsonarr = hc.getJArray("orglist", params);
				if(jsonarr==null){
					return;
				}
				int size = jsonarr.size();
				if(size==1){
					JSONObject obj = (JSONObject)jsonarr.get(0);
					PropUtil.getInstance().getProperties().setProperty("orgid", obj.get("orgid").toString());
				}
			}
		}.start();
	}
}
