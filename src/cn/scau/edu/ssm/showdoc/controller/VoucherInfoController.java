package cn.scau.edu.ssm.showdoc.controller;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.scau.edu.ssm.showdoc.exception.MyException;
import cn.scau.edu.ssm.showdoc.po.Voucher;
import cn.scau.edu.ssm.showdoc.po.VoucherExtendClass;
import cn.scau.edu.ssm.showdoc.po.VoucherInfoExtendClass;
import cn.scau.edu.ssm.showdoc.po.VoucherVO;
import cn.scau.edu.ssm.showdoc.service.VoucherInfoService;
import cn.scau.edu.ssm.showdoc.validator.ValidGroup1;
import cn.scau.edu.ssm.showdoc.validator.ValidGroup2;
import cn.scau.edu.ssm.showdoc.validator.ValidGroup3;

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
	public String insertVoucherInfo(Model model,HttpServletRequest request,@Validated(value={ValidGroup1.class}) VoucherVO voucherVO,BindingResult result,@RequestParam(value="pictures",required=true) MultipartFile pic,@RequestParam(required=true) String vcode) throws Exception // 
	{
		if(result.hasErrors())
		{
			List<ObjectError> errors = result.getAllErrors();
		//	List<String> errorInfos = new ArrayList<String>();
			StringBuffer errorInfos = new StringBuffer();
			for(ObjectError error : errors)
			{
				errorInfos.append(error.getDefaultMessage()).append(',');
			}
			model.addAttribute("errors", errorInfos.toString());
			model.addAttribute("voucherVO", voucherVO);
			return "login/register";
		}
		/**
		 * 使用后台自己的验证码
		 */
		
		  String code = (String)request.getSession().getAttribute("vcode");
		  System.out.println(vcode);
		  if(code == null || vcode == null || !code.equals(vcode))
		  {  	
		  		model.addAttribute("errors", "验证码出错");
		 		model.addAttribute("voucherVO", voucherVO);
		 	    return "login/register";
		  }
		String pic_name = "";
		if(pic != null && pic.getOriginalFilename() != null && pic.getOriginalFilename().length() > 0) 
		{
			
			String path = "E:\\picture\\";
			String pic_oriname = pic.getOriginalFilename();
			String mimeType = request.getServletContext().getMimeType(pic_oriname);
			if (!mimeType.startsWith("image/")) {
				model.addAttribute("errors", "您上传的不是图片的类型，请选择jpg、png、jpeg等格式的图片...");
				model.addAttribute("voucherVO", voucherVO);
				return "login/register";
			}
			int index = pic_oriname.lastIndexOf(".");
//			String suffix = "";
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
		request.getSession().setAttribute("images", "/pic/"+pic_name);
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
	@RequestMapping(value="/queryByName.action",method={RequestMethod.GET,RequestMethod.POST})
	public void queryByName(HttpServletRequest request,HttpServletResponse response,@Validated(value={ValidGroup2.class}) Voucher voucher, BindingResult result) throws Exception
	{
		String message = "";
		if(result.hasErrors())
		{
			//throw new MyException("错误编号10004:用户账号检验有没有注册过时要求非空且在6-15字符内...");
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
	
	/**
	 * 用户更新个人的密码
	 * @param response
	 * @param voucherExtendClass
	 * @param result
	 * @throws Exception
	 */
	@RequestMapping(value="/updatePassword.action",method={RequestMethod.GET,RequestMethod.POST})
	public void updatePassword(HttpServletResponse response,@Validated(value={ValidGroup3.class}) VoucherExtendClass voucherExtendClass, BindingResult result) throws Exception
	{
		String message = "";
		if(result.hasErrors())
		{
			message="illegal";  //账户或密码的格式不对
		} else {
			boolean updateResult = voucherInfoService.updateById(voucherExtendClass.getId(), voucherExtendClass);
			if(updateResult == false)
				message = "fail";  //账户和原始密码不匹配或者该账户和密码不是自己的
			else
				message = "success";
		}
		response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = null;
        out = response.getWriter();
        out.println(message);
	}
	
	/**
	 * 修改用户的基本信息
	 */
	@RequestMapping(value="/updateVoucherInfo.action",method={RequestMethod.POST,RequestMethod.GET})
	public void updateVoucherInfo(HttpServletRequest request,HttpServletResponse response,@Validated(value={ValidGroup1.class}) VoucherInfoExtendClass voucherInfoExtendClass,BindingResult result,@RequestParam(value="pictures",required=true) MultipartFile pic) throws Exception
	{
		String message = "";
		boolean flag = false;
		String pic_name = "";
		if(result.hasErrors())
		{
			message="illegal,请输入正确的邮箱";  //账户或密码的格式不对
		} else {
			if(pic != null && pic.getOriginalFilename() != null && pic.getOriginalFilename().length() > 0) 
			{
				
				String path = "E:\\picture\\";
				String pic_oriname = pic.getOriginalFilename();
				String mimeType = request.getServletContext().getMimeType(pic_oriname);
				if (!mimeType.startsWith("image/")) {
					message = "illegal,您上传的不是图片的类型，请选择jpg、png、jpeg等格式的图片...";
					request.setAttribute("messages", message);
					request.getRequestDispatcher("/jsp/project/userproject.jsp").forward(request, response);
				}
				int index = pic_oriname.lastIndexOf(".");
//				String suffix = "";
				
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
					flag = true;
				}
				voucherInfoExtendClass.setPicture("/pic/"+pic_name);
			}
			Integer voucherId = (Integer)request.getSession().getAttribute("userid");
			boolean updateResult = voucherInfoService.updateVoucherInfoById(voucherId, voucherInfoExtendClass);
			if(updateResult == false)
				message = "fail";
			else 
				message = "success";
		}
		request.setAttribute("messages", message);
		if(flag == true && message.equals("success"))
			request.getSession().setAttribute("images","/pic/"+pic_name );
		request.getRequestDispatcher("/jsp/project/userproject.jsp").forward(request, response);
		/*response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = null;
        out = response.getWriter();
        out.println(message);*/
	}
	
	/**
	 * 获取验证码
	 */
	@RequestMapping(value="/getCaptchar.action",method={RequestMethod.GET,RequestMethod.POST})
	public void getCaptchar(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		response.setContentType("image/jpeg");    
        response.setHeader("Pragma", "No-cache");    
        response.setHeader("Cache-Control", "no-cache");    
        response.setDateHeader("Expires", 0);    
        HttpSession session = request.getSession();    
        // 在内存中创建图象    
        int width = 75, height = 25;    
        BufferedImage image = new BufferedImage(width, height,    
                BufferedImage.TYPE_INT_RGB);    
        // 获取图形上下文    
        Graphics g = image.getGraphics();    
        // 生成随机类    
        Random random = new Random();    
        // 设定背景色    
        g.setColor(getRandColor(200, 250));    
        g.fillRect(0, 0, width, height);    
        // 设定字体    
        g.setFont(new Font("Times New Roman", Font.PLAIN, 24));    
        // 画边框    
        g.setColor(getRandColor(160, 200));    
        g.drawRect(0, 0, width - 1, height - 1);    
        // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到    
        g.setColor(getRandColor(160, 200));    
        for (int i = 0; i < 155; i++) {    
            int x = random.nextInt(width);    
            int y = random.nextInt(height);    
            int xl = random.nextInt(12);    
            int yl = random.nextInt(12);    
            g.drawLine(x, y, x + xl, y + yl);    
        }    
        // 取随机产生的认证码(4位数字)    
        String sRand = "";    
        for (int i = 0; i < 4; i++) {    
            String rand = String.valueOf(random.nextInt(10));    
            sRand += rand;    
            // 将认证码显示到图象中    
            g.setColor(new Color(20 + random.nextInt(110), 20 + random    
                    .nextInt(110), 20 + random.nextInt(110)));    
            // 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成    
            g.drawString(rand, 13 * i + 14, 20);    
        }    
    
        // 将认证码存入SESSION    
        session.setAttribute("vcode", sRand);    
    
        // 图象生效    
        g.dispose();    
        // 输出图象到页面    
        ImageIO.write(image, "JPEG", response.getOutputStream()); 
	}
	
	/**
	 * 获取验证码的内容(具有实时性)
	 */
	@RequestMapping(value="/getVcode.action",method={RequestMethod.GET,RequestMethod.POST})
	public void getVcode(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String code = (String)request.getSession().getAttribute("vcode");
		if(code == null || "".equals(code.trim()))
			code = "3490"; 
		response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = null;
        out = response.getWriter();
        out.println(code);  //前端接收时记得有\n
	}
	
	/**
	 * 通过ID来查询用户的个人信息
	 */
	@RequestMapping(value="/queryVoucherById.action",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody VoucherVO queryVoucherById(@RequestParam(required=true) Integer id) throws Exception
	{
		if(id == null)
			throw new MyException("错误编号10005:用户信息ID为空...");
		VoucherVO voucherVO = voucherInfoService.queryVoucherById(id);
	/*	ModelAndView mav = new ModelAndView();
		mav.addObject("voucherVO", voucherVO);
		mav.setViewName("showVoucher");*/
		return voucherVO;
	}
	
	/**
	 * 检验用户登录
	 */
	@RequestMapping(value="/checkVoucher.action",method={RequestMethod.GET,RequestMethod.POST})
	public void checkVoucher(HttpServletRequest request,HttpServletResponse response,@Validated(value={ValidGroup1.class}) VoucherExtendClass voucherExtendClass, BindingResult result,@RequestParam(required=true) String vcode) throws Exception
	{
		String message = "";
		if(result.hasErrors())
		{
			message="illegal";  //账户或密码的格式不对
		} else {
			 String code = (String)request.getSession().getAttribute("vcode");
			 System.out.println(vcode);
			  if(code == null || vcode == null || !code.equals(vcode))
			  {
			 	    message = "fail,验证码出错";
			  } else {
					Integer checkResult = voucherInfoService.selectVoucherByNameAndPass(voucherExtendClass);
					if(checkResult == null)
						message = "fail";
					else {
						HttpSession session = request.getSession();
						session.setAttribute("loginStatu", "login");
						session.setAttribute("username",voucherExtendClass.getUsername());
						session.setAttribute("userid", checkResult);
						String images = voucherInfoService.queryPictureById(checkResult);
						if(images == null)
							images = "/pic/default.jpg";
						session.setAttribute("images", images);
						message = "success,project/showProject.action";  //userproject.jsp
					}
			  }
		}
		response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = null;
        out = response.getWriter();
        out.println(message);
	}
	
	/**
	 * 退出
	 */
	@RequestMapping(value="/exitVoucher.action",method={RequestMethod.GET,RequestMethod.POST})
	public String exitVoucher(HttpServletRequest request) throws Exception 
	{
		request.getSession().invalidate();
		return "forward:/jsp/login/exit.jsp";
	}
	
	/**
	 * 获取验证码的颜色
	 */
	Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色    
        Random random = new Random();    
        if (fc > 255)    
            fc = 255;    
        if (bc > 255)    
            bc = 255;    
        int r = fc + random.nextInt(bc - fc);    
        int g = fc + random.nextInt(bc - fc);    
        int b = fc + random.nextInt(bc - fc);    
        return new Color(r, g, b);    
    }    
}
