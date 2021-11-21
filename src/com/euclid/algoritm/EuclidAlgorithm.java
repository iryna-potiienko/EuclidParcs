package com.euclid.algoritm;

import parcs.AM;
import parcs.AMInfo;

public class EuclidAlgorithm implements AM {

    public static int count = 0;

    @Override
    public void run(AMInfo amInfo) {
        int leftNumber = amInfo.parent.readInt();
        int rightNumber = amInfo.parent.readInt();

        System.out.println("Started to process pair: left:" + leftNumber + " right: " + rightNumber);

        int greatestCommonDividend = gcd(leftNumber, rightNumber);

        amInfo.parent.write(leftNumber);
        amInfo.parent.write(rightNumber);

        amInfo.parent.write(greatestCommonDividend);
    }

    public static int gcd(int a, int b) {
        if (b == 0) {
            count = 0;

            return a;
        }
        count++;

        return gcd(b, a % b);
    }
}
