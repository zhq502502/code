package com.seegle.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import com.seegle.data.FileOperation;
import com.seegle.form.VideoInfoActionForm;
import com.seegle.util.PropUtil;
import com.seegle.util.SeegleLog;

public class VideoUploadAction extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";
    private static final String top_videoupload = "top_videoupload";
    private String fileSizeLimit = "2047";
	private File oldFile;
	private FileOperation fo = new FileOperation();
	private String orgid;

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        orgid = session.getAttribute("orgid")==null?orgid:session.getAttribute("orgid").toString();
//        SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("debug in UserSetManagerAction");
        String upload = request.getParameter("upload");
        //获取允许上传的文件最大大小
        fileSizeLimit = PropUtil.getInstance().getValue("fileupload.limitsize");
        request.setAttribute("fileSizeLimit", fileSizeLimit);
        if(upload==null||"".equals(upload)){
    		String top = request.getParameter("top");
    		if(!(top==null||top.equals(""))){
    			request.setAttribute("top", top);
    			return mapping.findForward(top_videoupload);
    		}
        	return mapping.findForward(SUCCESS);
        }else if(upload.equals("upload")){
        	
    		//文件保存位置，当前项目下的upload/mail
    		String uploadDir = "upload" + File.separatorChar +orgid+ File.separatorChar;
    		String ctxDir = session.getServletContext().getRealPath(String.valueOf(File.separatorChar));
    		if (!ctxDir.endsWith(String.valueOf(File.separatorChar))) {
    			ctxDir = ctxDir + File.separatorChar;
    		}
    		File savePath = new File(ctxDir + uploadDir);
    		if (!savePath.exists()) {
    			savePath.mkdirs();
    		}
    		String saveDirectory = ctxDir + uploadDir ;
    		
    		if (StringUtils.isBlank(this.fileSizeLimit.toString())) {
    			this.fileSizeLimit = "2047";// 默认2047M
    		}
    		int maxPostSize = Integer.parseInt(this.fileSizeLimit) * 1024 * 1024;
    		String encoding = "UTF-8";
    		//文件名命名规则
    		MyFileRenamePolicy rename = new MyFileRenamePolicy();
    		MultipartRequest multi = null;
    		try {
    			multi = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, rename);
    		} catch (IOException e) {
    			SeegleLog.getInstance().getLog(this.getClass(), orgid).warn("用户取消上传文件"+e);
    			e.printStackTrace();
    		}
    		
    		
    		// 输出
    		Enumeration files = multi.getFileNames();
    		while (files.hasMoreElements()) {
    			String name = (String) files.nextElement();
    			File f = multi.getFile(name);
    			if (f != null) {
    				String fileName = multi.getFilesystemName(name);
    				String lastFileName = saveDirectory + "/" + fileName;
    				String fileSavePath = uploadDir  + fileName;
    				//获取上传文件日期
    				Date DT = new Date();
    				SimpleDateFormat SimDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    				String StrDA = SimDF.format(DT);
    				//文件大小
    				FileInputStream fileinput = new FileInputStream(f);
    				double size = fileinput.available();
    				fileinput.close();
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
        			
    				//添加文件信息到数据库
				  	VideoInfoActionForm vif = new VideoInfoActionForm();
				  	vif.setDel_url(saveDirectory);
				    vif.setDownload_url("/"+uploadDir.replace("\\", "/"));
				    vif.setFile_hash_name(fileName) ;
				    vif.setFile_real_name(oldFile.getName()) ;
				    vif.setFile_size(sf+unit) ;
				    vif.setFile_text("") ;
				    vif.setFile_time(StrDA) ;
				    vif.setFile_categories(1);
				    vif.setFile_down_power(0);
				    vif.setFile_flag(0);
				    vif.setFile_view_power(0);
					fo.addFile(orgid,vif);
    				response.getWriter().print(fileSavePath);
    			}
    		}
    		files = null;
        	return null;
        }else if(upload.equals("del")){
        	String filePath = request.getParameter("filePath");
    		String prjPath = request.getSession().getServletContext().getRealPath("/");
    		PrintWriter out = response.getWriter();
    		File file = new File(prjPath+filePath);
    		if(file.exists()){
    			//本地删除文件
    			String filename_new = filePath.replace("\\", "/");
    			String filenames [] = filename_new.split("/");
    			String filename = filenames[filenames.length-1];
    			fo.delFile(orgid, filename,prjPath+filePath);
    			file.delete();
    			out.print("success");
    		}
        	return null;
        }else{
        	return null;
        }
    }
    /**
     * 重命名文件
     * @author zhangqing
     * @date 2013-5-15 下午04:11:59
     */
    class MyFileRenamePolicy implements FileRenamePolicy {
    	private String fileName;
		public File rename(File file) {
			oldFile = file;
			//年月日+随机数的方式重命名文件
	        SimpleDateFormat format = new SimpleDateFormat("yyMMddhhmmss");
			int rand = new Random().nextInt(1000);
			String hashname = format.format(new Date()) + rand;
			String fileSaveName = StringUtils.join(new String[] { hashname, ".",
					FilenameUtils.getExtension(file.getName()) });
			File result = new File(file.getParentFile(), fileSaveName);
			fileName = result.getName();
			return result;
		}
		public String getFileName(){
			return this.fileName;
		}
	}
}

