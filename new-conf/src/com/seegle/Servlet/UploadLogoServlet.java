package com.seegle.Servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.seegle.data.SQLOperation;
import com.seegle.util.PropUtil;
/**
 * 上传log图片
 * @author zhangqing
 * @date 2013-7-5 上午11:23:00
 */
public class UploadLogoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public UploadLogoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		int flag = -1;
		SQLOperation so = new SQLOperation();
		String orgid = session.getAttribute("orgid").toString();
		String path1 = "/"+PropUtil.getInstance().getValue("logo.path")+"/"+orgid+"/";
		//String path = session.getServletContext().getRealPath(path1);
		//String uploadPath = "";
		String text = "修改失败";
		String action = request.getParameter("action"); 	
		String language = request.getParameter("language"); 
		if("changelogo".equalsIgnoreCase(action)){ //上传logo	
			//File file = new File(path);
			boolean isMultipart = ServletFileUpload.isMultipartContent(request); //检查输入请求是否为multipart表单数据
			if(isMultipart){
				DiskFileItemFactory factory = new DiskFileItemFactory();
				try{					
			    ServletFileUpload upload = new ServletFileUpload(factory);
			    upload.setSizeMax(2*1024*1024); //最大2M
			    List<FileItem> items = upload.parseRequest(request);
			    Iterator<FileItem> itr = items.iterator();
			    while (itr.hasNext()) {
			    	FileItem item = (FileItem) itr.next();
			        //检查当前项目是普通表单项目还是上传文件。
			        if (item.isFormField()) {  //是普通表单项目
			        } else {
			        	if(item.getSize()!=0){   //文件大小不为零
			        		
			        		String filePath = item.getName();
			        		if(filePath!=null&&!filePath.trim().equals("")){ //文件名不为空
			        			int point = filePath.lastIndexOf("."); 
			        			String extension = filePath.substring(point+1,filePath.length()); //获取文件后缀名
			        			if(extension.equals("gif") || extension.equals("jpg") || extension.equals("bmp") || extension.equals("png")){
			        				
			        				String newName = orgid+"_"+Calendar.getInstance().getTimeInMillis()+"_image."+extension;
				    	 			if(so.updateOrglogo(orgid,newName)&&so.updateOrgLogoBlob(orgid, item.getInputStream())){
				    	 				if("en".equalsIgnoreCase(language)){
				    	 					text = "logo uploaded successfully!";
				    	 				}else if("zh_tw".equalsIgnoreCase(language)){
				    	 					text = "logo上傳成功！";
				    	 				}
				    	 				else{
				    	 					text = "logo上传成功！";
				    	 				}
				    	 				flag = 0; //文件保存好
				    	 			}else{
				    	 				if("en".equalsIgnoreCase(language)){
				    	 					text = "logo save failed";
				    	 				}else if("zh_tw".equalsIgnoreCase(language)){
				    	 					text = "logo保存失敗";
				    	 				}
				    	 				else{
				    	 					text = "logo保存失败";
				    	 				}
				    	 				text = "logo保存失败";
				        				flag=5;
				        				//out.println("logo保存失败");
				    	 			}
			        			}else{
			        				if("en".equalsIgnoreCase(language)){
			    	 					text = "Please re-select image upload. Upload file type must be jpg, png, gif or bmp!";
			    	 				}else if("zh_tw".equalsIgnoreCase(language)){
			    	 					text = "請重新選擇圖片上傳.上​​傳文件類型必須為jpg、png、gif或者bmp！";
			    	 				}
			    	 				else{
			    	 					text = "请重新选择图片上传.上传文件类型必须为jpg、png、gif或者bmp！";
			    	 				}
			        				flag=5;
					        		//out.println("文件类型错误.上传文件文件类型必须为jpg、png、gif或者bmp！");
			        			}		        			
			        		}else{
			        			if("en".equalsIgnoreCase(language)){
		    	 					text = "Image name can not be empty Please re-select image upload.";
		    	 				}else if("zh_tw".equalsIgnoreCase(language)){
		    	 					text = "圖片名不能為空.請重新選擇圖片上傳.";
		    	 				}
		    	 				else{
		    	 					text = "图片名不能为空.请重新选择图片上传.";
		    	 				}
			        			flag=4;
				        		//out.println("文件名为空");
			        		}
			        	}else{
			        		if("en".equalsIgnoreCase(language)){
	    	 					text = "File size can not be 0 bytes., Please re-select image upload.";
	    	 				}else if("zh_tw".equalsIgnoreCase(language)){
	    	 					text = "文件大小不能為0字節.請重新選擇圖片上傳.";
	    	 				}
	    	 				else{
	    	 					text = "文件大小不能为0字节.请重新选择图片上传.";
	    	 				}
			        		flag=3;
			        		//out.println("文件内容为空");
			        	}
			        }  
			    }
				}catch (Exception ex) {
					if("en".equalsIgnoreCase(language)){
	 					text = "Upload error";
	 				}else if("zh_tw".equalsIgnoreCase(language)){
	 					text = "上傳出現錯誤";
	 				}
	 				else{
				         text = "上传出现错误！";
	 				}
			         flag=2; //上传错误
			         ex.printStackTrace();
		        }
			}else{
				if("en".equalsIgnoreCase(language)){
 					text = "Form must be multipart type";
 				}else if("zh_tw".equalsIgnoreCase(language)){
 					text = "表單必須為multipart類型";
 				}
 				else{
 					text = "表单必须为multipart类型！";
 				}
				flag=1;
				//out.println("表单必须为multipart");
			}
			out.print(flag);
			String pageAlert = "<script type='text/javascript'>alert('" + text + "')</script>";
	        request.setAttribute("msg", pageAlert);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/ConfPage.go?inc=ChangeLogo");
	        dispatcher.forward(request, response); 
		}else if("renewlogo".equalsIgnoreCase(action)){ //恢复默认logo
			//System.out.println("renewlogo");
			String orglogo = so.getOrglogo(orgid);
			//System.out.println("orglogo："+orglogo);
			if(orglogo==null||orglogo.trim().equals("")){
				flag=1;
				//System.out.println("当前已经为默认logo！");
			}else{
				String logopath = path1+orglogo;
				String realpath = request.getSession().getServletContext().getRealPath(logopath);
				//System.out.println("realpath："+realpath);
				File file = new File(realpath);
				if (file.isFile()&& file.exists()) {   
			        file.delete();    
			    }
				if(so.updateOrglogo(orgid.toString(),"")&&so.updateOrgLogoBlob(orgid, null)){
					flag=0;
				}else{
					flag=1;
				}
			}
			out.print(flag); 
		}
		//out.print(flag); 	
	}
}
