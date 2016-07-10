package com.seegle.data;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import com.seegle.form.UserInfoActionForm;
import com.seegle.form.VideoInfoActionForm;
import com.seegle.util.SeegleLog;

import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *文件操作类
 **/
public class FileOperation {
	private String setName = "set names utf8";	
	    
	/**
	 * 从数据库获取录像列表（分页显示） 
	 * 显示记录的起始位置：firstIndex 每页显示记录数：showNumber 企业id：orgid 搜索关键字：key  录像分类：vc
	 **/
	public ArrayList queryVideoList(String orgid,String key, int vc, int firstIndex, int showNumber) {
		Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
        String sql = "";
        String countSql = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList videoList = new ArrayList();
        int pageNumber = 1;  //获取分页数，默认分1页
        int maxNumber = 0;  //总记录数

        try {
            if ("".equals(key)) {  //查询所有录像
            	if(vc!=1){ //不是所有分类
	                countSql = "SELECT count(*) FROM file_info where file_flag='0' AND file_categories="+vc+" and orgid="+orgid;
	                sql = "SELECT id,file_real_name,file_hash_name,file_text,file_time,file_size,file_view_power,file_flag," +
	                		"file_down_power,file_categories,download_url,del_url from file_info WHERE file_flag='0' " +
	                		"AND file_categories="+vc+" and orgid="+orgid+" limit "+firstIndex+","+showNumber;
                }
            	else{ //所有分类
                    countSql = "SELECT count(*) FROM file_info where file_flag='0' and orgid="+orgid;
                    sql = "SELECT id,file_real_name,file_hash_name,file_text,file_time,file_size,file_view_power,file_flag," +
                    		"file_down_power,file_categories,download_url,del_url from file_info WHERE file_flag='0' and orgid="+orgid+" limit "+firstIndex+","+showNumber;
            	}
            } else {  //根据会议室名模糊查询
            	if(vc!=1){
	            	key = "'%" + key + "%'";
	                countSql = "SELECT count(*) FROM file_info where file_flag='0' AND file_categories="+vc+" AND (file_real_name like "+key+") and orgid="+orgid;
	                sql = "SELECT id,file_real_name,file_hash_name,file_text,file_time,file_size,file_view_power,file_flag,file_down_power," +
	                		"file_categories,download_url,del_url from file_info WHERE file_flag='0' " +
	                		"AND file_categories="+vc+" AND file_real_name like "+key+" and orgid="+orgid+" limit "+firstIndex+","+showNumber;
                }
            	else{
            		key = "'%" + key + "%'";
                    countSql = "SELECT count(*) FROM file_info where file_flag='0' AND (file_real_name like "+key+") and orgid="+orgid;
                    sql = "SELECT id,file_real_name,file_hash_name,file_text,file_time,file_size,file_view_power,file_flag,file_down_power," +
                    		"file_categories,download_url,del_url from file_info WHERE file_flag='0' AND file_real_name like "+key+" and orgid="+orgid+" limit "+firstIndex+","+showNumber;
            	}
            }
            ps = conn.prepareStatement(countSql);  
            SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("从数据库获取录像列表：参数："+"orgid:"+orgid+"，key:"+key+"，vc:"+vc+"，firstIndex:"+firstIndex+"，showNumber:"+showNumber+",sqlcount:"+countSql+",sql:"+sql);
            ps.executeQuery(setName);
            rs = ps.executeQuery();
            rs.next(); 
            maxNumber = rs.getInt("count(*)");  //总记录数
            pageNumber = (maxNumber + showNumber - 1) / showNumber;  //分页数

            ps = conn.prepareStatement(sql);
            ps.executeQuery(setName);
            rs = ps.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    VideoInfoActionForm vif = new VideoInfoActionForm();
                    vif.setId(rs.getInt("id"));
                    vif.setFile_real_name(rs.getString("file_real_name"));
                    vif.setFile_hash_name(rs.getString("file_hash_name"));
                    vif.setFile_text(rs.getString("file_text"));
                    vif.setFile_time(rs.getString("file_time").substring(0, 19));
                    vif.setFile_size(rs.getString("file_size"));
                    vif.setFile_flag(rs.getInt("file_flag"));
                    vif.setFile_view_power(rs.getInt("file_view_power"));
                    vif.setFile_down_power(rs.getInt("file_down_power"));
                    vif.setFile_categories(rs.getInt("file_categories"));
                    vif.setDownload_url(rs.getString("download_url"));
                    vif.setDel_url(rs.getString("del_url"));
                    vif.setPageNumber(pageNumber);
                    videoList.add(vif);
                }
            }
        } catch (SQLException e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("从数据库获取录像列表出错"+e);
            e.printStackTrace();
        } finally{
        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
        }
        return videoList;
    }	
	
    /**
     *添加录像文件
     **/
    public void addFile(String orgid,VideoInfoActionForm vif) {
        Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
        PreparedStatement ps = null;
        int recordi = 0;
        
        //读取添加文件信息的SQL
        String addFile = "INSERT INTO file_info (file_real_name,file_hash_name,file_text,file_time,file_size,file_view_power," +
        		"file_flag,file_down_power,file_categories,download_url,del_url,orgid) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("数据库中添加录像记录：参数："+"orgid:"+orgid+"，vif:"+vif.toString()+",sql:"+addFile);
            ps = conn.prepareStatement(addFile);//将信息插入flie_info表
            ps.setString(1, vif.getFile_real_name());//realname
            ps.setString(2, vif.getFile_hash_name());//hashname
            ps.setString(3, vif.getFile_text());//描述
            ps.setString(4, vif.getFile_time());//上传时间
            ps.setString(5, vif.getFile_size());//大小
            ps.setInt(6, vif.getFile_view_power());
            ps.setInt(7, vif.getFile_flag());
            ps.setInt(8, vif.getFile_down_power());
            ps.setInt(9, vif.getFile_categories());
            ps.setString(10, vif.getDownload_url());//下载地址
            ps.setString(11, vif.getDel_url());//删除地址            
            ps.setString(12, orgid);//删除地址            
            ps.execute(setName); 
            recordi = ps.executeUpdate();
            ps.close();
            conn.close();

        } catch (SQLException e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("添加录像文件出错"+e);
            e.printStackTrace();
        } finally{
        	ConnMYSQL.closeResources(null, ps, conn, orgid);
        }
    }
    
    /**
     *删除录像
     **/
    public boolean delFile(String orgid,int id) {    
    	boolean b = false;
        int i = 0;
        Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
        String queryVideoDelURL = "SELECT del_url,file_hash_name FROM file_info where id="+id+" and orgid="+orgid;
        String delVideo = "DELETE FROM file_info WHERE id="+id+" and orgid="+orgid;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String delURL = "" ;
        try {
        	ps = conn.prepareStatement(queryVideoDelURL);
            rs = ps.executeQuery();
            if(rs.next()){
            	delURL = rs.getString("del_url") + File.separator + rs.getString("file_hash_name");
            	SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("删除录像：参数："+"orgid:"+orgid+"，id:"+id+",delURL:"+delURL);
            }  
            rs.close();

            VideoInfoActionForm vif = new VideoInfoActionForm();
            try{
            	File file = new File(delURL);
            	if(file.exists()){
             	    file.delete();
             	    }else{
             	    	SeegleLog.getInstance().getLog(this.getClass(), orgid).warn(delURL+"文件不存在");
             	    }
            }catch(Exception e){
            	SeegleLog.getInstance().getLog(this.getClass(), orgid).error(delURL+"文件删除出错"+e);
            	e.printStackTrace();
            }
            
            ps = conn.prepareStatement(delVideo);
            i = ps.executeUpdate();
            if(i==1){
            	b = true;
            }
            ps.close();
            conn.close();
        } catch (SQLException e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("查询删除文件出错"+e);
            e.printStackTrace();
        }finally{
        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
        }
        return b;

    }
    /**
     * 删除录像，根据录像名称删除
     * @author zhangqing
     * @date 2013-5-15 下午05:02:20
     * @param orgid
     * @param fileHashName
     * @return
     */
    public boolean delFile(String orgid,String fileHashName,String deleteUrl) {    
    	boolean b = false;
    	int i = 0;
    	Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
    	String queryVideoDelURL = "SELECT del_url,file_hash_name FROM file_info where file_hash_name='"+fileHashName+"' and orgid="+orgid;
    	String delVideo = "DELETE FROM file_info WHERE file_hash_name='"+fileHashName+"' and orgid="+orgid;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	String delURL = deleteUrl ;
    	try {
    		ps = conn.prepareStatement(queryVideoDelURL);
    		rs = ps.executeQuery();
    		if(rs.next()){
    			delURL = rs.getString("del_url") + File.separator + rs.getString("file_hash_name");
    			SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("删除录像，根据录像名称删除"+delURL);
    		}
    		rs.close();
    		
    		try{
    			File file = new File(delURL);
    			if(file.exists()){
    				file.delete();
    			}else{
    				SeegleLog.getInstance().getLog(this.getClass(), orgid).warn(delURL+"文件不存在");
    			}
    		}catch(Exception e){
    			SeegleLog.getInstance().getLog(this.getClass(), orgid).error(delURL+"文件删除出错"+e);
    			e.printStackTrace();
    		}
    		
    		ps = conn.prepareStatement(delVideo);
    		i = ps.executeUpdate();
    		if(i==1){
    			b = true;
    		}
    		ps.close();
    		conn.close();
    	} catch (SQLException e) {
    		SeegleLog.getInstance().getLog(this.getClass(), orgid).error("查询删除文件出错"+e);
    		e.printStackTrace();
    	} finally{
    		ConnMYSQL.closeResources(rs, ps, conn, orgid);
    	}
    	return b;
    	
    }
 
    /**
     * 如果文件夹不存在则创建文件夹
     **/
    public void CreateFolder(String filepath) {
        File dirFile;
        boolean bFile;
        bFile = false;
        dirFile = new File(filepath);
        bFile = dirFile.exists();

        if (bFile == true) {
        } else {
            bFile = dirFile.mkdirs();
            if (bFile == true) {
                System.out.println("Create folder success!");
            } else {
                System.out.println("Disable to create the folder.");
            }
        }
    }
    
    /**
     * 遍历服务器文件夹所有文件,返回文件信息列表(文件名、大小、上传时间)
     **/
    
    public ArrayList fileList(String filepath, int firstIndex, int showNumber) {    	
    	CreateFolder(filepath);
    	File file = new File(filepath);
        File[] fl = file.listFiles();  //File类型的数组fl用来存储盘里的文件
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DecimalFormat digits = new DecimalFormat("0.00");
        ArrayList fileList = new ArrayList();  //文件信息列表
        int pageNumber = 1;  //获取分页数，默认分1页
        int maxNumber = 0;  //总记录数
        int n = firstIndex+showNumber;
        maxNumber = fl.length; //总记录数
        pageNumber = (maxNumber + showNumber - 1) / showNumber;  //分页数

        if (fl != null) {
            for (int i = firstIndex; i < fl.length&&i < n; i++) {
                if (fl[i].isFile()) {
                	VideoInfoActionForm vif = new VideoInfoActionForm();  //服务器录像文件信息                	
                	vif.setFile_real_name(fl[i].getName());  //录像的文件名
                	vif.setFile_size(digits.format(fl[i].length() / 1024d)+"KB");  //文件大小(KB)
                	vif.setFile_time(formatter.format(fl[i].lastModified()));  //备份生成时间
                	vif.setPageNumber(pageNumber);
                    fileList.add(vif);
                }
            }
        }
        return fileList;
    }
    
}