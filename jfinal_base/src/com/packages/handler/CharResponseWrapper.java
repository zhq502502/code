package com.packages.handler;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class CharResponseWrapper extends HttpServletResponseWrapper {

	 private CharArrayWriter charArrayWriter=new CharArrayWriter();//字符数组Writer  
     
	    public CharResponseWrapper(HttpServletResponse response) {  
	        super(response);  
	    }  
	      
	    @Override  
	    //覆盖父类方法,如果response输出的内容是字符类内容，则会调用getWriter()方法  
	    //如果是二进制内容，如图片数据，则会调用getOutputStream()方法。  
	    public PrintWriter getWriter() throws IOException{  
	        return new PrintWriter(charArrayWriter);//返回字符数组writer，缓存内容  
	    }  
	  
	    public CharArrayWriter getCharArrayWriter() {  
	        return charArrayWriter;  
	    }  
	    
	    public String getOutput() {
	        return charArrayWriter.toString();
	    }

}
