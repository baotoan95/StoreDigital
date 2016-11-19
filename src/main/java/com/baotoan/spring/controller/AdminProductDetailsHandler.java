package com.baotoan.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baotoan.spring.dao.ProductDetailDAO;
import com.baotoan.spring.entities.DetailProductGroup;
import com.baotoan.spring.entities.ProductDetailByGroup;

@Controller
@RequestMapping("/mngProductDetails")
public class AdminProductDetailsHandler {
	@Autowired
	private ProductDetailDAO productDetailDAO;
	
	@RequestMapping("/details")
	public String detailPage(ModelMap model) {
		List<ProductDetailByGroup> listDetailsByGroup = productDetailDAO.getAllProductDetailByGroup();
		model.addAttribute("details", listDetailsByGroup);
		return "details";
	}
	
	@RequestMapping(value = "/addDetail")
	public String addDetail(ModelMap model) {
		model.addAttribute("productDetail", new ProductDetailByGroup());
		model.addAttribute("action", "addDetail");
		return "edit_detail";
	}
	
	@RequestMapping(value = "/addDetail", method = RequestMethod.POST)
	public String addDetail(@ModelAttribute ProductDetailByGroup productDetailByGroup, ModelMap model) {
		if(productDetailDAO.addDetail(productDetailByGroup)) {
			model.addAttribute("message", "Thêm thành công");
		} else {
			model.addAttribute("message", "Chưa thêm thành công vui lòng kiểm tra lại");
		}
		model.addAttribute("action", "updateDetail");
		model.addAttribute("productDetail", productDetailByGroup);
		return "edit_detail";
	}
	
	@RequestMapping(value = "/updateDetail/{id}")
	public String updateDetail(@RequestParam("id") int id, ModelMap modelMap) {
		modelMap.addAttribute("productDetail", productDetailDAO.getDetailByGroupById(id));
		modelMap.addAttribute("action", "updateDetail");
		return "edit_detail";
	}
	
	@RequestMapping(value = "/updateDetail", method = RequestMethod.POST)
	public String updateDetail(@ModelAttribute ProductDetailByGroup productDetailByGroup, ModelMap model) {
		if(productDetailDAO.updateDetail(productDetailByGroup)) {
			model.addAttribute("action", "updateDetail");
			model.addAttribute("message", "Cập nhật thành công");
		} else {
			model.addAttribute("message", "Cập nhật chưa thành công");
		}
		model.addAttribute("productDetail", productDetailByGroup);
		return "edit_detail";
	}
	
	@RequestMapping("/detailGroups")
	public String groupDetailPage(ModelMap model) {
		List<DetailProductGroup> listDetailProductGroup = productDetailDAO.getAllDetailGroup();
		model.addAttribute("detailGroup", listDetailProductGroup);
		return "detail_groups";
	}
	
	public String addGroupDetail(@ModelAttribute DetailProductGroup detailProductGroup, ModelMap model) {
		return "edit_detail_group";
	}
	
	public String updateGroupDetail() {
		return "";
	}
}
