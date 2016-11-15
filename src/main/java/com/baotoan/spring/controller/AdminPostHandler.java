package com.baotoan.spring.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baotoan.spring.dao.PostDAO;
import com.baotoan.spring.dao.PostDAOImpl;
import com.baotoan.spring.dao.ProductDAO;
import com.baotoan.spring.dao.ProductDAOImpl;
import com.baotoan.spring.entities.Post;
import com.baotoan.spring.entities.Product;

@Controller
@RequestMapping("/mngPosts")
public class AdminPostHandler {
	private PostDAO postDAO = new PostDAOImpl();
	private ProductDAO productDAO = new ProductDAOImpl();
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/show/{currentPage}/")
	public String listPostPage(@PathVariable int currentPage, ModelMap model, HttpSession session, HttpServletRequest request) {
		session.setAttribute("adminCurrentPage", "mngPosts");
		
		Map<String, Object> result = postDAO.getAll(20, currentPage);
		List<Post> listPost = (List<Post>)result.get("posts");
		String pagination = (String)result.get("pagination");
		model.addAttribute("listPost", listPost);
		model.addAttribute("pagination", pagination.replaceAll("path", request.getContextPath() + "/mngPosts/show"));
		return "mg_posts";
	}
	
	@RequestMapping(value="/view/{id}", method = RequestMethod.GET)
	public String viewPost(@PathVariable int id, ModelMap model, HttpSession session) {
		session.setAttribute("adminCurrentPage", "mngPosts");
		
		Post post = postDAO.getPostById(id);
		model.addAttribute("title", "Bài viết: " + post.getId());
		model.addAttribute("action", "updatePost");
		model.addAttribute("post", post);
		return "edit_post";
	}
	
	@RequestMapping(value="/updatePost", method = RequestMethod.POST)
	public String updatePost(@ModelAttribute("post") Post post, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			return "edit_post";
		}
		post.setPublishDate(new Date());
		model.addAttribute("message", "Cập nhật chưa thành công");
		if(postDAO.updatePost(post)) {
			model.addAttribute("message", "Cáº­p nháº­t thÃ nh cÃ´ng");
		}
		model.addAttribute("post", post);
		model.addAttribute("action", "updatePost");
		model.addAttribute("title", "Bài viết: " + post.getId());
		return "edit_post";
	}
	
	@RequestMapping(value="/addNew", method = RequestMethod.GET)
	public String addNew(ModelMap model, HttpSession session) {
		session.setAttribute("adminCurrentPage", "mngPosts");
		
		model.addAttribute("post", new Post());
		model.addAttribute("action", "addPost");
		model.addAttribute("title", "Viết bài mới");
		return "edit_post";
	}
	
	@RequestMapping(value="/addPost", method = RequestMethod.POST)
	public String addPost(@ModelAttribute("post") Post post, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			return "edit_post";
		}
		post.setPublishDate(new Date());
		model.addAttribute("message", "Chưa thêm thành công");
		if(postDAO.addPost(post)) {
			model.addAttribute("message", "Thêm thành công");
		}
		model.addAttribute("post", post);
		model.addAttribute("action", "addPost");
		model.addAttribute("title", "Viết bài mới");
		return "edit_post";
	}
	
	@RequestMapping(value="delPost", method = RequestMethod.GET)
	public @ResponseBody String delPost(@RequestParam("id") int id) {
		if(id > 1) {
			List<Product> listProduct = productDAO.getProductsByPostId(id);
			for(Product product : listProduct) {
				product.setPostId(1);
				productDAO.updateProduct(product);
			}
			if(postDAO.deletePost(id)) {
				return "{\"status\":\"ok\"}";
			}
			return "{\"status\":\"Error \"}";
		} else {
			return "{\"status\":\"Không được xóa bài này\"}";
		}
	}
}
