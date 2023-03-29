package com.epam.parabank.ui.util;

import static java.lang.String.format;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Log4j
public class PropertyReader {
    private static final String PROJECT_PROPERTY_PATH =
            "src/main/resources/project.properties";
    private static PropertyReader propertyReader;
    private final Properties properties;

    private PropertyReader() {
        properties = new Properties();
    }

    public static PropertyReader getInstance() {
        if (propertyReader == null) {
            propertyReader = new PropertyReader();
        }
        return propertyReader;
    }

    public String getProperty(String propertyName) {
        if (properties.isEmpty()) {
            loadProperty();
        }
        return properties.getProperty(propertyName);
    }

    private void loadProperty() {
        try (FileReader reader = new FileReader(PROJECT_PROPERTY_PATH)) {
            properties.load(reader);
        } catch (IOException e) {
            log.warn(format("Exception in property reader %s", e.getMessage()));
        }
    }

    @Getter
    @AllArgsConstructor
    public enum ProjectPropertyName {
        URL_BASE("parabank.url.base"),
        URL_ADMINPAGE("parabank.url.adminpage"),
        URL_ACCOUNTOVERVIEW("parabank.url.accountoverview"),
        PARASOFT_URL("parasoft.url"),
        BROWSER("browser"),
        USER_PATH("parabank.user.path"),
        ENVIRONMENT("environment"),
        PARABANK_BASE_ENDPOINT("https://parabank.parasoft.com/parabank/services/bank"),
        PARABANK_ACCOUNTS_ENDPOINT("https://parabank.parasoft.com/parabank/services/bank/customers/%s/accounts"),
        PARABANK_LOGIN_ENDPOINT("https://parabank.parasoft.com/parabank/services/bank/login"),
        PARABANK_CUSTOMER_INFORMATION_ENDPOINT("https://parabank.parasoft.com/parabank/services/bank/customers/update/"),
        PARABANK_REQUEST_LOAN_ENDPOINT("https://parabank.parasoft.com/parabank/services/bank/requestLoan?customerId="),
        PARABANK_FETCH_TRANSACTION_ENDPOINT("https://parabank.parasoft.com/parabank/services/bank/accounts/"),
        PARABANK_RIGHT_ACCOUNTS_ENDPOINT("https://parabank.parasoft.com/parabank/services/bank/accounts"),
        PARABANK_CLEAN_DATABASE_ENDPOINT("https://parabank.parasoft.com/parabank/services/bank/cleanDB"),
        PARABANK_BILLPAY_ENDPOINT("https://parabank.parasoft.com/parabank/services/bank/billpay"),
        PARABANK_WITHDRAW_ENDPOINT("https://parabank.parasoft.com/parabank/services/bank/withdraw"),
        PARABANK_CREATE_NEW_ACCOUNT_ENDPOINT
                ("https://parabank.parasoft.com/parabank/services/bank/createAccount?customerId=%s&newAccountType=%s&fromAccountId=%s"),
        PARABANK_TRANSFER_FUNDS("https://parabank.parasoft.com/parabank/services/bank/transfer?fromAccountId=%s&toAccountId=%s&amount=%s"),
        PARABANK_LIST_OF_TRANSFERS_FOR_ACCOUNT("https://parabank.parasoft.com/parabank/services/bank/accounts/%s/transactions"),
        PARABANK_INITIALIZE_DATABASE_ENDPOINT("https://parabank.parasoft.com/parabank/services/bank/initializeDB"),
        PARABANK_CREATE_TRANSACTION_BY_AMOUNT_ENDPOINT("https://parabank.parasoft.com/parabank/services/bank/accounts/%s/transactions/amount/%s"),
        PARABANK_GET_TRANSACTION_BY_ID_ENDPOINT("https://parabank.parasoft.com/parabank/services/bank/transactions/%s");
        private String propertyName;
    }

    @Getter
    @AllArgsConstructor
    public enum DatabaseConditions {
        DB_CLEANED("dbcleaned"),
        DB_INITIALIZED("dbinitialized");
        private String propertyName;
    }
}
