	package com.baotoan.spring.controller;
	
	import java.util.List;
	import java.util.Map;
	
	import javax.servlet.http.HttpServletRequest;
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
	import com.baotoan.spring.dao.ProductDAO;
	import com.baotoan.spring.entities.MenuCate;
	import com.baotoan.spring.entities.Product;
	import com.baotoan.spring.utils.Constant;
import com.baotoan.spring.utils.UploadManager;
	
	@Controller
	@RequestMapping("/mngProducts")
	public class AdminProductHandler {
		@Autowired
		private ProductDAO productDAO;
		@Autowired
		private MenuCateDAO menuCateDAO;
		
		@SuppressWarnings("unchecked")
		@RequestMapping(value="/show/{currentPage}/")
		public String productPage(@PathVariable int currentPage, HttpSession session, ModelMap model, HttpServletRequest request) {
			session.setAttribute("adminCurrentPage", "mngProducts");
			
			Map<String, Object> result = productDAO.getProducts("", -1, Constant.DEFAULT, 15, currentPage);
			List<Product> listProduct = (List<Product>)result.get("products");
			String pagination = (String)result.get("htmlForToDoList");
			model.addAttribute("products", listProduct);
			model.addAttribute("pagination", pagination.replaceAll("path", request.getContextPath() + "/mngProducts/show"));
			
			return "mg_products";
		}
		
		@RequestMapping(value = "/add", method = RequestMethod.GET)
		public String addProduct(ModelMap model) {
			model.addAttribute("title", "Thêm sản phẩm");
			model.addAttribute("product", new Product());
			model.addAttribute("categories", generateMenu("", 0));
			model.addAttribute("action", "add");
			return "edit_product";
		}
		
		@RequestMapping(value = "/add", method = RequestMethod.POST)
		public String addProduct(@ModelAttribute Product product, @RequestParam(value="file", required=false) MultipartFile file, 
				@RequestParam(value="imageName", required=false) String name, ModelMap model) {
			UploadManager.uploadFile(name, file, "D:/Programer/Web/StoreDigital/src/main/webapp/resources/images/advertiments");
			product.setUrlImage("/images/advertiments/" + name);
			int key = productDAO.addProduct(product);
			if(key > 0) {
				product.setId(key);
				model.addAttribute("title", "Cập nhập thông tin chi tiết");
				model.addAttribute("product", product);
				model.addAttribute("action", "editDetail");
				return "edit_product_detail";
			} else {
				return "redirect:/add";
			}
		}
		
		@RequestMapping(value = "/del", method = RequestMethod.DELETE)
		@ResponseBody
		public String delProduct(@ModelAttribute(value = "id") int id) {
			productDAO.deleteProduct(id);
			return "{\"\":\"\"}";
		}
		
		
		@RequestMapping(value = "/edit/{proId}")
		public String editProduct(@PathVariable int proId, ModelMap model) {
			Product product = productDAO.getProductById(proId);
			
			// Get list category
			String cates = generateMenu("", 0);
			
			model.addAttribute("title", "Cập nhật thông tin sản phẩm");
			model.addAttribute("product", product);
			model.addAttribute("categories", cates);
			return "edit_product";
		}
		
		private String generateMenu(String space, int parentId) {
			String content = "";
			List<MenuCate> cates = menuCateDAO.getMenuCatesByParentId(parentId);
			for(MenuCate cate : cates) {
				content += "<option value=''>" + space + cate.getName() + "</option>" 
						+ generateMenu("&nbsp;&nbsp;&nbsp;&nbsp;" + space, cate.getId());
			}
			return content;
		}
	}
