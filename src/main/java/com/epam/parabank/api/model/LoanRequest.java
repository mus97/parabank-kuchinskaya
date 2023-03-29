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
@XmlRootElement(name = "loanResponse")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class LoanRequest {

    private String responseDate;
    private String loanProviderName;
    private Boolean approved;
    private String accountId;

}
