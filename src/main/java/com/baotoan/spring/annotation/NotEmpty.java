package com.baotoan.spring.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.baotoan.spring.validation.EmptyValidation;

@Documented
@Constraint(validatedBy = EmptyValidation.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmpty {
	String message() default "Không được phép để trống!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
