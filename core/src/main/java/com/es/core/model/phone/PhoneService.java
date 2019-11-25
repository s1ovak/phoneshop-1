package com.es.core.model.phone;

import java.util.List;

public interface PhoneService {
    List<Phone> findPhones(String query, String sort, String order, Integer page);
}
