package com.vsvet.example.marfeelizer.validator;

import com.vsvet.example.marfeelizer.validator.impl.NotNullOrBlankValidator;
import com.vsvet.example.marfeelizer.validator.impl.SiteDoesNotExistValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SiteDoesNotExistValidator.class)
@Documented
public @interface SiteDoesNotExist {

    String message() default "Site ''{0}'' already was marfeelized checked, please call updated/recheck action.";

    String fieldName() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
