package com.baotoan.spring.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.baotoan.spring.validation.PhoneValidation;

@Documented
@Constraint(validatedBy = PhoneValidation.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Phone {
	String message() default "Vui lòng nhập số điện thoại của bạn!";
    
    Class<?>[] groups() default {};
      
    Class<? extends Payload>[] payload() default {};
}
