package com.epam.parabank.api.utils;

import com.epam.parabank.api.model.Customer;

import lombok.extern.log4j.Log4j2;

import static com.epam.parabank.api.service.CreateEndPoint.createComplexEndpoint;
import static com.epam.parabank.api.service.CreateModel.createJaxbModel;
import static com.epam.parabank.api.utils.GetRequestUtil.makeRequestAndGetResponse;
import static com.epam.parabank.ui.util.PropertyReader.ProjectPropertyName.PARABANK_LOGIN_ENDPOINT;


public class LogInUtil {

    public static Customer logInWithUser(String userName, String password) {
        String xml = makeRequestAndGetResponse(createComplexEndpoint(PARABANK_LOGIN_ENDPOINT.getPropertyName(), userName, password)).asString();
        return createJaxbModel(xml, Customer.class);
    }
}
//<customer>
//  <id>12434</id>
//  <firstName>Sergei</firstName>
//  <lastName>Dovlatov</lastName>
//      <address>
//          <street>63rd Drive</street>
//          <city>New York</city>
//          <state>New York</state>
//          <zipCode>10005</zipCode>
//      </address>
//  <phoneNumber>111333111</phoneNumber>
//  <ssn>111333111</ssn>
//</customer>