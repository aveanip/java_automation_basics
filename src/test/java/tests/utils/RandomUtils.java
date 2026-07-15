package tests.utils;

import java.util.Random;


import static java.lang.String.format;

public class RandomUtils {
public static String getRandomString(int length) {
    String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    StringBuffer result = new StringBuffer();
    Random rnd = new Random();

    for (int i = 0; i < length; i++) {
        result.append(LETTERS.charAt(rnd.nextInt(LETTERS.length())));
    }
    return result.toString();
}

    public static String getRandomEmail() {
        return format("%s%s.ru", getRandomString(5), getRandomString(5));
    }

    public static String getRandomEmailWithoutDomain(){
        return format("%s@%s", getRandomString(5), getRandomString(5));
    }
}
