package com.es.phoneshop.web.controller.pages.cart;

import com.es.core.model.phone.Phone;
import com.es.core.model.phone.PhoneDao;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.annotation.Resource;

@Component
public class UpdateCartValidator implements Validator {
    private static final String ERROR_PATH = "items['%s']";

    @Resource
    PhoneDao phoneDao;

    @Override
    public boolean supports(Class<?> aClass) {
        return UpdateCartDto.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UpdateCartDto updateCartDto = (UpdateCartDto) o;

        updateCartDto.getItems().forEach((key, value) -> {
            Integer itemQuantity;
            try {
                itemQuantity = Integer.parseInt(value);
                if (itemQuantity < 1) {
                    errors.rejectValue(String.format(ERROR_PATH, key), "quantityError.invalidQuantity","Invalid quantity");
                } else {
                    Integer possibleQuantity = phoneDao.getPhoneStock(key);
                    if (itemQuantity > possibleQuantity) {
                        errors.rejectValue(String.format(ERROR_PATH, key), "quantityError.outOfStock","Too big quantity");
                    }
                }
            } catch (NumberFormatException e) {
                errors.rejectValue(String.format(ERROR_PATH, key),"quantityError.invalidQuantity", "Invalid quantity");
            }
        });
    }
}
