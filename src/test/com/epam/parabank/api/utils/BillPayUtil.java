package com.epam.parabank.api.utils;

import com.epam.parabank.api.model.BillPayee;
import com.epam.parabank.api.model.Customer;
import com.epam.parabank.api.model.PayeeAddress;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import netscape.javascript.JSObject;
import org.json.JSONObject;

public class BillPayUtil {

    public static BillPayee createBillPayee(Customer customer){
        return new BillPayee(customer.getFirstName(),
                new PayeeAddress(customer.getAddress().getZipCode(),
                        customer.getAddress().getState(),
                        customer.getAddress().getCity(),
                        customer.getAddress().getStreet()),
                customer.getPhoneNumber(),
                customer.getAccounts().getAccountList().get(0).getAccountId());
    }

    @SneakyThrows
    public static JSONObject createJson(Object object){

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(object);
        return new JSONObject(json);
    }
}
