package com.es.core.model.phone;

import java.util.List;
import java.util.Optional;

public interface PhoneDao {
    Integer getPhoneStock(Long phoneId);
    Optional<Phone> get(Long key);
    void save(Phone phone);
    List<Phone> findAll(int offset, int limit);
    List<Phone> findAll(String query, int offset, int limit);
    void decreasePhoneStock(Long phoneId, Integer quantity);
}
