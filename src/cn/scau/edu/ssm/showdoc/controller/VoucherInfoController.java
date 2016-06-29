package cn.scau.edu.ssm.showdoc.controller;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

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

import cn.scau.edu.ssm.showdoc.po.VoucherVO;
import cn.scau.edu.ssm.showdoc.service.VoucherInfoService;
import cn.scau.edu.ssm.showdoc.validator.ValidGroup1;

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
	
	@RequestMapping(value="/insertVoucherInfo.action",method={RequestMethod.GET,RequestMethod.POST})
	public String insertVoucherInfo(Model model,HttpServletRequest request,@Validated(value={ValidGroup1.class}) VoucherVO voucherVO,BindingResult result,@RequestParam(value="picture",required=true) MultipartFile pic) throws Exception // 
	{
//		System.out.println(request.getAttribute("username"));
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
			return "login/regist";
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
}
