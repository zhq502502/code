package com.packages.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * 邮件发送类
 * @author zhangqing
 * @date 2013-5-6 下午04:00:40
 */
public class EmailUtil {
	/**邮件格式为文本类型*/
	public static final String TYPE_TEXT = "1";
	/**邮件格式为html格式*/
	public static final String TYPE_HTML = "2";
	/**邮件发出地址*/
	private String emailLoginName = "";
	/**发送邮件的密码*/
	private String emailLoginPassword = "";
	/**邮件认证服务器地址*/
	private String emailHost = "";
	/**邮件认证服务器端口*/
	private String emailPort = "";
	/**发件人昵称*/
	private String emailalias = emailLoginName;
	/**email参数*/
	private Properties emailProp;
	/**邮件会话*/
	private Session session;
	/**邮件事物*/
	private Transport trans ;
	
	/**
	 * 私有化无参构造方法
	 */
	/**
	 * 构造方法
	 * @param orgid 企业ID
	 * @throws Exception 
	 */
	public EmailUtil() throws Exception{
		initEmailProp();
		MailAuthenticator auth = new MailAuthenticator(emailLoginName,emailLoginPassword);
        session = Session.getInstance(emailProp, auth);
        //是否开启dubug，开启后能看到邮件发送的细节
        session.setDebug(false);
        try {
			trans = session.getTransport("smtp");
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	    try {
			trans.connect(emailHost, emailLoginName, emailLoginPassword);
		} catch (MessagingException e) {
			//e.printStackTrace();
			throw new Exception(e);
		}
	}
	/**
	 * 初始化邮件配置信息
	 * @author zhangqing
	 * @date 2013-5-14 下午04:49:56
	 * @param orgid 企业ID
	 * @return
	 */
	private void initEmailProp() {
		emailHost = PropUtil.getInstance().getValue("email.host");
		emailLoginName = PropUtil.getInstance().getValue("email.loginname");
		emailLoginPassword = PropUtil.getInstance().getValue("email.loginpwd");
		emailPort = PropUtil.getInstance().getValue("email.port");
		emailalias = PropUtil.getInstance().getValue("email.alias");
		emailProp = System.getProperties();
		emailProp.put("mail.smtp.host",  emailHost);
		emailProp.put("mail.smtp.socketFactory.fallback", false);
		emailProp.put("mail.smtp.port", emailPort);
		emailProp.put("mail.smtp.socketFactory.port", emailPort);
		emailProp.put("mail.smtp.auth", true);
		emailProp.put("mail.smtp.starttls.enable", false);
		System.out.println("服务器地址："+emailHost+",登录账户:"+emailLoginName+",登录密码:"+emailLoginPassword+",服务器端口:"+emailPort+",发件人昵称:"+emailalias);
	}
	/**
	 * 发送邮件
	 * @author zhangqing
	 * @date 2013-5-6 下午04:04:44
	 * @param emails email地址串，格式：111@qq.com,222@qq.com
	 * @param content 邮件内容
	 * @param type 邮件类型1text，2html，发送带附件的邮件时，请务必填2
	 * @param title 邮件标题
	 * @param attachments 附件列表map里面存值，filename,filepath
	 * @return
	 */
	@SuppressWarnings("static-access")
	public boolean send(String emails,String title,String content,String type,List<Map<String,String>> attachments){
		try {
			//配置发送的邮件
			MimeMessage message = new MimeMessage(session);
			String nick=MimeUtility.encodeText(emailalias);
			message.setFrom(new InternetAddress(nick+"<"+emailLoginName+">"));
			message.setRecipients(RecipientType.TO,InternetAddress.parse(emails));
			message.setSubject(title);
			message.setSentDate(new Date());// 邮件发送的时间日期
			//判断发送格式然后填充内容
			if(type!=null&&type.equals(EmailUtil.TYPE_HTML)){
				Multipart mm = new MimeMultipart();  
				//文本内容
				BodyPart mdp1 = new MimeBodyPart(); 
				mdp1.setContent(content, "text/html;charset=UTF-8"); 
				mm.addBodyPart(mdp1);  
				//end 文本内容
				
				//附件内容
				if(attachments!=null){
					for(Map<String,String> map:attachments){
						BodyPart mdp = new MimeBodyPart();   
						DataSource source = new FileDataSource(map.get("filepath"));
						mdp.setDataHandler(new DataHandler(source));
						mdp.setFileName(MimeUtility.encodeText(map.get("filename")));
						mm.addBodyPart(mdp);  
					}
				}
				//附件内容
				
				
				message.setContent(mm); 
			}else{
				message.setText(content);
			}
			trans.send(message);
			System.out.println("\n发送成功:邮件标题："+title+",发件人昵称:"+emailalias+",收件人:"+emails+",邮件内容："+content+",内容格式："+type+",附件："+(attachments==null?"没有附件":"文件个数："+attachments.size())+"\n");
//			System.out.println("----------------邮件发送成功---------------发送时间：" +new Date());
		    } catch (Exception e) {
		    	System.out.println("邮件发送时出错"+e);
		    	e.printStackTrace();
				return false;
		    } finally{
		    	try {
					trans.close();
				} catch (MessagingException e) {
					e.printStackTrace();
				}
		    }
		return true;
	}
	
	/**
	 * 通过线程发送邮件，用户无需等待邮件发送完成
	 * @author zhangqing
	 * @date 2013-5-6 下午04:05:58
	 * @param emails email地址串，格式：111@qq.com,222@qq.com
	 * @param content 邮件内容
	 * @param type 邮件类型1text，2html
	 * @param title 邮件标题
	 * @param orgid 企业ID
	 * @throws Exception 
	 */
	public static void sendByThread(String emails,String title,String content,String type,List<Map<String,String>> attachments) throws Exception{
		new EmailThread(emails,title, content,type,attachments).start();
	}
	
	/**
	 * 验证邮件登录信息
	 * @author zhangqing
	 * @date 2013-5-17 下午05:18:20
	 * @param orgid 企业ID
	 * @param emailhost 认证服务器地址
	 * @param emailloginname 登录名
	 * @param emailloginpwd 登录密码
	 * @param emailport 邮件服务器验证端口
	 * @return
	 */
	public static boolean validateLogin(String orgid,String emailhost,String emailloginname,String emailloginpwd,String emailport){
		Properties emailProp = System.getProperties();
		emailProp.put("mail.smtp.host",  emailhost);
		emailProp.put("mail.smtp.socketFactory.fallback", false);
		emailProp.put("mail.smtp.port", emailport);
		emailProp.put("mail.smtp.socketFactory.port", emailport);
		emailProp.put("mail.smtp.auth", true);
		emailProp.put("mail.smtp.starttls.enable", false);
		
		MailAuthenticator auth = new MailAuthenticator( emailloginname, emailloginpwd);
		Session session = Session.getInstance(emailProp, auth);
        //是否开启dubug，开启后能看到邮件发送的细节
        session.setDebug(false);
        Transport trans = null ;
        try {
        	trans = session.getTransport("smtp");
		} catch (NoSuchProviderException e) {
			System.out.println("邮件验证失败"+e);
			return false;
		}
		try {
			trans.connect(emailhost, emailloginname, emailloginpwd);
			return true;
		} catch (MessagingException e) {
			System.out.println("邮件验证失败"+e);
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String [] str){
		String emails = "zhq_502502@126.com";
		String content = "<a href=\"123\">邮件测试内容</a>";
		String title = "邮件测试标题";
		//EmailUtil.sendByThread(emails,title, content);
		System.out.println("开始发送");
		try {
			List<Map<String,String>> attachments = new ArrayList<>();
			Map<String,String> map = new HashMap<>();
			map.put("filename", "测试.text");
			map.put("filepath","d:/config.ini");
			attachments.add(map);
			Map<String,String> map1 = new HashMap<>();
			map1.put("filename", "测试2.text");
			map1.put("filepath","d:/config.ini");
			attachments.add(map1);
			new EmailUtil().send(emails,title, content,TYPE_HTML,attachments);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("发送完成");
		
	}
}

/**
 * 邮件发送线程
 * @author zhangqing
 * @date 2013-5-6 下午04:13:35
 */
class EmailThread extends Thread{
	/**EmailUtil对象*/
	private EmailUtil emailUtil;
	/**邮箱地址列表*/
	private String emails;
	/**邮件内容*/
	private String content;
	/**邮件标题*/
	private String title;
	/**邮件格式text/html*/
	private String type ;
	/**附件内容*/
	List<Map<String,String>> attachments;
	/**
	 * 构造方法
	* @param emails email地址串，格式：111@qq.com,222@qq.com
	 * @param content 邮件内容
	 * @param type 邮件类型1text，2html，发送带附件的邮件时，请务必填2
	 * @param title 邮件标题
	 * @param attachments 附件列表
	 * @throws Exception 邮件初始化异常
	 */
	public EmailThread(String emails,String title,String content,String type,List<Map<String,String>> attachments) throws Exception{
		emailUtil = new EmailUtil();
		this.emails = emails;
		this.content = content;
		this.title = title;
		this.type = type;
		this.attachments = attachments;
	}
	
	@Override
	public void run(){
		emailUtil.send(emails,title, content,type,attachments);
	}
}

/**
 * 邮件认证类
 * @author zhangqing
 * @date 2013-5-8 上午10:29:11
 */
class MailAuthenticator extends Authenticator{
	/**邮件发出地址*/
	private String emailLoginName;
	/**发送邮件的密码*/
	private String emailLoginPassword ;
    public MailAuthenticator(String emailLoginName,String emailLoginPassword){
    	this.emailLoginName = emailLoginName;
    	this.emailLoginPassword = emailLoginPassword;
    }
    protected PasswordAuthentication getPasswordAuthentication()
    {
        return new PasswordAuthentication(emailLoginName, emailLoginPassword);
    } 
}
