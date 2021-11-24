package com.example.paymentproject.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static long randomLong() {
        SecureRandom rng = new SecureRandom();
        return Math.abs(rng.nextLong());
    }

    public static int randomInt() {
        SecureRandom rng = new SecureRandom();
        return Math.abs(rng.nextInt());
    }

    public static boolean checkEmail(String email) {
        String emailRegex = "(?:[a-z0-9!#$%&'*+/=?^_`\\{|\\}~-]+(?:\\." +
                "[a-z0-9!#$%&'*+/=?^_`\\{|\\}~-]+)*|" +
                "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\" +
                "[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*)@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)" +
                "+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]" +
                "?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]" +
                "*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|" +
                "\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static String md5Apache(String st) {
        String md5Hex = DigestUtils.md5Hex(st);
        return md5Hex;
    }
}