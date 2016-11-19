package com.baotoan.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

	// Manage details

	@RequestMapping("/details")
	public String detailPage(ModelMap model) {
		List<ProductDetailByGroup> listDetailsByGroup = productDetailDAO.getAllProductDetailByGroup();
		model.addAttribute("details", listDetailsByGroup);
		return "details";
	}

	@RequestMapping(value = "/addDetail")
	public String addDetail(ModelMap model) {
		model.addAttribute("productDetail", new ProductDetailByGroup());
		model.addAttribute("detailGroups", productDetailDAO.getAllDetailGroup());
		model.addAttribute("action", "addDetail");
		model.addAttribute("title", "Thêm thông số chi tiết");
		return "edit_detail";
	}

	@RequestMapping(value = "/addDetail", method = RequestMethod.POST)
	public String addDetail(@ModelAttribute ProductDetailByGroup productDetailByGroup, ModelMap model) {
		if (productDetailDAO.addDetail(productDetailByGroup)) {
			model.addAttribute("message", "Thêm thành công");
			model.addAttribute("title", "Cập nhật thông số chi tiết");
		} else {
			model.addAttribute("message", "Chưa thêm thành công vui lòng kiểm tra lại");
			model.addAttribute("title", "Thêm thông số chi tiết");
		}
		model.addAttribute("action", "updateDetail");
		model.addAttribute("productDetail", productDetailByGroup);
		model.addAttribute("detailGroups", productDetailDAO.getAllDetailGroup());
		return "edit_detail";
	}

	@RequestMapping(value = "/updateDetail/{id}")
	public String updateDetail(@PathVariable("id") int id, ModelMap modelMap) {
		modelMap.addAttribute("productDetail", productDetailDAO.getDetailByGroupById(id));
		modelMap.addAttribute("action", "updateDetail");
		modelMap.addAttribute("title", "Cập nhật thông số chi tiết");
		modelMap.addAttribute("detailGroups", productDetailDAO.getAllDetailGroup());
		return "edit_detail";
	}

	@RequestMapping(value = "/updateDetail", method = RequestMethod.POST)
	public String updateDetail(@ModelAttribute ProductDetailByGroup productDetailByGroup, ModelMap model) {
		if (productDetailDAO.updateDetail(productDetailByGroup)) {
			model.addAttribute("action", "updateDetail");
			model.addAttribute("message", "Cập nhật thành công");
		} else {
			model.addAttribute("message", "Cập nhật chưa thành công");
		}
		model.addAttribute("title", "Cập nhật thông số chi tiết");
		model.addAttribute("productDetail", productDetailByGroup);
		model.addAttribute("detailGroups", productDetailDAO.getAllDetailGroup());
		return "edit_detail";
	}

	// Manage group detail

	@RequestMapping("/detailGroups")
	public String groupDetailPage(ModelMap model) {
		List<DetailProductGroup> listDetailProductGroup = productDetailDAO.getAllDetailGroup();
		model.addAttribute("detailGroup", listDetailProductGroup);
		return "detail_groups";
	}

	@RequestMapping("/addGroupDetail")
	public String addGroupDetail(ModelMap model) {
		model.addAttribute("detailGroup", new DetailProductGroup());
		model.addAttribute("action", "addGroupDetail");
		model.addAttribute("title", "Thêm nhóm chi tiết");
		return "edit_detail_group";
	}
	
	@RequestMapping(value = "/addGroupDetail", method = RequestMethod.POST)
	public String addGroupDetail(@ModelAttribute DetailProductGroup detailProductGroup, ModelMap model) {
		if(productDetailDAO.addDetailGroup(detailProductGroup)) {
			model.addAttribute("action", "editDetailGroup");
			model.addAttribute("title", "Cập nhật nhóm chi tiết");
			model.addAttribute("message", "Thêm thành công");
		} else {
			model.addAttribute("message", "Thêm chưa thành công");
			model.addAttribute("action", "addGroupDetail");
			model.addAttribute("title", "Thêm nhóm chi tiết");
		}
		model.addAttribute("detailGroup", detailProductGroup);
		return "edit_detail_group";
	}
	
	@RequestMapping(value = "/updateGroupDetail/{id}", method = RequestMethod.GET)
	public String updateGroupDetail(@PathVariable(value = "id") int id, ModelMap model) {
		model.addAttribute("detailGroup", productDetailDAO.getDetailGroupById(id));
		model.addAttribute("action", "updateGroupDetail");
		model.addAttribute("title", "Cập nhật nhóm chi tiết");
		return "edit_detail_group";
	}

	@RequestMapping(value = "/updateGroupDetail", method = RequestMethod.POST)
	public String updateGroupDetail(@ModelAttribute DetailProductGroup detailProductGroup, ModelMap model) {
		if(productDetailDAO.updateDetailGroup(detailProductGroup)) {
			model.addAttribute("message", "Cập nhật thành công");
		} else {
			model.addAttribute("message", "Cập nhật chưa thành công");
		}
		model.addAttribute("action", "editDetailGroup");
		model.addAttribute("detailGroup", detailProductGroup);
		model.addAttribute("title", "Cập nhật nhóm chi tiết");
		return "edit_detail_group";
	}
	
	public String deleteGroupDetail(@PathVariable int id) {
		return "redirect:/mngProductDetails/detailGroups";
	}
}
