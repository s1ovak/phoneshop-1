package com.es.phoneshop.web.controller.pages.compare;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/ajaxCompare")
public class AjaxCompareController {
    @Resource
    private CompareList compareList;

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void addToCompareList(@RequestBody AddToCompareListDto dto) {
        Long phoneId = dto.getPhoneId();
        if(compareList.getPhoneIds().contains(phoneId)) {
            compareList.getPhoneIds().remove(phoneId);
        } else {
            compareList.getPhoneIds().add(phoneId);
        }
    }
}
