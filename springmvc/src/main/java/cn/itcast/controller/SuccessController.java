package cn.itcast.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SuccessController {

	@RequestMapping("success")
	public String list(){
		System.out.println("访问s成功");
		return "success";
	}
	
}

