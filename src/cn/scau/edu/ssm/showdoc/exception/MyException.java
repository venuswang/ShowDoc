package cn.scau.edu.ssm.showdoc.exception;
/**
 * 创建属于自己的异常类MyException
 * @author Administrator
 *
 */
public class MyException extends Exception {
	//定义自己所要展示的异常信息，格式为：   错误编号：错误描述
	private String message;
	
	public MyException(String message)
	{
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
