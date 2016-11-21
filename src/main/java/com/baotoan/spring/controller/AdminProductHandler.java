	package com.baotoan.spring.controller;
	
	import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
import org.springframework.web.multipart.MultipartFile;

import com.baotoan.spring.dao.ImageDAO;
import com.baotoan.spring.dao.MenuCateDAO;
import com.baotoan.spring.dao.PostDAO;
import com.baotoan.spring.dao.ProductDAO;
import com.baotoan.spring.dao.ProductDetailDAO;
import com.baotoan.spring.dao.PromotionDAO;
import com.baotoan.spring.dto.ProductDetailFormDTO;
import com.baotoan.spring.entities.DetailProduct;
import com.baotoan.spring.entities.DetailProductGroup;
import com.baotoan.spring.entities.Image;
import com.baotoan.spring.entities.MenuCate;
import com.baotoan.spring.entities.Product;
import com.baotoan.spring.entities.ProductDetailByGroup;
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
		@Autowired
		private ProductDetailDAO productDetailDAO;
		
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
		public String addProduct(@ModelAttribute Product product, @RequestParam("avatarUrl") MultipartFile file, ModelMap model) {
			String fileName = GenerateCode.generateFileName() + ".jpg";
			product.setUrlImage("/images/advertiments/" + fileName);
			UploadManager.uploadFile(fileName, file, "D:/Programer/Web/StoreDigital/src/main/webapp/resources/images/advertiments");
			
			product.setImportDate(new Date());
			
			int key = productDAO.addProduct(product);
			imageDAO.addImage(new Image(0, fileName, product.getUrlImage(), key, true));
			model.addAttribute("product", product);
			if(key > 0) {
				return "redirect:/mngProducts/addDetail/" + key;
			} else {
				return "redirect:/mngProducts/add";
			}
		}
		
		@RequestMapping("/addDetail/{id}")
		public String addDetail(@PathVariable("id") int prodId, ModelMap model) {
			Product product = productDAO.getProductById(prodId);
			if(null == product) {
				return "redirect:/mngProducts/show/1/";
			} else {
				Map<DetailProductGroup, List<ProductDetailByGroup>> data = new TreeMap<DetailProductGroup, List<ProductDetailByGroup>>();
				List<DetailProductGroup> detailProductGroups = productDetailDAO.getAllDetailGroup();
				for(DetailProductGroup detailProductGroup : detailProductGroups) {
					List<ProductDetailByGroup> productDetailByGroups = productDetailDAO.getAllProductDetailByGroupId(detailProductGroup.getId());
					data.put(detailProductGroup, productDetailByGroups);
				}
				model.addAttribute("title", "Cập nhập thông tin chi tiết");
				model.addAttribute("action", "addDetail");
				model.addAttribute("details", data);
				ProductDetailFormDTO productDetailFormDTO = new ProductDetailFormDTO();
				productDetailFormDTO.setProductId(prodId);
				model.addAttribute("productDetailForm", productDetailFormDTO);
			}
			return "edit_product_detail";
		}
		
		@RequestMapping(value = "/addDetail", method = RequestMethod.POST)
		public String addDetail(@ModelAttribute("productDetailForm") ProductDetailFormDTO productDetailFormDTO, ModelMap model) {
			List<DetailProduct> detailProducts = productDetailFormDTO.getDetailsProduct();
			
			Map<DetailProductGroup, List<ProductDetailByGroup>> data = new TreeMap<DetailProductGroup, List<ProductDetailByGroup>>();
			List<DetailProductGroup> detailProductGroups = productDetailDAO.getAllDetailGroup();
			for(DetailProductGroup detailProductGroup : detailProductGroups) {
				List<ProductDetailByGroup> productDetailByGroups = productDetailDAO.getAllProductDetailByGroupId(detailProductGroup.getId());
				data.put(detailProductGroup, productDetailByGroups);
			}
			
			if(null != detailProducts && detailProducts.size() > 0) {
				for(DetailProduct detailProduct : detailProducts) {
					String value = detailProduct.getValue().trim();
					if(!value.equals("")) {
						productDAO.addDetailProduct(detailProduct);
					}
				}
				model.addAttribute("title", "Cập nhật thông tin chi tiết");
				model.addAttribute("action", "editDetail");
			} else {
				model.addAttribute("title", "Thêm thông tin chi tiết");
				model.addAttribute("action", "addDetail");
			}
			
			model.addAttribute("productDetailForm", productDetailFormDTO);
			model.addAttribute("details", data);
			return "edit_product_detail";
		}
		
		@RequestMapping(value = "/edit/{proId}")
		public String editProduct(@PathVariable int proId, ModelMap model) {
			Product product = productDAO.getProductById(proId);
			
			model.addAttribute("title", "Cập nhật thông tin sản phẩm");
			model.addAttribute("product", product);
			model.addAttribute("categories", generateMenu("", 0));
			model.addAttribute("posts", postDAO.getAllWithoutContent());
			model.addAttribute("promotions", promotionDAO.getAll());
			model.addAttribute("action", "edit");
			return "edit_product";
		}
		
		@RequestMapping(value = "/edit", method = RequestMethod.POST)
		public String editProduct(@ModelAttribute Product product, @RequestParam("avatarUrl") MultipartFile file, ModelMap model) {
			if(null != file) {
				String fileName = GenerateCode.generateFileName() + ".jpg";
				product.setUrlImage("/images/advertiments/" + fileName);
				UploadManager.uploadFile(fileName, file, "D:/Programer/Web/StoreDigital/src/main/webapp/resources/images/advertiments");
			}
			if(productDAO.updateProduct(product)) {
				model.addAttribute("title", "Cập nhật thành công");
				return "redirect:/mngProducts/editDetail/" + product.getId();
			} else {
				model.addAttribute("title", "Cập nhật chưa thành công");
				return "edit_product";
			}
		}
		
		@RequestMapping(value = "/editDetail/{id}")
		public String editDetail(@PathVariable("id") int id, ModelMap model) {
			Product product = productDAO.getProductById(id);
			if(null == product) {
				return "redirect:/mngProducts/show/1/";
			} else {
				Map<DetailProductGroup, List<ProductDetailByGroup>> data = new TreeMap<DetailProductGroup, List<ProductDetailByGroup>>();
				List<DetailProductGroup> detailProductGroups = productDetailDAO.getAllDetailGroup();
				for(DetailProductGroup detailProductGroup : detailProductGroups) {
					List<ProductDetailByGroup> productDetailByGroups = productDetailDAO.getAllProductDetailByGroupId(detailProductGroup.getId());
					data.put(detailProductGroup, productDetailByGroups);
				}
				model.addAttribute("title", "Cập nhập thông tin chi tiết");
				model.addAttribute("action", "editDetail");
				model.addAttribute("details", data);
				ProductDetailFormDTO productDetailFormDTO = new ProductDetailFormDTO();
				productDetailFormDTO.setDetailsProduct(productDAO.getDetailProductsByProductId(id));
				productDetailFormDTO.setProductId(id);
				model.addAttribute("productDetailForm", productDetailFormDTO);
			}
			return "edit_product_detail";
		}
		
		@RequestMapping(value = "/editDetail", method = RequestMethod.POST)
		public String editDetail(@ModelAttribute("productDetailForm") ProductDetailFormDTO productDetailFormDTO, ModelMap model) {
			List<DetailProduct> detailProducts = productDetailFormDTO.getDetailsProduct();
			
			Map<DetailProductGroup, List<ProductDetailByGroup>> data = new TreeMap<DetailProductGroup, List<ProductDetailByGroup>>();
			List<DetailProductGroup> detailProductGroups = productDetailDAO.getAllDetailGroup();
			for(DetailProductGroup detailProductGroup : detailProductGroups) {
				List<ProductDetailByGroup> productDetailByGroups = productDetailDAO.getAllProductDetailByGroupId(detailProductGroup.getId());
				data.put(detailProductGroup, productDetailByGroups);
			}
			
			if(null != detailProducts && detailProducts.size() > 0) {
				for(DetailProduct detailProduct : detailProducts) {
					String value = detailProduct.getValue().trim();
					if(!value.equals("")) {
						productDAO.addDetailProduct(detailProduct);
					}
				}
				model.addAttribute("title", "Cập nhật thông tin chi tiết");
			} else {
				model.addAttribute("title", "Cập nhật chưa tin chi tiết");
			}
			
			model.addAttribute("action", "editDetail");
			model.addAttribute("productDetailForm", productDetailFormDTO);
			model.addAttribute("details", data);
			return "edit_product_detail";
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
		
		@RequestMapping("/delete/{id}")
		public String deleteProduct(@PathVariable("id") int id, ModelMap model) {
			productDAO.deleteProduct(id);
			return "redirect:/mngProducts/show/1/";
		}
	}
