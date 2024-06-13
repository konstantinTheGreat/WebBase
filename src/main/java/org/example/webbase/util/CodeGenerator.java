package org.example.webbase.util;

import java.util.Random;

public class CodeGenerator {

    public static int generate() {
        Random number = new Random(); //make private static
        return number.nextInt(9000) + 1000;
    }
}
