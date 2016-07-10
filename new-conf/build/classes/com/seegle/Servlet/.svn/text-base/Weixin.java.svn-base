package com.seegle.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.Writer;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;

public class Weixin extends HttpServlet {

	private static final long serialVersionUID = -3295130527689138021L;
	private final String TOKEN = "seegle.com";
	private final String TEXT = "text";
	private final String RESP_CONTENT = "欢迎使用视高会议系统，回复如下代码执行相应操作：\n1：创建电话会议\n2:创建视频会议";

	public Weixin() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	
	/**
	 * get方法验证连接的合法性
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 String signature = request.getParameter("signature");
		 String timestamp = request.getParameter("timestamp");
		 String nonce = request.getParameter("nonce");
		 String echostr = request.getParameter("echostr");
		 PrintWriter out = response.getWriter();
		 if(!chechUrl(signature,timestamp,nonce,echostr)){
			 echostr = "-1";
		 }
		 out.write(echostr);
	}
	/**
	 * 相应用户的输入
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Scanner scanner = new Scanner(req.getInputStream());
		resp.setContentType("application/xml");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		try {
			// 1、获取用户发送的信息
			StringBuffer sb = new StringBuffer(100);
			while (scanner.hasNextLine()) {
				sb.append(scanner.nextLine());
			}
			
			// 2、解析用户的信息
			JAXBContext jc = JAXBContext.newInstance(WeChatReqBean.class);
			Unmarshaller u = jc.createUnmarshaller();
			WeChatReqBean reqBean = (WeChatReqBean) u.unmarshal(new StringReader(sb.toString()));
			
			// 3、判断是否为文本消息。并对请求内容组织回复内容
			String content = reqBean.getContent();
			String type = reqBean.getMsgType();
			String contentResp = "";
			if(type==null||!type.equals(TEXT)||content == null){
				contentResp  = RESP_CONTENT;
			}else{
				if(content.equals("1")){
					contentResp = "电话会议创建成功，会议ID为：123456";
				}else if(content.equals("2")){
					contentResp = "对不起，视频会议创建功能不可用";
				}else{
					contentResp = RESP_CONTENT;
				}
			}
			
			// 4、创建一个回复消息
			jc = JAXBContext.newInstance(WeChatRespBean.class);
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.setProperty(CharacterEscapeHandler.class.getName(), new CharacterEscapeHandler() {
				@Override
				public void escape(char[] arg0, int arg1, int arg2,
						boolean arg3, Writer arg4) throws IOException {
					// TODO Auto-generated method stub
					arg4.write(arg0, arg1, arg2);
				}
			});
			m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			WeChatRespBean respBean = createRespBean(reqBean, contentResp);
			m.marshal(respBean, out);
			out.flush();
		} catch (JAXBException e) {
			e.printStackTrace();
		} finally {
			if (scanner != null) {
				scanner.close();
				scanner = null;
			}
			if (out != null) {
				out.close();
				out = null;
			}
		}
	}
	
	/**
	 * 创建
	 */
	private WeChatRespBean createRespBean(WeChatReqBean reqBean, String content) {
		WeChatRespBean respBean = new WeChatRespBean();
		respBean.setFromUserName(reqBean.getToUserName());
		respBean.setToUserName(reqBean.getFromUserName());
		respBean.setMsgType(TEXT);
		respBean.setCreateTime(new Date().getTime());
		respBean.setContent(content);
		return respBean;
	}

	public void init() throws ServletException {
		// Put your code here
	}
	/**
	 * 验证url的正确性
	 */
	public boolean chechUrl(String signature,String timestamp,String nonce,String echostr){
		String[] tmpArr={TOKEN,timestamp,nonce};  
		Arrays.sort(tmpArr);  
		String tmpStr=this.ArrayToString(tmpArr);  
		tmpStr=this.SHA1Encode(tmpStr);  
		if(tmpStr.equalsIgnoreCase(signature)){  
		    return true;  
		}else{  
		    return false;  
		}
	}
	//数组转字符串  
    public String ArrayToString(String [] arr){  
        StringBuffer bf = new StringBuffer();  
        for(int i = 0; i < arr.length; i++){  
         bf.append(arr[i]);  
        }  
        return bf.toString();  
    }  
    //sha1加密  
    public String SHA1Encode(String sourceString) {  
        String resultString = null;  
        try {  
           resultString = new String(sourceString);  
           MessageDigest md = MessageDigest.getInstance("SHA-1");  
           resultString = byte2hexString(md.digest(resultString.getBytes()));  
        } catch (Exception ex) {  
        }  
        return resultString;  
    }  
    public final String byte2hexString(byte[] bytes) {  
        StringBuffer buf = new StringBuffer(bytes.length * 2);  
        for (int i = 0; i < bytes.length; i++) {  
            if (((int) bytes[i] & 0xff) < 0x10) {  
                buf.append("0");  
            }  
            buf.append(Long.toString((int) bytes[i] & 0xff, 16));  
        }  
        return buf.toString().toUpperCase();  
    } 
    
    public static void main(String [] at){
    	new Weixin().chechUrl("123",new Date().getTime()+"",Math.random()+"","");
    }

}

class WeChatRespBean{
	public WeChatRespBean(){
		
	}
	private String FromUserName;
	private String ToUserName;
	private String MsgType;
	private Long CreateTime;
	private String Content;
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public Long getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(Long createTime) {
		CreateTime = createTime;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
}
class WeChatReqBean{
	public WeChatReqBean(){
		
	}
	private String FromUserName;
	private String ToUserName;
	private String MsgType;
	private Long CreateTime;
	private String Content;
	private String MsgId;
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public Long getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(Long createTime) {
		CreateTime = createTime;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
}
