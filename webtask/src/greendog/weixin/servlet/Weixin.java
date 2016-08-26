package greendog.weixin.servlet;

import greendog.weixin.beans.weixin.WeChatReqBean;
import greendog.weixin.beans.weixin.WeChatRespBean;
import greendog.weixin.business.BusService;
import greendog.weixin.util.WeixinPropUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
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

import org.apache.log4j.Logger;

import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;

public class Weixin extends HttpServlet {

	private static final long serialVersionUID = -3295130527689138021L;
	private BusService service = new BusService();
//	private final String TOKEN = "seegle_com";
//	private final String TEXT = "text";
//	private final String RESP_CONTENT = "欢迎访问视高微信机器人，回复如下代码执行相应操作：\n[1]：创建电话会议\n[2]：创建视频会议";
	private Logger log = Logger.getLogger(Weixin.class);
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
		 log.error("公众版连接验证参数：signature:"+signature+",timestamp:"+timestamp+",nonce:"+nonce+",echostr:"+echostr);
		 if(!(signature==null||timestamp==null||nonce==null||echostr==null)){
			 PrintWriter out = response.getWriter();
			 if(!chechUrl(signature,timestamp,nonce,echostr)){
				 echostr = "-1";
			 }
			 System.out.println(echostr);
			 out.write(echostr);
		 }
	}
	/**
	 * 相应用户的输入
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Scanner scanner = new Scanner(req.getInputStream());
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/xml");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		try {
			// 1、获取用户发送的信息
			StringBuffer sb = new StringBuffer();
			while (scanner.hasNextLine()) {
				sb.append(scanner.nextLine());
			}
			
			// 2、解析用户的信息
			JAXBContext jc = JAXBContext.newInstance(WeChatReqBean.class);
			Unmarshaller u = jc.createUnmarshaller();
			log.error("接收到请求\n"+sb.toString());
			WeChatReqBean reqBean = (WeChatReqBean) u.unmarshal(new StringReader(sb.toString()));
			
			// 3、判断是否为文本消息。并对请求内容组织回复内容
			String content = reqBean.getContent();
			String type = reqBean.getMsgType();
			String contentResp = "";
			if(type==null||!type.equals(WeixinPropUtil.getInstance().getValue("weixin.type"))||content == null){
				contentResp  = WeixinPropUtil.getInstance().getValue("weixin.content");
			}else{
				String bus[] = content.split("-");
				log.error("请求内容:"+content);
				if(bus==null){
					contentResp = WeixinPropUtil.getInstance().getValue("weixin.content");
				}else if(bus.length==2){
					contentResp = service.getStringByBus(bus[0], bus[1]);
				}else{
					contentResp = WeixinPropUtil.getInstance().getValue("weixin.content");
				}
			}
			log.error("响应信息:"+contentResp);
			
			// 4、创建一个回复消息
			jc = JAXBContext.newInstance(WeChatRespBean.class);
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.setProperty(CharacterEscapeHandler.class.getName(), new CharacterEscapeHandler() {
				//@Override
				public void escape(char[] arg0, int arg1, int arg2,
						boolean arg3, Writer arg4) throws IOException {
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
	 * @throws UnsupportedEncodingException 
	 */
	private WeChatRespBean createRespBean(WeChatReqBean reqBean, String content) throws UnsupportedEncodingException {
		WeChatRespBean respBean = new WeChatRespBean();
		respBean.setFromUserName(reqBean.getToUserName());
		respBean.setToUserName(reqBean.getFromUserName());
		respBean.setMsgType(WeixinPropUtil.getInstance().getValue("weixin.type"));
		respBean.setCreateTime(new Date().getTime());
		respBean.setContent(content);
		return respBean;
	}

	public void init() throws ServletException {
		// Put your code here
	}
	/**
	 * 验证url的正确性
	 * @throws UnsupportedEncodingException 
	 */
	public boolean chechUrl(String signature,String timestamp,String nonce,String echostr) throws UnsupportedEncodingException{
		String[] tmpArr={WeixinPropUtil.getInstance().getValue("weixin.token"),timestamp,nonce};  
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
    
    public static void main(String [] at) throws UnsupportedEncodingException{
    	System.out.println(WeixinPropUtil.getInstance().getValue("weixin.content"));
    }
}
