package cn.itcast.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SuccessController {

	@RequestMapping("success")
	public String list(){
		System.out.println("访问s成功");
		return "redirect:success.jsp";
	}

	@RequestMapping("success2")
	public ModelAndView list2(){
		System.out.println("访问s成功");
		ModelAndView  model = new ModelAndView("redirect:/success.jsp");
		return model;
	}

	@RequestMapping("success3")
	public ModelAndView list3(){
		ModelAndView  model = new ModelAndView("redirect:/test/test1");
		return model;
	}
	// 转发
	@RequestMapping("success4")
	public ModelAndView list4(){
		ModelAndView  model = new ModelAndView("forward:/test/test1");
		return model;
	}
}

