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

import com.baotoan.spring.dao.ImageDAO;
import com.baotoan.spring.dao.MenuCateDAO;
import com.baotoan.spring.dao.PostDAO;
import com.baotoan.spring.dao.ProductDAO;
import com.baotoan.spring.dao.PromotionDAO;
import com.baotoan.spring.entities.Image;
import com.baotoan.spring.entities.MenuCate;
import com.baotoan.spring.entities.Product;
import com.baotoan.spring.utils.Constant;
import com.baotoan.spring.utils.GenerateCode;
import com.baotoan.spring.utils.UploadManager;
	
	@Controller
	@RequestMapping("/mngProducts")
	public class AdminProductHandler {
		@Autowired
		private ProductDAO productDAO;
		@Autowired
		private MenuCateDAO menuCateDAO;
		@Autowired
		private PostDAO postDAO;
		@Autowired
		private PromotionDAO promotionDAO;
		@Autowired
		private ImageDAO imageDAO;
		
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
			model.addAttribute("posts", postDAO.getAllWithoutContent());
			model.addAttribute("promotions", promotionDAO.getAll());
			model.addAttribute("action", "add");
			return "edit_product";
		}
		
		@RequestMapping(value = "/add", method = RequestMethod.POST)
		public String addProduct(@ModelAttribute Product product, @RequestParam("avatar") MultipartFile file, ModelMap model) {
			String fileName = GenerateCode.generateFileName() + ".jpg";
			product.setUrlImage("/images/advertiments/" + fileName);
			UploadManager.uploadFile(fileName, file, "D:/Programer/Web/StoreDigital/src/main/webapp/resources/images/advertiments");
			int key = productDAO.addProduct(product);
			imageDAO.addImage(new Image(0, fileName, product.getUrlImage(), key, true));
			model.addAttribute("product", product);
			if(key > 0) {
				product.setId(key);
				model.addAttribute("title", "Cập nhập thông tin chi tiết");
				model.addAttribute("action", "editDetail");
				
				return "edit_product_detail";
			} else {
				return "redirect:/mngProducts/add";
			}
		}
		
		private void setAttributeForEditProductDetail(ModelMap model) {
			model.addAttribute("", attributeValue)
		}
		
		public String editDetail() {
			
			return "edit_product_detail";
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
			
			model.addAttribute("title", "Cập nhật thông tin sản phẩm");
			model.addAttribute("product", product);
			model.addAttribute("categories", generateMenu("", 0));
			model.addAttribute("posts", postDAO.getAllWithoutContent());
			model.addAttribute("promotions", promotionDAO.getAll());
			model.addAttribute("action", "editDetail");
			return "edit_product";
		}
		
		private String generateMenu(String space, int parentId) {
			String content = "";
			List<MenuCate> cates = menuCateDAO.getMenuCatesByParentId(parentId);
			for(MenuCate cate : cates) {
				content += "<option value='"+ cate.getId() +"'>" + space + cate.getName() + "</option>" 
						+ generateMenu("&nbsp;&nbsp;&nbsp;&nbsp;" + space, cate.getId());
			}
			return content;
		}
	}
