package com.epam.parabank.api.service;

import com.epam.parabank.api.model.BillPayee;
import com.epam.parabank.api.model.Customer;
import com.epam.parabank.api.model.PayeeAddress;

public class RestBillPayeeService {

    public BillPayee createBillPayee(Customer customer){
        return new BillPayee(customer.getFirstName(),
                new PayeeAddress(customer.getAddress().getZipCode(),
                        customer.getAddress().getState(),
                        customer.getAddress().getCity(),
                        customer.getAddress().getStreet()),
                customer.getPhoneNumber(),
                customer.getAccounts().getAccountList().get(0).getAccountId());
    }
}
