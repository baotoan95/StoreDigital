package com.baotoan.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baotoan.spring.dao.RegUserDAO;
import com.baotoan.spring.dao.RegUserDAOImpl;
import com.baotoan.spring.entities.RegUser;
import com.baotoan.spring.service.Mailer;

@Controller
public class RegUserHanler {
	private RegUserDAO regUserDAO = new RegUserDAOImpl();
	@Autowired
	private Mailer mailer;
	
	@RequestMapping(value="/reguser", method = RequestMethod.GET)
	@ResponseBody
	public String reg(@RequestParam(value="email", required = true) String email) {
		if(!regUserDAO.isReged(email)) {
			regUserDAO.addRegUserDAO(new RegUser(0, email));
			if(mailer.sender(new String[] {email}, "Ä�Äƒng kÃ½ thÃ nh cÃ´ng", "Báº¡n Ä‘Ã£ Ä‘Äƒng kÃ½ nháº­n email vá»›i cÃ¡c báº£n tin má»›i nháº¥t tá»« StoreDigital")) {
				return "{\"message\":\"Báº¡n Ä‘Ã£ Ä‘Äƒng kÃ½ thÃ nh cÃ´ng dá»‹ch vá»¥ cá»§a chÃºng tÃ´i\"}";
			}
		} else {
			return "{\"message\":\"Email Ä‘Ã£ Ä‘Æ°á»£c Ä‘Äƒng kÃ½ trÆ°á»›c Ä‘Ã¢y\"}";
		}
		return "{\"message\":\"CÃ³ lá»—i\"}";
	}
	
}
