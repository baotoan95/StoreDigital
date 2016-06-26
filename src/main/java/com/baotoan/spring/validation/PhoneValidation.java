package com.baotoan.spring.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.baotoan.spring.annotation.Phone;

public class PhoneValidation implements ConstraintValidator<Phone, String> {

	public void initialize(Phone arg0) {
	}

	public boolean isValid(String phoneNo, ConstraintValidatorContext arg1) {
		if(phoneNo == null || phoneNo.length() == 0) {
			return false;
		}
		return phoneNo.matches("[0-9()-\\.]*");
	}

}
