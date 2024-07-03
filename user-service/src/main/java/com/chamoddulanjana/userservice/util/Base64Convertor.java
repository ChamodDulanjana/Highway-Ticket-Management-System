package com.chamoddulanjana.userservice.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Convertor {
    public static String convertPassword(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes());
    }
}
