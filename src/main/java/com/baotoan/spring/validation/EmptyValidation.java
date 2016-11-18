package com.baotoan.spring.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.baotoan.spring.annotation.NotEmpty;

public class EmptyValidation implements ConstraintValidator<NotEmpty, String>{

	public void initialize(NotEmpty arg0) {
	}

	public boolean isValid(String string, ConstraintValidatorContext arg1) {
		if(string == null) {
			return false;
		}
		return string.length() > 0;
	}
	
}
