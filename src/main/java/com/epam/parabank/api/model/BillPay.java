package com.epam.parabank.api.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "billPayResult")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class BillPay {

    private String accountId;
    private String amount;
    private String payeeName;
}
