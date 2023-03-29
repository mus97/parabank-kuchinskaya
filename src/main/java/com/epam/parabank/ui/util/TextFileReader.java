package com.epam.parabank.ui.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Log4j
public class TextFileReader {
    private static final String PARAGRAPH_REGEX = "\\s{2}+";

    public static String readTextFromFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            log.warn("Exception during file reading : " + e);
        }
        return null;
    }

    public static List<String> readTextFromFileByLines(String filePath) {
        return Stream.of(readTextFromFile(filePath)
                .split(PARAGRAPH_REGEX)).toList();
    }

    @Getter
    @AllArgsConstructor
    public enum FilePath {
        ABOUT_US_TEXT_FILE_PATH("src/main/resources/AboutUsText.txt"),
        CONTACT_US_ERROR("src/main/resources/ContactUsError.txt"),
        CONTACT_US_MESSAGE("src/main/resources/ContactUsMessage.txt"),
        CONTACT_US_RESULT("src/main/resources/ContactUsResult.txt"),
        LOG_OUT_TITLE("src/main/resources/LogOutTitle.txt"),
        FORGOT_LOGIN_INFO_SUCCESS("src/main/resources/ForgotLoginInfoSuccess.txt"),
        FORGOT_LOGIN_INFO_NONEXISTENCE_ERROR("src/main/resources/ForgotLoginInfoNonExistence.txt"),
        FORGOT_LOGIN_INFO_EMPTY_ERROR("src/main/resources/ForgotLoginInfoEmptyError.txt"),
        TRANSFER_FUNDS_MESSAGE("src/main/resources/TransferFundsMessage.txt");
        private final String filePath;
    }
}
