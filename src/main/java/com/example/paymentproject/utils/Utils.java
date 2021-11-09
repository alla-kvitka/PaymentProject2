package com.example.paymentproject.utils;

import java.security.SecureRandom;

public class Utils {

    public static long randomLong() {
        SecureRandom rng = new SecureRandom();
        return Math.abs(rng.nextLong());
    }

    public static int randomInt() {
        SecureRandom rng = new SecureRandom();
        return Math.abs(rng.nextInt());
    }

}