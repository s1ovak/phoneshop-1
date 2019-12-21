package com.es.phoneshop.web.controller.pages.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderDto {

    @NotBlank(message = "First name should not be null")
    private String firstName;

    @NotBlank(message = "Last name should not be null")
    private String lastName;

    @NotBlank(message = "Address should not be null")
    private String address;

    @NotBlank(message = "Phone should not be null")
    private String phone;

    private String info;
}
