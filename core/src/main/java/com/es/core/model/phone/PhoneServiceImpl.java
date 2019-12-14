package com.es.core.model.phone;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PhoneServiceImpl implements PhoneService {
    @Resource
    private PhoneDao phoneDao;

    @Override
    public Optional<Phone> getPhoneById(Long id) {
       return phoneDao.get(id);
    }

    @Override
    public List<Phone> findPhones(String query, String sort, String order, Integer page) {
        List<Phone> validPhones = phoneDao.findAll(query, (page - 1) * 10, 10);
        if (validPhones != null) {
            return sortPhones(createComparatorForSort(sort, order), validPhones);
        } else return null;
    }

    private Comparator<Phone> createComparatorForSort(String sort, String order) {
        Comparator<Phone> PhoneComparator = null;
        if (sort != null && !sort.trim().isEmpty() && order != null && !order.trim().isEmpty()) {
            if ("brand".equalsIgnoreCase(sort)) {
                PhoneComparator = "asc".equalsIgnoreCase(order)
                        ? Comparator.comparing(Phone::getBrand)
                        : Comparator.comparing(Phone::getBrand).reversed();
            } else if ("price".equalsIgnoreCase(sort)) {
                PhoneComparator = "asc".equalsIgnoreCase(order)
                        ? Comparator.comparing(Phone::getPrice)
                        : Comparator.comparing(Phone::getPrice).reversed();
            } else if ("model".equalsIgnoreCase(sort)) {
                PhoneComparator = "asc".equalsIgnoreCase(order)
                        ? Comparator.comparing(Phone::getModel)
                        : Comparator.comparing(Phone::getModel).reversed();
            } else if ("displaySize".equalsIgnoreCase(sort)) {
                PhoneComparator = "asc".equalsIgnoreCase(order)
                        ? Comparator.comparing(Phone::getDisplaySizeInches)
                        : Comparator.comparing(Phone::getDisplaySizeInches).reversed();
            }
        }
        return PhoneComparator;
    }

    private List<Phone> sortPhones(
            Comparator<Phone> PhoneComparator, List<Phone> phones) {
        Objects.requireNonNull(phones, "Collection should not be null");

        if (PhoneComparator != null) {
            phones = phones
                    .stream()
                    .sorted(PhoneComparator)
                    .collect(Collectors.toList());
        }
        return phones;
    }

}
