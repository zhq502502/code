package greendog.exception;

public class MyException extends Exception{

	/**
	 * @author zhangqing
	 * @date 2014-3-26 上午10:50:52
	 */
	private static final long serialVersionUID = 4750529707369483975L;

	public MyException() {
		//super();
	}

	public MyException(String message, Throwable cause) {
		//super(message, cause);
	}

	public MyException(String message) {
		//super(message);
	}

	public MyException(Throwable cause) {
		//super(cause);
	}
}
