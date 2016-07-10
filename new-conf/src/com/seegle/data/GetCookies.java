package com.seegle.data;

import javax.servlet.http.Cookie;

import com.seegle.util.PropUtil;

/**
 *
 * 读取cookie信息
 */
public class GetCookies {

    public String getCookie(Cookie[] cookies, String cookieName) {  //根据cookie数组和名称获得单个cookie值
        String cookieValue = null;
        //获取当前web项目名,加到cookieName前
        String path = "";
        if(PropUtil.getInstance().getValue("path")!=null){
        	path=PropUtil.getInstance().getValue("path");
        }   
        cookieName = path+"/"+cookieName;
        
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie c = cookies[i];
                if (c.getName().equalsIgnoreCase(cookieName)) {
                    cookieValue = c.getValue();
                }
            }
        }
        return cookieValue;
    }

//    /*
//     * 写cookie
//     */
//    public void setCookie(String name, String value) {
//        Cookie namecookie = new Cookie(value, "SGlanguage");
//    //生命周期
//        namecookie.setMaxAge(60 * 60 * 24 * 365);
//        response.addCookie(namecookie);
//
//    }
}
