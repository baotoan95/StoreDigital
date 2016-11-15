package com.baotoan.spring.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baotoan.spring.dao.PromotionDAO;
import com.baotoan.spring.dao.PromotionDAOImpl;
import com.baotoan.spring.entities.Promotion;
import com.baotoan.spring.entities.PromotionDetail;
import com.baotoan.spring.utils.UploadManager;

@Controller
@RequestMapping("/mngPromotions")
public class AdminPromotionHandler {
	private PromotionDAO promotionDAO = new PromotionDAOImpl();
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/show/{currentPage}", method = RequestMethod.GET)
	public String promotionPage(@PathVariable int currentPage, HttpSession session, ModelMap model) {
		session.setAttribute("adminCurrentPage", "mngPromotions");
		
		Map<String, Object> result = promotionDAO.getPromotions(15, currentPage);
		List<Promotion> listPromotion = (List<Promotion>)result.get("promotions");
		String pagination = (String)result.get("htmlForToDoList");
		model.addAttribute("promotions", listPromotion);
		model.addAttribute("pagination", pagination);
		return "mg_promotions";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/view/{promotionId}/{currentPage}", method = RequestMethod.GET)
	public String viewPromotion(@PathVariable int promotionId, @PathVariable int currentPage, ModelMap model,
			 HttpSession session) {
		session.setAttribute("adminCurrentPage", "mngPromotions");
		
		Promotion promotion = promotionDAO.getPromotionById(promotionId);
		Map<String, Object> result = promotionDAO.getPromotionDetailByPromotionId(promotionId, 15, currentPage);
		List<PromotionDetail> listPro = (List<PromotionDetail>) result.get("promotions");
		String pagination = (String) result.get("htmlForToDoList");
		model.addAttribute("promotionsDetail", listPro);
		model.addAttribute("promotion", promotion);
		model.addAttribute("pagination", pagination);
		return "view_promotion";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/viewDetail/{id}", method = RequestMethod.GET)
	public String viewDetail(@PathVariable int id, ModelMap model) {
		Map<String, Object> result = promotionDAO.getPromotions(999, 1);
		List<Promotion> listPromotion = (List<Promotion>)result.get("promotions");
		
		PromotionDetail pro = promotionDAO.getPromotionDetailById(id);
		model.addAttribute("title", "Mã khuyến mãi: " + promotionDAO.getPromotionById(pro.getPromotionId()).getId());
		model.addAttribute("promotionDetail", pro);
		model.addAttribute("promotions", listPromotion);
		model.addAttribute("action", "update");
		return "promotion_detail";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/update", method = RequestMethod.GET, produces="application/x-www-form-urlencoded;charset=UTF-8") 
	public String update(@ModelAttribute PromotionDetail promotionDetail, ModelMap model) {
		if(promotionDAO.updatePromotionDetail(promotionDetail)) {
			model.addAttribute("message", "Cập nhật thành công");
		} else {
			model.addAttribute("message", "Cập nhật chưa thành công");
		}
		Map<String, Object> result = promotionDAO.getPromotions(999, 1);
		List<Promotion> listPromotion = (List<Promotion>)result.get("promotions");
		model.addAttribute("promotions", listPromotion);
		model.addAttribute("title", "Mã khuyến mãi: " + promotionDAO.getPromotionById(promotionDetail.getPromotionId()).getId());
		model.addAttribute("promotionDetail", promotionDetail);
		model.addAttribute("action", "update");
		return "promotion_detail";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/addNew/{type}")
	public String addNew(@PathVariable int type, ModelMap model, HttpSession session) {
		session.setAttribute("adminCurrentPage", "mngPromotions");
		
		if(type == 1) { // add promotion
			model.addAttribute("promotion", new Promotion());
			model.addAttribute("title", "Thêm khuyến mãi");
			return "promotion";
		} else { // Add promotion detail
			model.addAttribute("promotionDetail", new PromotionDetail());
			model.addAttribute("title", "Thêm chi tiết khuyến mãi");
			model.addAttribute("action", "addPromotionDetail");
			Map<String, Object> result = promotionDAO.getPromotions(999, 1);
			List<Promotion> listPromotion = (List<Promotion>)result.get("promotions");
			model.addAttribute("promotions", listPromotion);
			return "promotion_detail";
		}
	}
	
	@RequestMapping(value="/addPromotion", method = RequestMethod.POST)
	public String addPromotion(@ModelAttribute Promotion promotion, @RequestParam("file") MultipartFile file ,
			@RequestParam("imageName") String name, ModelMap model) {
		model.addAttribute("title", "Thêm khuyến mãi");
		model.addAttribute("promotion", new Promotion());
		String pathStore = "D:/Programer/Web/StoreDigital/src/main/webapp/resources/images/promotions";
		if(UploadManager.uploadFile(name, file, pathStore)) {
			promotion.setImageUrl("/images/promotions/" + name);
			promotionDAO.addPromotion(promotion);
			model.addAttribute("message", "Thêm thành công");
		}
		return "promotion";
	}
	
	@RequestMapping(value="/addPromotionDetail", method = RequestMethod.GET)
	public String addPromotionDetail(@ModelAttribute PromotionDetail promotionDetail, ModelMap model) {
		model.addAttribute("title", "Thêm khuyến mãi");
		model.addAttribute("action", "addPromotionDetail");
		model.addAttribute("promotionDetail", new PromotionDetail());
		if(promotionDAO.addPromotionDetail(promotionDetail)) {
			model.addAttribute("message", "Thêm thành công");
		} else {
			model.addAttribute("message", "Thêm chưa thành công");
		}
		return "promotion_detail";
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	@ResponseBody
	public String delPromotion(@RequestParam(value="id", required=true) int id,
			@RequestParam(value="type", required=true) String type) {
		if(type.equalsIgnoreCase("promotion")) {
			promotionDAO.deletePromotionDetailsByPromotionId(id);
			if(promotionDAO.deletePromotion(id)) {
				return "{\"status\":\"ok\"}";
			}
		} else {
			if(promotionDAO.deletePromotionDetail(id)) {
				return "{\"status\":\"ok\"}";
			}
		}
		return "{\"status\":\"fail\"}";
	}
	
}
