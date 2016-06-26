package com.baotoan.spring.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.baotoan.spring.validation.DateValidation;

@Documented
@Constraint(validatedBy = DateValidation.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DateValid {
	String message() default "Định dạng ngày giờ chưa đúng!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
