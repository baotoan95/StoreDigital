package com.baotoan.spring.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.baotoan.spring.annotation.DateValid;

public class DateValidation implements ConstraintValidator<DateValid, String>{

	public void initialize(DateValid d) {
		
	}

	public boolean isValid(String date, ConstraintValidatorContext arg1) {
		if(date.length() != 19) {
			return false;
		}
		return true;
	}

}
