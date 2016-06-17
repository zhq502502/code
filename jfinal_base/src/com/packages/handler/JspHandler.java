package com.packages.handler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jfinal.handler.Handler;

public class JspHandler extends Handler{


	@Override
	public void handle(String target, HttpServletRequest request,
			HttpServletResponse response, boolean[] isHandled) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		//List<Sysitem> lsAuthorizes = Sysitem.dao.getListAuthorizes();
		//System.out.println("lsAuthorizes.size(): "+lsAuthorizes.size());
		HttpSession session = request.getSession();
		//Tblemployee employee = (Tblemployee) session.getAttribute("user");
		Object employee =  session.getAttribute("user");
		//System.out.println(employee);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");		
		System.out.println("target: "+target);
		int index = target.lastIndexOf(".jsp");
		//System.out.println("index: "+index);
		if (target.endsWith(".jsp")){
			if("/login.jsp".equals(target) || "/inc/mail/temp1.jsp".equals(target) || "/inc/mail/temp2.jsp".equals(target) || "/inc/mail/temp3.jsp".equals(target)|| "/print.jsp".equals(target)){
				return;
			} 
			else if("/header.jsp".equals(target) || "/org.jsp".equals(target) || "/sidebar.jsp".equals(target)
					|| "/blank.jsp".equals(target) || "/home.jsp".equals(target)){
				if(employee == null || "".equals(employee)){
					try {
						response.sendRedirect("./logout");
					} catch (IOException e) {
						e.printStackTrace();
					}
					return;
				} else {
					return;
				}
			} else {
				//isHandled[0] = true;
				if(employee == null){
					nextHandler.handle("./logout", request, response, isHandled);
				}				
				/*try {
					String rid = request.getParameter("id");//popup页面加载使用ID参数				
					PrintWriter out = response.getWriter();
					CharResponseWrapper newResponse = new CharResponseWrapper(response);
					request.getRequestDispatcher(target).include(request, newResponse);					
					String output = newResponse.getOutput();
					//System.out.println(output);
					Document doc = Jsoup.parse(output, "UTF-8"); 
					int authObjectId = -1;
					if(rid != null && !"".equals(rid)){
						authObjectId = Integer.parseInt(rid);
					} else {
						String encodefile = target.substring(1, index);
						String authObjectStr = StringUtil.Base64decode(encodefile);	
						if(authObjectStr != null){
							authObjectId = Integer.parseInt(authObjectStr);
						}
					}
					if(rid != null && !"".equals(rid)){
						Element ele = doc.getElementById(rid);
						doc.empty();
						doc.append(ele.outerHtml());
					}
					List<Sysitem> lsSubAuthorizes = Sysitem.dao.getSubAuthorizesById(authObjectId);//当前页面的所有控件
					//System.out.println("lsSubAuthorizes.size(): "+lsSubAuthorizes.size());
					for(Sysitem item : lsSubAuthorizes){
						String itemid = item.get("id").toString();
						Element element = doc.getElementById(itemid);
						boolean canRead = s.isReadable(employee, item);
						boolean canWrite = s.isWriteable(employee, item);
						if(element != null){
							if(!canRead){ //不可读
								element.remove();		
							}
							else if(!canWrite && canRead){ //可读不可写
								if("button".equalsIgnoreCase(element.nodeName())){
									element.attr("disabled", "disabled");
								} else {
									element.attr("readonly", "readonly");
								}
							}
						}
						//System.out.println("id:"+item.getInt("id")+"---"+"type:"+item.getStr("itemtype")+item.getStr("readposts")+item.getStr("writeposts"));
					}
					
					
					out.write(doc.toString());
					out.flush();
					out.close();
					return;
				} catch (Exception e) {
					e.printStackTrace();
				}*/
			}
		} else {
			/*if(target.equals("/")){
				try {
					response.sendRedirect("login.jsp");
				} catch (Exception e) {
					e.printStackTrace();
				}
				return;
			}
			else */
			if(target.contains("/html/")){
				/*if(employee == null){
					try {
						isHandled[0] = true;
						response.getWriter().print(401);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return;
				}*/
			}
		}
		nextHandler.handle(target, request, response, isHandled);		
	}

}
