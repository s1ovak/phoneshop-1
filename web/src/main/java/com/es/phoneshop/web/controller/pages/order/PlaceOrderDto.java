package com.es.phoneshop.web.controller.pages.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderDto {

    @NotBlank(message = "First name should not be null")
    @Size(min = 1, max = 30)
    private String firstName;

    @NotBlank(message = "Last name should not be null")
    private String lastName;

    @NotBlank
    private String address;

    @NotBlank(message = "Phone should not be null")
    private String phone;

    private String info;
}
