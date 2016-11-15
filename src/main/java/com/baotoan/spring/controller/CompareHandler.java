package com.baotoan.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baotoan.spring.dao.ProductDAO;
import com.baotoan.spring.dao.ProductDAOImpl;
import com.baotoan.spring.entities.DetailProduct;
import com.baotoan.spring.entities.Product;

@Controller
public class CompareHandler {
	private ProductDAO productDAO = new ProductDAOImpl();
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/addCompare", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> addCompare(@RequestParam(value="id", required = true) int id, HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", "");
		result.put("product", null);
		List<Product> listCompare = (List<Product>)session.getAttribute("listCompare");
		if(null == listCompare) {
			listCompare = new ArrayList<Product>();
			session.setAttribute("listCompare", listCompare);
		}
		if(listCompare.size() == 4) {
			result.put("status", "Vui lòng xóa bớt danh sách so sánh (tối đa 4 sản phẩm)");
			return result;
		}
		for(int i = 0; i < listCompare.size(); i++) {
			if(listCompare.get(i).getId() == id) {
				result.put("status", "Sản phẩm đã tồn tại");
				return result;
			}
		}
		Product product = productDAO.getProductById(id);
		product.getUrlImage();
		listCompare.add(product);
		result.put("status", "ok");
		result.put("product", product);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/delCompare", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> delCompare(@RequestParam(value="id", required = true) int id, HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Product> listCompare = (List<Product>)session.getAttribute("listCompare");
		for(int i = 0; i < listCompare.size(); i++) {
			if(listCompare.get(i).getId() == id) {
				result.put("product", listCompare.get(i));
				listCompare.remove(i);
				result.put("status", "ok");
				result.put("quantity", listCompare.size());
				return result;
			}
		}
		result.put("status", "Có lỗi!");
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/viewCompare", method = RequestMethod.GET)
	public String viewCompare(HttpSession session, ModelMap model) {
		List<Product> listCompare = (List<Product>)session.getAttribute("listCompare");
		if(null == listCompare || listCompare.size() <= 0) {
			return "redirect:index";
		}
		
		for(Product pro : listCompare) {
			Map<String, Map<String, DetailProduct>> detail = productDAO.getDetailProduct(pro.getId());
			pro.setDetail(detail);
 		}
		
		Map<String, Set<String>> groups = getGroup(listCompare);
		model.addAttribute("groups", groups);
		return "compare";
	}
	
	private Map<String, Set<String>> getGroup(List<Product> listProduct) {
		Map<String, Set<String>> listGroupTemp = new HashMap<String, Set<String>>();
		
		Set<String> groups = new HashSet<String>();
		for(Product pro : listProduct) {
			Map<String, Map<String, DetailProduct>> detail = pro.getDetail();
			Set<String> group = detail.keySet();
			Iterator<String> iter = group.iterator();
			while(iter.hasNext()) {
				String groupName = iter.next();
				if(!groups.contains(groupName)) {
					groups.add(groupName);
				}
			}
		}
		
		Iterator<String> iterGroup = groups.iterator();
		while(iterGroup.hasNext()) {
			Set<String> nameDetails = new TreeSet<String>();
			String groupName = iterGroup.next();
			for(Product pro : listProduct) {
				Map<String, Map<String, DetailProduct>> detail = pro.getDetail();
				Map<String, DetailProduct> d = detail.get(groupName);
				Set<String> names = d.keySet();
				Iterator<String> iter = names.iterator();
				while(iter.hasNext()) {
					nameDetails.add(iter.next());
				}
			}
			
			listGroupTemp.put(groupName, nameDetails);
		}
		
		return listGroupTemp;
	}
}
