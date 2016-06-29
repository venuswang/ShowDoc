package cn.scau.edu.ssm.showdoc.controller;


import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.scau.edu.ssm.showdoc.po.Voucher;
import cn.scau.edu.ssm.showdoc.po.VoucherVO;
import cn.scau.edu.ssm.showdoc.service.VoucherInfoService;
import cn.scau.edu.ssm.showdoc.validator.ValidGroup1;
import cn.scau.edu.ssm.showdoc.validator.ValidGroup2;

/**
 * 用户信息维护类
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/voucher")
public class VoucherInfoController {
	@Autowired
	private VoucherInfoService voucherInfoService;
	
	/**
	 * 用户账号信息注册
	 * @param model
	 * @param request
	 * @param voucherVO
	 * @param result
	 * @param pic
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/insertVoucherInfo.action",method={RequestMethod.GET,RequestMethod.POST})
	public String insertVoucherInfo(Model model,HttpServletRequest request,@Validated(value={ValidGroup1.class}) VoucherVO voucherVO,BindingResult result,@RequestParam(value="picture",required=true) MultipartFile pic) throws Exception // 
	{
		if(result.hasErrors())
		{
			List<ObjectError> errors = result.getAllErrors();
			List<String> errorInfos = new ArrayList<String>();
			for(ObjectError error : errors)
			{
				errorInfos.add(error.getDefaultMessage());
			}
			model.addAttribute("errors", errorInfos);
			model.addAttribute("voucherVO", voucherVO);
			return "login/register";
		}
		if(pic != null && pic.getOriginalFilename() != null && pic.getOriginalFilename().length() > 0) 
		{
			String path = "F:\\picture\\";
			String pic_oriname = pic.getOriginalFilename();
			int index = pic_oriname.lastIndexOf(".");
//			String suffix = "";
			String pic_name = "";
			if(index == -1)
			{
				pic_name = "default.jpg";
			} else{
				pic_name = UUID.randomUUID()+pic_oriname.substring(index);
				File file = new File(path+pic_name);
				if(!file.exists())
				{
					file.createNewFile();
				}
				pic.transferTo(file);
			}
			voucherVO.getVoucherInfo().setPicture("/pic/"+pic_name);
		}
		Integer userid = voucherInfoService.insertVoucherInfo(voucherVO);
		request.getSession().setAttribute("loginStatu", "login");
		request.getSession().setAttribute("username",voucherVO.getVoucher().getUsername());
		request.getSession().setAttribute("userid", userid);
		return "success";
	}
	
	/**
	 * 检验用户的账号是否已经被注册，存在了数据库中
	 * @param request
	 * @param response
	 * @param voucher
	 * @param result
	 * @throws Exception
	 */
	@RequestMapping("/queryByName.action")
	public void queryByName(HttpServletRequest request,HttpServletResponse response,@Validated(value={ValidGroup2.class}) Voucher voucher, BindingResult result) throws Exception
	{
		String message;
		if(result.hasErrors())
		{
//			throw new MyException("错误编号10004:用户账号检验有没有注册过时要求非空且在6-15字符内...");
			message="illegal";
		} else {
			Integer daoResult = voucherInfoService.queryVoucherByName(voucher.getUsername());
			if(daoResult == 0) 
				message = "success";
			else
				message = "fail";
		}
		response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = null;
        out = response.getWriter();
        out.println(message);
	}
}
