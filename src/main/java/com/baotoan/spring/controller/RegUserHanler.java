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
			if(mailer.sender(new String[] {email}, "Đăng kí thành công", "Bạn đã đăng kí nhận email với các bản tin mới nhất từ StoreDigital")) {
				return "{\"message\":\"Bạn đã đăng kí thành công!\"}";
			}
		} else {
			return "{\"message\":\"Email đã được đăng kí trước đây\"}";
		}
		return "{\"message\":\"Có lỗi, vui lòng báo lại với chúng tôi\"}";
	}
	
}
