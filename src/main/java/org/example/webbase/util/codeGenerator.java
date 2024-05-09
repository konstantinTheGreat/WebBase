package org.example.webbase.util;

import java.util.Random;

public class codeGenerator {

    public static int generate() {
        Random number = new Random();
        return number.nextInt(9000) + 1000;
    }
}
