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
@XmlRootElement(name = "position")
@XmlAccessorType(XmlAccessType.FIELD)
public class Position {

    private String positionId;
    private String customerId;
    private String name;
    private String symbol;
    private String shares;
    private String purchasePrice;
}