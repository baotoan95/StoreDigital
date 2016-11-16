package com.baotoan.spring.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baotoan.spring.dao.MenuCateDAO;
import com.baotoan.spring.entities.MenuCate;
import com.baotoan.spring.utils.UploadManager;

@Controller
@RequestMapping("/mngCates")
public class AdminCateHandler {
	@Autowired
	private MenuCateDAO menuDAO;
	
	@RequestMapping(value="/show", method = RequestMethod.GET)
	public String catePage(HttpSession session, ModelMap model) {
		session.setAttribute("adminCurrentPage", "mngCates");
		
		List<MenuCate> listMenuCate = menuDAO.getAll();
		model.addAttribute("cates", listMenuCate);
		
		return "mg_cates";
	}
	
	@RequestMapping(value="/view/{id}/", method = RequestMethod.GET)
	public String editCate(@PathVariable int id, ModelMap model) {
		MenuCate menuCate = menuDAO.getMenuCateById(id);
		model.addAttribute("title", "Menu ID: " + menuCate.getId());
		model.addAttribute("action", "edit");
		model.addAttribute("cate", menuCate);
		setListParent(model);
		return "edit_cate";
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST)
	public String editCateAction(@ModelAttribute MenuCate menu, ModelMap model) {
		model.addAttribute("message", "Cập nhật chưa thành công, vui lòng kiểm tra lại");
		if(menuDAO.updateMenuCate(menu)) {
			model.addAttribute("message", "Cập nhật thành công");
		}
		model.addAttribute("title", "Menu ID: " + menu.getId());
		model.addAttribute("action", "edit");
		model.addAttribute("cate", menu);
		return "edit_cate";
	}
	
	@RequestMapping(value="/addNew", method = RequestMethod.GET)
	public String addNew(ModelMap model) {
		model.addAttribute("cate", new MenuCate());
		model.addAttribute("title", "Thêm Thể Loại");
		model.addAttribute("action", "addCate");
		
		setListParent(model);
		
		return "edit_cate";
	}
	
	private void setListParent(ModelMap model) {
		List<MenuCate> list = menuDAO.getAll();
		Set<Integer> parents = new TreeSet<Integer>();
		for(MenuCate menu : list) {
			parents.add(menu.getId());
		}
		Iterator<Integer> parentIds = parents.iterator();
		List<Integer> listParent = new ArrayList<Integer>();
		while(parentIds.hasNext()) {
			listParent.add(parentIds.next());
		}
		model.addAttribute("listParent", listParent);
	}
	
	@RequestMapping(value="/addCate", method = RequestMethod.POST)
	public String addCate(@ModelAttribute MenuCate menuCate, @RequestParam("file") MultipartFile file,
			@RequestParam("imageName") String imageName, ModelMap model) {
		model.addAttribute("message", "Thêm không thành công");
		String storePath = "D:/Programer/Web/StoreDigital/src/main/webapp/resources/images";
		if(UploadManager.uploadFile(imageName, file, storePath)) {
			menuCate.setImageUrl("/images/" + imageName);
		}
		if(menuDAO.addMenuCate(menuCate)) {
			model.addAttribute("message", "Thêm thành công");
		}
		model.addAttribute("cate", menuCate);
		model.addAttribute("title", "Thêm Thể Loại");
		model.addAttribute("action", "addCate");
		return "edit_cate";
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	@ResponseBody
	public String delete(@RequestParam("id") int id) {
		if(menuDAO.deleteMenuCate(id)) {
			return "{\"status\":\"ok\"}";
		}
		return "{\"status\":\"failure\"}";
	}
}
