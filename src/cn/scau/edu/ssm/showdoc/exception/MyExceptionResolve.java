package cn.scau.edu.ssm.showdoc.exception;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
/**
 * 定义自己的异常解析器
 * @author Administrator
 *
 */
public class MyExceptionResolve implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,HttpServletResponse response, Object object, Exception exception) {
		MyException myException = null;
		ModelAndView mav = new ModelAndView();
		if(exception instanceof MaxUploadSizeExceededException)
		{
			String errors = "文件应不大于 "+ ((MaxUploadSizeExceededException)exception).getMaxUploadSize()+" byte.";
			//List<String> errors = new ArrayList<String>();
			//errors.add(error);
			request.setAttribute("errors", errors);
			try {
				request.getRequestDispatcher("login/register.jsp").forward(request, response);
			} catch (ServletException e) {
				mav.addObject("message", "错误编号10001：因上传的图片太大而重回注册界面中Servlet出错...");
				mav.setViewName("error");
			} catch (IOException e) {
				mav.addObject("message", "错误编号10002：因上传的图片太大而重回注册界面中IO出错...");
				mav.setViewName("error");
			}
		} else if(exception instanceof MyException) {
			myException = (MyException)exception;
		} else {
			myException = new MyException("错误编号20001:未知错误...");
		}
		String message = myException.getMessage();
		mav.addObject("message", message);
		mav.setViewName("error");
		return mav;
	}

}
