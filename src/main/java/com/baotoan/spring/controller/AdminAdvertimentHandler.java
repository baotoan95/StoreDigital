package com.baotoan.spring.controller;

import java.util.List;
import java.util.Map;

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

import com.baotoan.spring.dao.AdvertimentDAO;
import com.baotoan.spring.entities.Advertiment;
import com.baotoan.spring.utils.UploadManager;

@Controller
@RequestMapping("/mngAdvertiments")
public class AdminAdvertimentHandler {
	@Autowired
	private AdvertimentDAO advertDAO;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/show/{currentPage}/", method = RequestMethod.GET)
	public String advertimentPage(@PathVariable int currentPage, HttpSession session, ModelMap model) {
		session.setAttribute("adminCurrentPage", "mngAdvertiments");
		
		Map<String, Object> result = advertDAO.getAdvertiments(20, currentPage);
		List<Advertiment> listAdvert = (List<Advertiment>)result.get("adverts");
		String pagination = (String)result.get("pagination");
		model.addAttribute("adverts", listAdvert);
		model.addAttribute("pagination", pagination);
		
		return "mg_adverts";
	}
	
	@RequestMapping(value="/view/{id}/", method = RequestMethod.GET)
	public String view(@PathVariable int id, ModelMap model) {
		Advertiment advert = advertDAO.getadAdvertimentById(id);
		model.addAttribute("title", "Mã quảng cáo: " + advert.getId());
		model.addAttribute("advert", advert);
		model.addAttribute("action", "updateAdvert");
		return "edit_advert";
	}
	
	@RequestMapping(value="/updateAdvert", method = RequestMethod.POST)
	public String updateAdvert(@ModelAttribute("advert") Advertiment advert, @RequestParam(value="file", required=false) MultipartFile file, 
			@RequestParam(value="imageName", required=false) String name, ModelMap model) {
		model.addAttribute("message", "Cập nhật chưa thành công");
		String store = "D:/Programer/Web/StoreDigital/src/main/webapp/resources/images/advertiments";
		if(UploadManager.uploadFile(name, file, store)) {
			advert.setImageUrl("/images/advertiments/" + name);
		}
		String describe = "<li data-transition='random' data-slotamount='7' data-masterspeed='1000' data-thumb='/Demo/resources/images/advertiments/slider_img_1.html'><img src='/Demo/resources"+advert.getImageUrl()+"' data-bgposition='left top' data-bgfit='cover' data-bgrepeat='no-repeat' alt=\"banner\"/>" +
              "<div    class='tp-caption ExtraLargeTitle sft  tp-resizeme ' data-x='15'  data-y='80'  data-endspeed='500'  data-speed='500' data-start='1100' data-easing='Linear.easeNone' data-splitin='none' data-splitout='none' data-elementdelay='0.1' data-endelementdelay='0.1' style='z-index:2; white-space:nowrap;'>Phong cách mới</div>" +
              "<div    class='tp-caption LargeTitle sfl  tp-resizeme ' data-x='15'  data-y='135'  data-endspeed='500'  data-speed='500' data-start='1300' data-easing='Linear.easeNone' data-splitin='none' data-splitout='none' data-elementdelay='0.1' data-endelementdelay='0.1' style='z-index:3; white-space:nowrap;'>Giá hấp <span>dẫn</span></div>" +
              "<div    class='tp-caption sfb  tp-resizeme ' data-x='15'  data-y='360'  data-endspeed='500'  data-speed='500' data-start='1500' data-easing='Linear.easeNone' data-splitin='none' data-splitout='none' data-elementdelay='0.1' data-endelementdelay='0.1' style='z-index:4; white-space:nowrap;'><a href='#' class=\"view-more\">Xem Thêm</a> <a href='#' class=\"buy-btn\">Mua Ngay</a></div>" +
              "<div    class='tp-caption Title sft  tp-resizeme ' data-x='15'  data-y='230'  data-endspeed='500'  data-speed='500' data-start='1500' data-easing='Power2.easeInOut' data-splitin='none' data-splitout='none' data-elementdelay='0.1' data-endelementdelay='0.1' style='z-index:4; white-space:nowrap;'>Mua ngay còn kịp :D</div>" +
            "</li>";
		advert.setDescribe(describe);
		if(advertDAO.updateAdvertiment(advert)) {
			model.addAttribute("advert", advert);
			model.addAttribute("message", "Cập nhật thành công!");
		}
		model.addAttribute("title", "Mã quảng cáo: " + advert.getId());
		model.addAttribute("action", "updateAdvert");
		return "edit_advert";
	}
	
	@RequestMapping(value="/addNew", method = RequestMethod.GET)
	public String addNew(ModelMap model) {
		model.addAttribute("title", "Thêm quảng cáo");
		model.addAttribute("action", "addAdvert");
		model.addAttribute("advert", new Advertiment());
		return "edit_advert";
	}
	
	@RequestMapping(value="/addAdvert", method = RequestMethod.POST)
	public String addAdvert(@ModelAttribute("advert") Advertiment advert, @RequestParam(value="file", required=false) MultipartFile file, 
			@RequestParam(value="imageName", required=false) String name, ModelMap model) {
		model.addAttribute("message", "Thêm chưa thành công");
		String store = "D:/Programer/Web/StoreDigital/src/main/webapp/resources/images/advertiments";
		if(UploadManager.uploadFile(name, file, store)) {
			advert.setImageUrl("/images/advertiments/" + name);
		}
		if(advertDAO.addAdvertiment(advert)) {
			model.addAttribute("message", "Thêm thành công");
			model.addAttribute("advert", advert);
		}
		model.addAttribute("title", "Thêm quảng cáo");
		model.addAttribute("action", "addAdvert");
		return "edit_advert";
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	public @ResponseBody String delete(@RequestParam("id") int id) {
		System.out.println(id);
		if(advertDAO.deleteAdvertiment(id)) {
			return "{\"status\":\"ok\"}";
		}
		return "{\"status\":\"failure\"}";
	}
}
