package cn.scau.edu.ssm.showdoc.po;
/**
 * 返回参数的构造基类
 * @author Administrator
 *
 */
public class MyResultSet {
	private boolean result;
	private String message;
	
	public boolean isResult() {
		return result;
	}
	
	public void setResult(boolean result) {
		this.result = result;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
}
