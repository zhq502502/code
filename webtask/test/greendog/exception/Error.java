package greendog.exception;

public class Error {
	private Integer code;
	private Integer exception;
	private String stack;
	private String errormsg;
	private String errorservlet;
	private String errorurl;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public Integer getException() {
		return exception;
	}
	public void setException(Integer exception) {
		this.exception = exception;
	}
	public String getStack() {
		return stack;
	}
	public void setStack(String stack) {
		this.stack = stack;
	}
	public String getErrormsg() {
		return errormsg;
	}
	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
	public String getErrorservlet() {
		return errorservlet;
	}
	public void setErrorservlet(String errorservlet) {
		this.errorservlet = errorservlet;
	}
	public String getErrorurl() {
		return errorurl;
	}
	public void setErrorurl(String errorurl) {
		this.errorurl = errorurl;
	}
	
}
