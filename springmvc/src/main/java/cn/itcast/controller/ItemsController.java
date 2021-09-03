package cn.itcast.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.domain.Items;
import cn.itcast.service.ItemsService;
/**
 * oscathe 缓存，已关闭，不利于调试。
 * 还有问题：上传图片失败。
 * 可以实现，查询，删除。
 * @author fch
 *
 */
@Controller
@RequestMapping("/items")
public class ItemsController {

	@Resource
	private ItemsService itemsService;

	//查询所有商品
	@RequestMapping("list")
	public String list(Model model){

		List<Items> list = itemsService.findAll();
		System.out.println("_____"+list.size()+"-----");
		model.addAttribute("itemsList", list);

		return "redirect:/itemsList.jsp";

	}
	//跳转到修改页面
	@RequestMapping("edit")
	public String edit(Integer id , Model model){

		//根据Id查询商品
		Items items = itemsService.findByID(id);
		//页面回显
		model.addAttribute("item", items);

		return "editItem";
	}

	@RequestMapping("saveOrUpdate")
	public String saveOrUpdate(Items items){

		itemsService.saveOrUpdate(items);

		return "redirect:list";
	}

	//根据Id进行删除
	@RequestMapping("deleteByID")
	public String deleteByID(Integer id)
	{
		itemsService.deleteByID(id);

		return "redirect: list";
	}

	//批量删除
	@RequestMapping("deleteByIds")
	public String deleteByIds(Integer[] id){

		for(Integer ids : id){

			itemsService.deleteByID(ids);
		}


		return "redirect: list";
	}


}
