package com.euclid.generator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class InputDataGenerator {

    private static final int MIN_RANGE = 1;

    private static final int MAX_RANGE = 10000;

    private static final int COUNT_OF_NUMBERS = 1000;


    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("out/input", false));

        for (int i = 0; i < COUNT_OF_NUMBERS; i++) {
            int leftNumber = getRandomNumber();
            int rightNumber = getRandomNumber();

            writer.append(String.valueOf(leftNumber)).append(" ");
            writer.append(String.valueOf(rightNumber)).append(" \n");
        }

        writer.close();
    }

    private static int getRandomNumber() {
        return (int) ((Math.random() * (InputDataGenerator.MAX_RANGE - InputDataGenerator.MIN_RANGE))
                + InputDataGenerator.MIN_RANGE);
    }
}
