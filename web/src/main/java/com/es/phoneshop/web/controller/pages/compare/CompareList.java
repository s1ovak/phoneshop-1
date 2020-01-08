package com.es.phoneshop.web.controller.pages.compare;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompareList {
    private List<Long> phoneIds;

    public CompareList() {
        phoneIds = new ArrayList<>();
    }

    public List<Long> getPhoneIds() {
        return phoneIds;
    }
}
