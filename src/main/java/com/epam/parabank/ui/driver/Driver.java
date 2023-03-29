package com.epam.parabank.ui.driver;

import java.util.Arrays;

public enum Driver {

    CHROME("chrome"), FIREFOX("firefox"), EDGE("edge");

    private String driverType;

    Driver(String driverType) {
        this.driverType = driverType;
    }

    public String getDriverType() {
        return driverType;
    }

    public static Driver getByDriverType(String driverType) {
        return Arrays.stream(Driver.values())
                .filter(driver -> driver.getDriverType().equals(driverType))
                .findAny()
                .orElse(CHROME);
    }
}
