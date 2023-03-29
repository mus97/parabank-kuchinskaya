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
@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Customer {

    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String ssn;
    private Adress address;
    private Accounts accounts;
    private Transactions transactions;
    private Positions positions;
}
