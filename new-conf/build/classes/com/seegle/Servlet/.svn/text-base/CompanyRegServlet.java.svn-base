package com.seegle.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONObject;

import com.seegle.data.GetConfigFile;
import com.seegle.data.HttpClient;
import com.seegle.data.CompanyOperation;
import com.seegle.data.UserOperation;
import com.seegle.util.SeegleLog;

public class CompanyRegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GetConfigFile gcf = new GetConfigFile();//读取配置
	private String url = gcf.getConfig("apiUrl");  //API服务器的地址 ，例如："http://www.seegle.cn"
	
    public CompanyRegServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@SuppressWarnings("static-access")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String orgid = "";
		String orgname = "公司名称测试";
		String userid = "admin";
		String userpass = "";
		String token = "";
		String maxconfcount = "";
		String maxconfsum = "";
		String flag = "0";

//		SeegleLog.getInstance().getLog(this.getClass(), orgid.toString()).debug("Enter CompanyRegServlet.class");
		
		//获取orgid,userpass
		orgid = request.getParameter("orgid").toString().trim();
	 	userpass = request.getParameter("userpass").trim();
		
	 	SeegleLog.getInstance().getLog(this.getClass(), orgid.toString()).info("企业注册：获取orgid:"+orgid);
		
		//登录获取token
		if(orgid!=null && userid!=null) {
			HttpClient hc = new HttpClient(url,orgid);
			String userpass1 = hc.md5(userpass);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("orgid", orgid)); 
			params.add(new BasicNameValuePair("u", userid)); 
			params.add(new BasicNameValuePair("p", userpass1));
			JSONObject jsonObject = hc.getJObject("token", params);	
			token = jsonObject.get("token").toString();
			if(token!=null && token.length()>8){
			} else {
				flag = "-1";
			}
		} else {
			flag = "-2";
		}
		
		SeegleLog.getInstance().getLog(this.getClass(), orgid.toString()).info("企业注册：获取token:"+token);
		
		//连接中心获取企业信息
		HttpClient hc = new HttpClient(url,orgid);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("accessKey", token)); 
		params.add(new BasicNameValuePair("orgid", orgid)); 
		JSONObject json = hc.getJObject("companyreg", params);
		
		SeegleLog.getInstance().getLog(this.getClass(), orgid.toString()).info("企业注册：获取企业信息json:"+json);

		//orgid通过验证后，进行添加企业相关数据操作
		if(json.get("orgid").toString().equals(orgid)){
			orgid = json.get("orgid").toString();
			orgname = json.get("orgname").toString();
			maxconfcount = json.get("maxconfcount").toString();
			maxconfsum = json.get("maxconfsum").toString();
			
			CompanyOperation co =new CompanyOperation();
			boolean exist = co.existCompany(orgid); //判断企业是否存在
			if(!exist){
				boolean addcom = co.addCompany(orgid, orgname); //向company表中添加企业信息
				//boolean create = co.createUsertable(orgid); //创建orgid_user表
				UserOperation uo = new UserOperation(orgid);
				//添加超级管理员
				try{
					uo.addUser(orgid, userid, "Administrator", "", "", "", userpass, "0", "0", "", "0", "", "", "", "", "1");
					//flag = orgname;	
				}catch(Exception e){
					flag = "-3";
					out.print(flag);
				}
				/*if(create){
				//boolean createfile = co.createFiletable(orgid);
				if(!createfile){
					flag = "-7";
				}
				}else{
					flag = "-4";
				｝*/
			}
			else{
				flag = "-5";
			}
		}else{
			flag = "-6";
		}	
		out.print(flag);	
	}

}
