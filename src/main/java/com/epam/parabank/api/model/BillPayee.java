package com.epam.parabank.api.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "billPayResult")
public class BillPayee {

    private String name;
    private PayeeAddress address;
    private String phoneNumber;
    private String accountNumber;
}
