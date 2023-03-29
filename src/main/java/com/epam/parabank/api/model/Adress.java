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
@XmlRootElement(name = "address")
@XmlAccessorType(XmlAccessType.FIELD)
public class Adress {

    private String street;
    private String city;
    private String state;
    private String zipCode;
}
