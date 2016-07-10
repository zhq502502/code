package com.seegle.Servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.seegle.data.FileOperation;
import com.seegle.data.SQLOperation;
import com.seegle.form.VideoInfoActionForm;

public class UploadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public UploadFileServlet() {
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
		FileOperation fo = new FileOperation();
		String orgid = session.getAttribute("orgid").toString();
		String path1 = "/upload/"+orgid+"/";
		String path = session.getServletContext().getRealPath(path1);
		String uploadPath = "";
		String text = "上传失败";
		String action = request.getParameter("action"); 	
		if("uploadvedio".equalsIgnoreCase(action)){ //上传录像文件	
			File file = new File(path);
			boolean isMultipart = ServletFileUpload.isMultipartContent(request); //检查输入请求是否为multipart表单数据
			if(isMultipart){
				DiskFileItemFactory factory = new DiskFileItemFactory();
				try{					
			    ServletFileUpload upload = new ServletFileUpload(factory);
			    upload.setSizeMax(200*1024*1024); //最大200M
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
			        			if(extension.equals("smvx") || extension.equals("rmvb") || extension.equals("wmv")){
			        				//获取上传文件日期
				        	 	  	Date DT = new Date();
				        	        SimpleDateFormat SimDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				        	        String StrDA = SimDF.format(DT);
				        	        //获取上传文件hash名
				        	        SimpleDateFormat format = new SimpleDateFormat("yyMMddhhmmss");
				        			int rand = new Random().nextInt(1000);
				        			String hashname = format.format(new Date()) + rand;
				        			hashname= hashname+"."+extension; 
				        			
				        			//File f = new File(path+File.separator+filePath);
				    	 	        //f.renameTo(new File(path+File.separator+hashname));
				        			//获取上传文件大小
				        			double size = item.getSize();
				        			DecimalFormat df=new DecimalFormat("###.##"); 
				        			String unit = "B";
				        			if (size > (1024 * 1024 * 1024)) {
				        				unit = "GB";
				        				size = size/(1024 * 1024 * 1024);
				        			} else if (size > (1024 * 1024)) {
				        				unit = "MB";
				        				size = size/(1024 * 1024);
				        			} else if (size > 1024) {
				        				unit = "KB";
				        				size = size/1024;
				        			}  
				        			String sf = df.format(size) ;
				        			
				        			uploadPath = path+"\\"+hashname;
				        	 	  	if (!file.exists()) {   
				        	 	  		file.mkdir();
				        	 	  	}
				        	 	  	out.println(uploadPath);
				        	 	  	item.write(new File(uploadPath));

				        	 	  	//添加文件信息到数据库
				        	 	  	VideoInfoActionForm vif = new VideoInfoActionForm();
				        	 	  	vif.setDel_url(path);
				        	        vif.setDownload_url(path1);
				        	        vif.setFile_hash_name(hashname) ;
				        	        vif.setFile_real_name(filePath) ;
				        	        vif.setFile_size(sf+unit) ;
				        	        vif.setFile_text("") ;
				        	        vif.setFile_time(StrDA) ;
				        	        vif.setFile_categories(1);
				        	        vif.setFile_down_power(0);
				        	        vif.setFile_flag(0);
				        	        vif.setFile_view_power(0);
				    	 			fo.addFile(orgid,vif);
				    	 	        
				    	 			text = "上传成功！";
				        	 	  	flag = 0; //文件保存好
			        			}else{
			        				text = "请重新选择文件上传.上传文件类型必须为smvx、rmvb或者wmv！";
			        				flag=5;
					        		out.println("文件类型错误.上传文件文件类型必须为smvx、rmvb或者wmv！");
			        			}		        			
			        		}else{
			        			text = "文件名不能为空.请重新选择文件上传.";
			        			flag=4;
				        		out.println("文件名为空");
			        		}
			        	}else{
			        		text = "文件大小不能为0字节.请重新选择图片上传.";
			        		flag=3;
			        		out.println("文件内容为空");
			        	}
			        }  
			    }
				}catch (Exception ex) {
			         text = "上传出现错误！";
			         flag=2; //上传错误
			         ex.printStackTrace();
		        }
			}else{
				text = "表单必须为multipart类型！";
				flag=1;
				out.println("表单必须为multipart");
			}
			String pageAlert = "<script type='text/javascript'>alert('" + text + "')</script>";
	        request.setAttribute("msg", pageAlert);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/ConfPage.go?inc=VideoUpload");
	        dispatcher.forward(request, response); 
		}
	}
}
