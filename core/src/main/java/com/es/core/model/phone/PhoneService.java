package com.es.core.model.phone;

import java.util.List;
import java.util.Optional;

public interface PhoneService {
    List<Phone> findPhones(String query, String sort, String order, Integer page);
    Optional<Phone> getPhoneById(Long id);
}
