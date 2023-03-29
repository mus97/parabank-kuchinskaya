package com.epam.parabank.api.service;

import com.epam.parabank.api.RestBaseService;
import com.epam.parabank.api.model.Customer;

public class RestCustomerService extends RestBaseService {

    private final RestLoginService restLoginService;

    public RestCustomerService() {
        restLoginService = new RestLoginService();
    }

    public Customer getCustomer(final String username, final String password) {
        return createModel(restLoginService.login(username, password), Customer.class);
    }
}
