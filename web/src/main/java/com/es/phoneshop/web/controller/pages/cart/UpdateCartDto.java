package com.es.phoneshop.web.controller.pages.cart;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class UpdateCartDto {
    private Map<Long, String> items;
}
