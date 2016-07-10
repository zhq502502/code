package com.seegle.data;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.seegle.data.GetConfigFile;
import com.seegle.data.HttpClient;
import com.seegle.data.CommonFunction;
import com.seegle.form.PayHistoryForm;
import com.seegle.lang.SGDateTime;
import com.seegle.util.LanguageUtil;

import seegle.mgr.util.AC_conf_user_log;

public class Export2Excel {
	private static CommonFunction cf = new CommonFunction();
	private GetConfigFile gcf = new GetConfigFile();//读取配置
	private String url = gcf.getConfig("apiUrl");  //API服务器的地址 ，例如："http://www.seegle.cn"
	private String orgid;
	private Export2Excel(){
		
	}
	public Export2Excel(String orgid){
		this.orgid = orgid;
	}
	public void createXLS(String token, OutputStream out, String btime,
			String etime, int t, String c, int id,String language) {
		LanguageUtil lu = new LanguageUtil();
		if (t == 1) {
			if ("count".equals(c)) {
				try {
					HSSFWorkbook wb = new HSSFWorkbook();
					HSSFSheet sheet = wb.createSheet();
					HSSFRow row = sheet.createRow(0);
					HSSFCell cell;
					int nColumn = 6;
					for (int i = 1; i <= nColumn; i++) {
						cell = row.createCell(i - 1);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						if (i == 1)
							cell.setCellValue(lu.getLanguage(language,"log.conf.confroomid","会议室ID"));
						if (i == 2)
							cell.setCellValue(lu.getLanguage(language,"log.conf.confroomname","会议室名称"));
						if (i == 3)
							cell.setCellValue(lu.getLanguage(language,"log.conf.usernum","用户数量"));
						if (i == 4)
							cell.setCellValue(lu.getLanguage(language,"log.conf.totaltimes","使用总次数"));
						if (i == 5)
							cell.setCellValue(lu.getLanguage(language,"log.conf.totaltime","使用总时长"));
						if (i == 6)
							cell.setCellValue(lu.getLanguage(language,"log.conf.averagetime","平均时长"));
					}
					int iRow = 1;

					HttpClient hc = new HttpClient(url,orgid);
					List<NameValuePair> param = new ArrayList<NameValuePair>();
					param.add(new BasicNameValuePair("accessKey", token.toString())); 
					param.add(new BasicNameValuePair("btime", btime)); 
					param.add(new BasicNameValuePair("etime", etime)); 
					JSONArray jsonarr = hc.getJArray("logconf", param);	
					List<AC_conf_user_log> rs = new ArrayList<AC_conf_user_log>();

					if(jsonarr!=null){
						if(jsonarr.size()>0){				
							for (int i=0; i<jsonarr.size(); ++i) {
								 AC_conf_user_log clog = new AC_conf_user_log();
								 JSONObject json = (JSONObject)jsonarr.get(i);
								 int confinfoid = Integer.parseInt(json.get("confid").toString());
								 int useronline = Integer.parseInt(json.get("confusernum").toString());
								 int grouponline = Integer.parseInt(json.get("usenum").toString());
								 clog.Setconfinfoid(confinfoid);		 
								 clog.Setconfname(json.get("confname").toString());
								 clog.Setuseronlinenumber(useronline);
								 clog.Setgrouponlinenumber(grouponline);
								 clog.Setusermac(timeFormat(Integer.parseInt(json.get("usetotaltimes").toString()),lu.getLanguage(language,"log.user.hour","小时"),lu.getLanguage(language,"log.user.minute","分钟"),lu.getLanguage(language,"log.user.second","秒")));
								 clog.Setusername(timeFormat(Integer.parseInt(json.get("useavgtimes").toString()),lu.getLanguage(language,"log.user.hour","小时"),lu.getLanguage(language,"log.user.minute","分钟"),lu.getLanguage(language,"log.user.second","秒")));
								 rs.add(clog);
							}				
						}
						}

					if (rs.size() > 0) {
						for (int i = 0; i < rs.size(); i++) {
							AC_conf_user_log cl = new AC_conf_user_log();
							
							cl = (AC_conf_user_log) rs.get(i);
							row = sheet.createRow(iRow);
							cell = row.createCell(0);
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							cell.setCellValue(cl.Getconfinfoid());
							cell = row.createCell(1);
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							cell.setCellValue(cl.Getconfname());
							cell = row.createCell(2);
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							cell.setCellValue(cl.Getuseronlinenumber());
							cell = row.createCell(3);
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							cell.setCellValue(cl.Getgrouponlinenumber());
							cell = row.createCell(4);
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							cell.setCellValue(cl.Getusermac());
							cell = row.createCell(5);
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							cell.setCellValue(cl.Getusername());
							iRow++;
						}
						out.flush();
						wb.write(out);
						out.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if ("detail".equals(c)) {
				try {
					HSSFWorkbook wb = new HSSFWorkbook();
					HSSFSheet sheet = wb.createSheet();
					HSSFRow row = sheet.createRow(0);
					HSSFCell cell;
					int nColumn = 5;
					for (int i = 1; i <= nColumn; i++) {
						cell = row.createCell(i - 1);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						if (i == 1)
							cell.setCellValue(lu.getLanguage(language,"login.html.userid","用户账号"));
						if (i == 2)
							cell.setCellValue(lu.getLanguage(language,"log.conf.detail.username","用户名称"));
						if (i == 3)
							cell.setCellValue(lu.getLanguage(language,"log.conf.detail.logintime","进入会议室时间"));
						if (i == 4)
							cell.setCellValue(lu.getLanguage(language,"log.conf.detail.logouttime","退出会议室时间"));
						if (i == 5)
							cell.setCellValue(lu.getLanguage(language,"log.conf.detail.onlinetime","在线时长"));
					}
					int iRow = 1;
					/*使用API调用*/
					HttpClient hc = new HttpClient(url,orgid);
					List<NameValuePair> param = new ArrayList<NameValuePair>();
					param.add(new BasicNameValuePair("accessKey", token.toString())); 
					param.add(new BasicNameValuePair("id",id+"")); 
					param.add(new BasicNameValuePair("btime", btime)); 
					param.add(new BasicNameValuePair("etime", etime)); 
					JSONArray jsonarr = hc.getJArray("logconfsingle", param);	
					List<AC_conf_user_log> rs = new ArrayList<AC_conf_user_log>();
					if(jsonarr!=null){
						if(jsonarr.size()>0){				
							for (int i=0; i<jsonarr.size(); ++i) {
								 AC_conf_user_log clog = new AC_conf_user_log();
								 JSONObject json = (JSONObject)jsonarr.get(i);
								 SGDateTime logintime = new SGDateTime(json.get("intime").toString());
								 SGDateTime logouttime = new SGDateTime(json.get("outtime").toString());					 
								 int userid = Integer.parseInt(json.get("userid").toString());
								 clog.Setconfinfoid(id);
								 clog.Setusername(json.get("username").toString());
								 clog.Setlogintime(logintime);
								 clog.Setlogouttime(logouttime);					 
								 clog.Setuserid(userid);		 
								 rs.add(clog);
							}				
						}
						}

					if (rs.size() > 0) {
						for (int i = 0; i < rs.size(); i++) {
							AC_conf_user_log cl = new AC_conf_user_log();
							cl = (AC_conf_user_log) rs.get(i);
							row = sheet.createRow(iRow);
							cell = row.createCell(0);
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							cell.setCellValue(cl.Getuserid());
							cell = row.createCell(1);
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							cell.setCellValue(cl.Getusername());
							cell = row.createCell(2);
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							cell.setCellValue(cl.Getlogintime().toString());
							cell = row.createCell(3);
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							cell.setCellValue(cl.Getlogouttime().toString());
							cell = row.createCell(4);
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							cell.setCellValue(timeFormat(cf.dateToSecondsDiff(cl.Getlogintime(), cl.Getlogouttime()),lu.getLanguage(language,"log.user.hour","小时"),lu.getLanguage(language,"log.user.minute","分钟"),lu.getLanguage(language,"log.user.second","秒")));
							iRow++;
						}
						out.flush();
						wb.write(out);
						out.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				
			}
		} else if (t == 2) {
			if("count".equals(c)){
			try {
				HSSFWorkbook wb = new HSSFWorkbook();
				HSSFSheet sheet = wb.createSheet();
				HSSFRow row = sheet.createRow(0);
				HSSFCell cell;
				int nColumn = 6;
				for (int i = 1; i <= nColumn; i++) {
					cell = row.createCell(i - 1);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					if (i == 1)
						cell.setCellValue(lu.getLanguage(language,"login.html.userid","用户账号"));
					if (i == 2)
						cell.setCellValue(lu.getLanguage(language,"log.conf.detail.username","用户名称"));
					if (i == 3)
						cell.setCellValue(lu.getLanguage(language,"log.user.attendtotaltimes","参会总次数"));
					if (i == 4)
						cell.setCellValue(lu.getLanguage(language,"log.user.attendtotaltime","参会总时长"));
					if (i == 5)
						cell.setCellValue(lu.getLanguage(language,"log.user.averageonlinetime","平均参会时长"));
					if (i == 6)
						cell.setCellValue(lu.getLanguage(language,"log.user.lastlogintime","最后登录时间"));
				}
				int iRow = 1;
				/*使用API调用*/
				HttpClient hc = new HttpClient(url,orgid);
				List<NameValuePair> param = new ArrayList<NameValuePair>();
				param.add(new BasicNameValuePair("accessKey", token.toString())); 
				param.add(new BasicNameValuePair("btime", btime)); 
				param.add(new BasicNameValuePair("etime", etime)); 
				JSONArray jsonarr = hc.getJArray("loguser", param);	
				List<AC_conf_user_log> rs = new ArrayList<AC_conf_user_log>();

				if(jsonarr!=null){
					if(jsonarr.size()>0){				
						for (int i=0; i<jsonarr.size(); ++i) {
							 AC_conf_user_log clog = new AC_conf_user_log();
							 JSONObject json = (JSONObject)jsonarr.get(i);
							 int userid = Integer.parseInt(json.get("userid").toString());
							 int userconfnum = Integer.parseInt(json.get("usenum").toString());
							 SGDateTime logintime = new SGDateTime(json.get("lastlogintime").toString());
							 clog.Setuserid(userid);		 
							 clog.Setusername(json.get("username").toString());
							 clog.Setmaxconfuser(userconfnum);
							 clog.Setusermac(timeFormat(Integer.parseInt(json.get("usetotaltimes").toString()),lu.getLanguage(language,"log.user.hour","小时"),lu.getLanguage(language,"log.user.minute","分钟"),lu.getLanguage(language,"log.user.second","秒")));
							 clog.Setconfname(timeFormat(Integer.parseInt(json.get("useavgtimes").toString()),lu.getLanguage(language,"log.user.hour","小时"),lu.getLanguage(language,"log.user.minute","分钟"),lu.getLanguage(language,"log.user.second","秒")));
							 clog.Setlogintime(logintime);
							 rs.add(clog);
						}				
					}
					}
				if (rs.size() > 0) {
					for (int i = 0; i < rs.size(); i++) {
						AC_conf_user_log cl = new AC_conf_user_log();
						
						cl = (AC_conf_user_log) rs.get(i);
						row = sheet.createRow(iRow);
						cell = row.createCell(0);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(cl.Getuserid());
						cell = row.createCell(1);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(cl.Getusername());
						cell = row.createCell(2);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(cl.Getmaxconfuser());
						cell = row.createCell(3);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(cl.Getusermac());
						cell = row.createCell(4);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(cl.Getconfname());
						cell = row.createCell(5);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(cl.Getlogintime().toString());
						iRow++;
					}
					out.flush();
					wb.write(out);
					out.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
			else if("detail".equals(c)){
			try {
				HSSFWorkbook wb = new HSSFWorkbook();
				HSSFSheet sheet = wb.createSheet();
				HSSFRow row = sheet.createRow(0);
				HSSFCell cell;
				int nColumn = 5;
				for (int i = 1; i <= nColumn; i++) {
					cell = row.createCell(i - 1);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					if (i == 1)
						cell.setCellValue(lu.getLanguage(language,"log.conf.confroomid","会议室ID"));
					if (i == 2)
						cell.setCellValue(lu.getLanguage(language,"log.conf.confroomname","会议室名称"));
					if (i == 3)
						cell.setCellValue(lu.getLanguage(language,"log.conf.detail.logintime","进入会议室时间"));
					if (i == 4)
						cell.setCellValue(lu.getLanguage(language,"log.conf.detail.logouttime","退出会议室时间"));
					if (i == 5)
						cell.setCellValue(lu.getLanguage(language,"log.conf.detail.onlinetime","在线时长"));
				}
				int iRow = 1;
				/*使用API调用*/
				HttpClient hc = new HttpClient(url,orgid);
				List<NameValuePair> param = new ArrayList<NameValuePair>();
				param.add(new BasicNameValuePair("accessKey", token.toString())); 
				param.add(new BasicNameValuePair("id", id+"")); 
				param.add(new BasicNameValuePair("btime", btime)); 
				param.add(new BasicNameValuePair("etime", etime)); 
				JSONArray jsonarr = hc.getJArray("logusersingle", param);	
				List<AC_conf_user_log> rs = new ArrayList<AC_conf_user_log>();
				if(jsonarr!=null){
					if(jsonarr.size()>0){				
						for (int i=0; i<jsonarr.size(); ++i) {
							 AC_conf_user_log clog = new AC_conf_user_log();
							 JSONObject json = (JSONObject)jsonarr.get(i);
							 int confinfoid = Integer.parseInt(json.get("confid").toString());
							 SGDateTime logintime = new SGDateTime(json.get("intime").toString());
							 SGDateTime logouttime = new SGDateTime(json.get("outtime").toString());
							 clog.Setconfinfoid(confinfoid);
							 clog.Setconfname(json.get("confname").toString());
							 clog.Setlogintime(logintime);
							 clog.Setlogouttime(logouttime);					 
							 clog.Setuserid(id);		 
							 rs.add(clog);
						}				
					}
					}

				if (rs.size() > 0) {
					for (int i = 0; i < rs.size(); i++) {
						AC_conf_user_log cl = new AC_conf_user_log();
						
						cl = (AC_conf_user_log) rs.get(i);
						row = sheet.createRow(iRow);
						cell = row.createCell(0);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(cl.Getconfinfoid());
						cell = row.createCell(1);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(cl.Getconfname());
						cell = row.createCell(2);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(cl.Getlogintime().toString());
						cell = row.createCell(3);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(cl.Getlogouttime().toString());
						cell = row.createCell(4);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(timeFormat(cf.dateToSecondsDiff(cl.Getlogintime(), cl.Getlogouttime()),lu.getLanguage(language,"log.user.hour","小时"),lu.getLanguage(language,"log.user.minute","分钟"),lu.getLanguage(language,"log.user.second","秒")));
						iRow++;
					}
					out.flush();
					// String filename = "workbook.xls";
					// FileOutputStream out = new
					// FileOutputStream(filename);
					wb.write(out);
					out.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			} else {
				
			}
			}else if (t == 3) {
				if ("0".equals(c)) {
				try {
					HSSFWorkbook wb = new HSSFWorkbook();
					HSSFSheet sheet = wb.createSheet();
					HSSFRow row = sheet.createRow(0);
					HSSFCell cell;
					int nColumn = 10;
					for (int i = 1; i <= nColumn; i++) {
						cell = row.createCell(i - 1);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						if (i == 1)
							cell.setCellValue(lu.getLanguage(language,"phonelog.orgid","企业ID"));
						if (i == 2)
							cell.setCellValue(lu.getLanguage(language,"phonecharge.paytime","充值时间"));
						if (i == 3)
							cell.setCellValue(lu.getLanguage(language,"phonecharge.paymoney","充值金额")+"("+lu.getLanguage(language,"phonelog.yuan","元")+")");
						if (i == 4)
							cell.setCellValue(lu.getLanguage(language,"phonecharge.lessenmoney","优惠金额")+"("+lu.getLanguage(language,"phonelog.yuan","元")+")");
						if (i == 5)
							cell.setCellValue(lu.getLanguage(language,"phonecharge.beforemoney","充值前金额")+"("+lu.getLanguage(language,"phonelog.yuan","元")+")");
						if (i == 6)
							cell.setCellValue(lu.getLanguage(language,"phonecharge.aftermoney","充值后金额")+"("+lu.getLanguage(language,"phonelog.yuan","元")+")");
						if (i == 7)
							cell.setCellValue(lu.getLanguage(language,"phonecharge.price","单价")+"("+lu.getLanguage(language,"phonecharge.yuanfen","元/分钟")+")");
						if (i == 8)
							cell.setCellValue(lu.getLanguage(language,"phonecharge.excessprice","超额计费")+"("+lu.getLanguage(language,"phonecharge.yuanfen","元/分钟")+")");
						if (i == 9)
							cell.setCellValue(lu.getLanguage(language,"phonecharge.operateuser","充值人员"));
						if (i == 10)
							cell.setCellValue(lu.getLanguage(language,"phonecharge.paymark","充值说明"));
					}
					int iRow = 1;
					
					PhoneChargeOperation pco = new PhoneChargeOperation(orgid.toString());
					int maxcount = pco.getPayHistoryCount(token, id+"", btime, etime,c);
					ArrayList phList = new ArrayList();	
			    	phList = pco.getPayHistory(token.toString(), id+"", btime, etime, 0, maxcount,c);
			    	if(phList.size()>0){
			    		for (int i=0; i<phList.size();i++) {
			    			PayHistoryForm phf = new PayHistoryForm();
			    			phf = (PayHistoryForm) phList.get(i);
							row = sheet.createRow(iRow);
							cell = row.createCell(0);
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							cell.setCellValue(phf.getOrgid());
							cell = row.createCell(1);
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							cell.setCellValue(phf.getOperatetime());
							cell = row.createCell(2);
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							cell.setCellValue(phf.getRealmoney());
							cell = row.createCell(3);
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							cell.setCellValue(phf.getLessenmoney());
							cell = row.createCell(4);
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							cell.setCellValue(phf.getBeforpay());
							cell = row.createCell(5);
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							cell.setCellValue(phf.getAfterpay());
							cell = row.createCell(6);
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							cell.setCellValue(phf.getPrice());
							cell = row.createCell(7);
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							cell.setCellValue(phf.getExcessprice());
							cell = row.createCell(8);
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							cell.setCellValue(phf.getOperateuser());
							cell = row.createCell(9);
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							cell.setCellValue(phf.getParam());
							iRow++;
						}
			    		out.flush();
						wb.write(out);
						out.close();
			    	}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if ("1".equals(c)) {
				try {
					HSSFWorkbook wb = new HSSFWorkbook();
					HSSFSheet sheet = wb.createSheet();
					HSSFRow row = sheet.createRow(0);
					HSSFCell cell;
					int nColumn = 8;
					for (int i = 1; i <= nColumn; i++) {
						cell = row.createCell(i - 1);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						if (i == 1)
							cell.setCellValue(lu.getLanguage(language,"phonelog.orgid","企业ID"));
						if (i == 2)
							cell.setCellValue(lu.getLanguage(language,"phonecharge.chargepaytime","扣费时间"));
						if (i == 3)
							cell.setCellValue(lu.getLanguage(language,"phonecharge.chargepaymoney","扣费金额")+"("+lu.getLanguage(language,"phonelog.yuan","元")+")");
						if (i == 4)
							cell.setCellValue(lu.getLanguage(language,"phonecharge.chargebeforemoney","扣费前金额")+"("+lu.getLanguage(language,"phonelog.yuan","元")+")");
						if (i == 5)
							cell.setCellValue(lu.getLanguage(language,"phonecharge.chargeaftermoney","扣费后金额")+"("+lu.getLanguage(language,"phonelog.yuan","元")+")");
						if (i == 6)
							cell.setCellValue(lu.getLanguage(language,"phonecharge.chargeprice","扣费单价")+"("+lu.getLanguage(language,"phonecharge.yuanfen","元/分钟")+")");
						if (i == 7)
							cell.setCellValue(lu.getLanguage(language,"phonecharge.chargeoperateuser","扣费人员"));
						if (i == 8)
							cell.setCellValue(lu.getLanguage(language,"phonecharge.chargeremark","扣费说明"));
					}
					int iRow = 1;
					
					PhoneChargeOperation pco = new PhoneChargeOperation(orgid.toString());
					int maxcount = pco.getPayHistoryCount(token, id+"", btime, etime,c);
					ArrayList phList = new ArrayList();	
			    	phList = pco.getPayHistory(token.toString(), id+"", btime, etime, 0, maxcount,c);
			    	if(phList.size()>0){
			    		for (int i=0; i<phList.size();i++) {
			    			PayHistoryForm phf = new PayHistoryForm();
			    			phf = (PayHistoryForm) phList.get(i);
							row = sheet.createRow(iRow);
							cell = row.createCell(0);
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							cell.setCellValue(phf.getOrgid());
							cell = row.createCell(1);
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							cell.setCellValue(phf.getOperatetime());
							cell = row.createCell(2);
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							cell.setCellValue(phf.getRealmoney());
							cell = row.createCell(3);
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							cell.setCellValue(phf.getBeforpay());
							cell = row.createCell(4);
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							cell.setCellValue(phf.getAfterpay());
							cell = row.createCell(5);
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							cell.setCellValue(phf.getPrice());
							cell = row.createCell(6);
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							cell.setCellValue(phf.getOperateuser());
							cell = row.createCell(7);
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							cell.setCellValue(phf.getParam().replace("<countdata>", lu.getLanguage(language,"phonecharge.chargecountdata","扣费数据条数：")).replace("<chargingtime>", lu.getLanguage(language,"phonecharge.chargecounttime","计费时长：")).replace("<begintime>", lu.getLanguage(language,"phonecharge.chargebegintime","扣费时间段开始：")).replace("<endtime>", lu.getLanguage(language,"phonecharge.chargeendtime","扣费时间段结束：")));
							iRow++;
						}
			    		out.flush();
						wb.write(out);
						out.close();
			    	}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				
			}
		}
	}
    /**
     * 时间长度格式化     
     **/
    public String timeFormat(int second,String h,String m,String s){
    	String t = "";
    	int hour = second/3600;
    	if(hour>0){
    		t+=hour + " " + h + " ";
    	}
    	int min = (second/60)%60;
    	if(hour>0 || min>0){
    		t+=min + m + " ";
    	}
    	second = second%60;
    	t+=second + s + " ";
    	return t;
    }
}
