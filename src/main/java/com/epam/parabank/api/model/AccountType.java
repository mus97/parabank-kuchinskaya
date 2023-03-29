package com.epam.parabank.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum AccountType {
    CHECKING("CHECKING"),
    SAVINGS("SAVINGS"),
    LOAN("LOAN");
    private final String type;

    public static String getAccountTypeNumber(final String accountType) {
        return String.valueOf(Arrays.stream(AccountType.values())
                .filter(a -> a.getType().equals(accountType))
                .findAny()
                .orElse(CHECKING).ordinal());
    }
}
