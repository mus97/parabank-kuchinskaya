package com.epam.parabank.cucumber.service;

import com.epam.parabank.api.model.Accounts;
import com.epam.parabank.api.model.Customer;
import com.epam.parabank.api.service.RestAccountService;
import com.epam.parabank.api.service.RestCustomerService;
import com.epam.parabank.api.service.RestTransferFundsService;
import com.epam.parabank.ui.businessobject.builder.UserManager;
import com.epam.parabank.ui.businessobject.model.User;

public class TransferFundsService {

    private final User user;
    private final RestAccountService restAccountService;
    private final RestCustomerService restCustomerService;
    private final RestTransferFundsService restTransferFundsService;

    public TransferFundsService() {
        user = new UserManager().getUser();
        restAccountService = new RestAccountService();
        restCustomerService = new RestCustomerService();
        restTransferFundsService = new RestTransferFundsService();
    }

    public void transferFunds(String amount) {
        String fromAccountId;
        String toAccountId;
        Customer customer = restCustomerService.getCustomer(user.getUsername(), user.getPassword());
        customer.setAccounts(restAccountService.createModel(restAccountService.getCustomerAccounts(customer.getId()), Accounts.class));

        fromAccountId = customer.getAccounts().getAccountList().get(0).getAccountId();
        toAccountId = customer.getAccounts().getAccountList().get(1).getAccountId();

        restTransferFundsService.transferFunds(fromAccountId, toAccountId, amount);
    }
}