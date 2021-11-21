package com.euclid.model;

import java.io.Serializable;

public class NumberPair implements Serializable {

    private final int leftNumber;

    private final int rightNumber;

    public NumberPair(int leftNumber, int rightNumber) {
        this.leftNumber = leftNumber;
        this.rightNumber = rightNumber;
    }

    public int getLeftNumber() {
        return leftNumber;
    }

    public int getRightNumber() {
        return rightNumber;
    }
}
