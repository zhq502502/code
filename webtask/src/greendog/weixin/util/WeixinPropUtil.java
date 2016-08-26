package greendog.weixin.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import util.OrderedProperties;

/**
 * properties文件操作工具类
 * @author zhangqing
 * @date 2013-5-8 上午10:12:09
 */
public class WeixinPropUtil {
	/**stiac的自己的对象*/
	private static WeixinPropUtil prop;
	/**输入流*/
	private InputStream is;
	/**properties对象*/
    private OrderedProperties info;
    /**
     * 私有构造方法，实现单例模式
     */
    private WeixinPropUtil(){
    	is = this.getClass().getResourceAsStream("/weixinconfig.properties");
    	info = new OrderedProperties();
		try {
			info.load(is);
			is.close();
		} catch (IOException e) {
			System.out.println("读取配置文件错误");
			e.printStackTrace();
		}
	}
    /**
     * 获得自身对象
     * @author zhangqing
     * @date 2013-5-8 上午10:21:05
     * @return
     */
    public static WeixinPropUtil getInstance(){
    	if(prop==null){
    		prop = new WeixinPropUtil();
    	}
    	return prop;
    }
    /**
     * 根据key获得对应的value
     * @author zhangqing
     * @date 2013-5-8 上午10:21:19
     * @param key 
     * @return
     * @throws UnsupportedEncodingException 
     */
    public String getValue(String key){
    	try {
			return new String(info.getProperty(key).getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    /**
     * 根据key获得对应的value，没有value则返回默认值
     * @author zhangqing
     * @date 2013-5-8 上午10:21:44
     * @param key
     * @param defaultValue 默认值
     * @return
     * @throws UnsupportedEncodingException 
     */
    public String getValue(String key,String defaultValue){
		try {
			return new String(info.getProperty(key, defaultValue).getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    /**
     * 获得properties对象
     * @author zhangqing
     * @date 2013-5-8 上午10:22:10
     * @return
     */
    public OrderedProperties getProperties(){
    	return info;
    }
    
    public static void main(String [] arg){
    	String test = WeixinPropUtil.getInstance().getValue("download.iphone");
    	//test = new String(test.getBytes("ISO-8859-1"),"UTF-8");
    	System.out.println(test);
    	
    }

}
