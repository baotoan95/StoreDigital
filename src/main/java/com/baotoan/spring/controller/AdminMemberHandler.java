package com.baotoan.spring.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baotoan.spring.dao.RoleDAOImpl;
import com.baotoan.spring.dao.UserDAO;
import com.baotoan.spring.dao.UserDAOImpl;
import com.baotoan.spring.entities.Role;
import com.baotoan.spring.entities.User;

@Controller
@RequestMapping("/mngMembers")
public class AdminMemberHandler {
	private UserDAO userDAO = new UserDAOImpl();
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/show/{currentPage}/", method = RequestMethod.GET)
	public String memberPage(@PathVariable int currentPage, HttpSession session, ModelMap model, HttpServletRequest request) {
		session.setAttribute("adminCurrentPage", "mngMembers");
		
		Map<String, Object> result = userDAO.getAllUser(20, currentPage); 
		List<User> listUser = (List<User>)result.get("users");
		String pagination = (String) result.get("pagination");
		model.addAttribute("users", listUser);
		model.addAttribute("pagination", pagination.replaceAll("path", request.getContextPath() + "/mngMembers/show"));
		
		return "mg_users";
	}
	
	@RequestMapping(value="/view/{mail}/", method = RequestMethod.GET)
	public String view(@PathVariable String mail, ModelMap model) {
		User user = userDAO.getUserByEmail(mail);
		model.addAttribute("title", "TÃ i khoáº£n cá»§a: " + user.getName());
		model.addAttribute("action", "updateUser");
		model.addAttribute("user", user);
		List<Role> listRole = new RoleDAOImpl().getAll();
		model.addAttribute("roles", listRole);
		return "edit_user";
	}
	
	@RequestMapping(value="/updateUser", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("user") User user, ModelMap model) {
		model.addAttribute("message", "Cáº­p nháº­t chÆ°a thÃ nh cÃ´ng");
		if(userDAO.updateUser(user)) {
			model.addAttribute("title", "TÃ i khoáº£n cá»§a: " + user.getName());
		}
		model.addAttribute("action", "updateUser");
		model.addAttribute("user", user);
		model.addAttribute("message", "Cáº­p nháº­t thÃ nh cÃ´ng");
		List<Role> listRole = new RoleDAOImpl().getAll();
		model.addAttribute("roles", listRole);
		return "edit_user";
	}
	
	@RequestMapping(value="/addNew", method = RequestMethod.GET)
	public String addNew(ModelMap model) {
		model.addAttribute("title", "ThÃªm má»›i má»™t tÃ i khoáº£n");
		model.addAttribute("action", "addUser");
		model.addAttribute("user", new User());
		List<Role> listRole = new RoleDAOImpl().getAll();
		model.addAttribute("roles", listRole);
		return "edit_user";
	}
	
	@RequestMapping(value="/addUser", method = RequestMethod.POST)
	public String addUser(@Valid User user, BindingResult result, ModelMap model) {
		model.addAttribute("action", "addUser");
		model.addAttribute("title", "ThÃªm má»›i má»™t tÃ i khoáº£n");
		model.addAttribute("user", user);
		List<Role> listRole = new RoleDAOImpl().getAll();
		model.addAttribute("roles", listRole);
		if(result.hasErrors()) {
			return "edit_user";
		}
		model.addAttribute("message", "ChÆ°a thÃªm thÃ nh cÃ´ng");
		if(userDAO.addUser(user)) {
			model.addAttribute("message", "Ä�Ã£ thÃªm thÃ nh cÃ´ng");
		}

		return "edit_user";
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	public @ResponseBody String delete(@RequestParam("mail") String mail) {
		if(userDAO.deleteUser(mail)) {
			return "{\"status\":\"ok\"}";
		}
		return "{\"status\":\"failure\"}";
	}
}
