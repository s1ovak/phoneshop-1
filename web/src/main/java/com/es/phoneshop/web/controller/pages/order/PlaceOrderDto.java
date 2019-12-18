package com.es.phoneshop.web.controller.pages.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderDto {

    @Length(min = 1, message = "First name should not be null")
    private String firstName;

    @Length(min = 1, message = "Last name should not be null")
    private String lastName;

    @Length(min = 1, message = "Address should not be null")
    private String address;

    @Length(min = 1, message = "Phone should not be null")
    private String phone;

    private String info;
}
