package com.es.phoneshop.web.controller.pages.ajax;

import com.es.core.model.phone.Phone;
import com.es.core.model.phone.PhoneDao;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.annotation.Resource;

@Component
public class AddToCartValidator implements Validator {

    @Resource
    PhoneDao phoneDao;

    @Override
    public boolean supports(Class<?> aClass) {
        return AddToCartDto.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        AddToCartDto addToCartDto = (AddToCartDto) o;
        Integer quantity;

        try {
            quantity = Integer.parseInt(addToCartDto.getQuantity());
        } catch (NumberFormatException e) {
            errors.reject("quantityError.invalidQuantity", "Invalid quantity");
            return;
        }

        if (quantity < 1) {
            errors.reject("quantityError.invalidQuantity", "Invalid quantity");
        } else if (phoneDao.getPhoneStock(addToCartDto.getPhoneId()) < quantity) {
            errors.reject("quantityError.outOfStock", "Quantity is too big");
        }
    }
}
