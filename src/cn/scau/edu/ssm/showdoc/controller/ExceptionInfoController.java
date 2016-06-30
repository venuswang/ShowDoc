package cn.scau.edu.ssm.showdoc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
/**
 * 公共异常处理类
 * @author Administrator
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/exception")
public class ExceptionInfoController {

	@RequestMapping(value="/operateVoucherHandle.action",method={RequestMethod.GET,RequestMethod.POST})
	public String operateVoucherHandle(Model model,@RequestParam(required=true,defaultValue="错误编号20001:未知错误...") String message) throws Exception
	{
		model.addAttribute("message", message);
		return "forward:/jsp/error.jsp";
	}
}
