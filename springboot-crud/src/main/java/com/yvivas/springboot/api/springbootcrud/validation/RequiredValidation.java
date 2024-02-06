package com.yvivas.springboot.api.springbootcrud.validation;

import org.springframework.util.StringUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RequiredValidation implements ConstraintValidator<IsRequired, String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        // if(value != null && !value.isBlank()){
        //     return true;
        // }else{
        //     return false;
        // }

            //Es lo mismo
            
        return StringUtils.hasText(value);
    }

}
