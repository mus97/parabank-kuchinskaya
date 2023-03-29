package com.epam.parabank.api.service;

import com.epam.parabank.api.RestBaseService;
import com.epam.parabank.api.model.Accounts;
import com.epam.parabank.api.model.Customer;
import com.epam.parabank.ui.businessobject.builder.UserManager;
import com.epam.parabank.ui.businessobject.model.User;
import io.restassured.response.Response;

public class RestAccountService extends RestBaseService {

    private static final String ACCOUNT_PATH = "/accounts/%s";
    private static final String ACCOUNTS_PATH = "/customers/%s/accounts";
    private static final String CREATE_NEW_ACCOUNT_PATH = "createAccount?customerId=%s&newAccountType=%s&fromAccountId=%s";
    private static final String SAVINGS_TYPE = "1";
    private static final String CHECKING_TYPE = "2";

    private final User user;
    private final RestCustomerService restCustomerService;

    public RestAccountService() {
        user = new UserManager().getUser();
        restCustomerService = new RestCustomerService();
    }

    public Response getCustomerAccounts(String id) {
        return getSpecification().get(String.format(ACCOUNTS_PATH, id));
    }

    public Response getAccountById(String id) {
        return getSpecification().get(String.format(ACCOUNT_PATH, id));
    }

    public void createANewAccount(String accountType) {
        String fromAccountId;
        Customer customer = restCustomerService.getCustomer(user.getUsername(), user.getPassword());
        customer.setAccounts(createModel(getCustomerAccounts(customer.getId()), Accounts.class));

        fromAccountId = customer.getAccounts().getAccountList().get(0).getAccountId();

        switch (accountType) {
            case "SAVINGS" -> accountType = SAVINGS_TYPE;
            case "CHECKING" -> accountType = CHECKING_TYPE;
        }

        getSpecification().post(String.format(CREATE_NEW_ACCOUNT_PATH, customer.getId(), accountType, fromAccountId));
    }

    public Response createNewAccount(final String customerId, final String accountType, final String fromAccountId) {
        return getSpecification().post(String.format(CREATE_NEW_ACCOUNT_PATH, customerId, accountType, fromAccountId));
    }
}
