package com.epam.parabank.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayeeAddress {

    private String zipCode;
    private String state;
    private String city;
    private String street;
}
