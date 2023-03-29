package com.epam.parabank.api.service;

import java.io.StringReader;

import org.apache.commons.lang3.StringUtils;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.log4j.Log4j;

@Log4j
public class CreateModel {

    private static final String NS2_REGEX = "ns2:";

    public static <T> T createJaxbModel(String xml, Class<T> type) {
        try {
            StringReader stringReader = new StringReader(xml.replaceAll(NS2_REGEX, StringUtils.EMPTY));
            JAXBContext jaxbContext = org.eclipse.persistence.jaxb.JAXBContextFactory
                    .createContext(new Class[]{type}, null);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (T) unmarshaller.unmarshal(stringReader);
        } catch (Exception e) {
            log.error("There is exception in Unmarshaller process : ", e);
        }
        return null;
    }
}
