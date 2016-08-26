package greendog.weixin.beans.weixin;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "xml")
public class WeChatRespBean{
	public WeChatRespBean(){
		
	}
	@XmlElement(name = "FromUserName")
	private String FromUserName;
	@XmlElement(name = "ToUserName")
	private String ToUserName;
	@XmlElement(name = "MsgType")
	private String MsgType;
	@XmlElement(name = "CreateTime")
	private Long CreateTime;
	@XmlElement(name = "Content")
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