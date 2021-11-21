package com.euclid;

import com.euclid.model.NumberPair;
import com.euclid.model.TaskWithData;
import parcs.AMInfo;
import parcs.channel;
import parcs.point;
import parcs.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainEuclidParcs {

    private static final int NUMBER_OF_TASKS = 5;


    public static void main(String[] args) throws Exception {
        List<NumberPair> numberPairs = extractNumberPairs();

        int countOfPairsPerTask = numberPairs.size() / NUMBER_OF_TASKS;

        List<List<NumberPair>> partitionsPerWorker = makeChunks(numberPairs, countOfPairsPerTask);

        List<channel> channels = new ArrayList<>();

        long startTime = System.nanoTime();

        for (List<NumberPair> pairs : partitionsPerWorker) {
            TaskWithData taskWithData = createTaskWithData(pairs);

            List<channel> newlyCreatedChannels = executeTask(taskWithData);

            channels.addAll(newlyCreatedChannels);
        }

        for (channel c : channels) {
            int leftNumber = c.readInt();
            int rightNumber = c.readInt();

            int greatestCommonDividend = c.readInt();

            System.out.println("leftNumber: " + leftNumber + " rightNumber: "
                    + rightNumber + " GCD: " + greatestCommonDividend + "\n");
        }

        double totalTime = (double) (System.nanoTime() - startTime) / 1000000000;
        System.out.println("Execution time: " + totalTime);

        System.out.println("Waiting for result...");
    }

    private static TaskWithData createTaskWithData(List<NumberPair> pairs) {
        task curtask = new task();
        curtask.addJarFile("EuclidAlgorithm.jar");

        return new TaskWithData(curtask, pairs);
    }

    private static List<channel> executeTask(TaskWithData task) {
        List<channel> channels = new ArrayList<>();

        for (NumberPair pair : task.getPairs()) {
            AMInfo info = new AMInfo(task.getTask(), null);

            point p = info.createPoint();
            channel c = p.createChannel();

            p.execute("com.euclid.algoritm.EuclidAlgorithm");

            c.write(pair.getLeftNumber());
            c.write(pair.getRightNumber());

            channels.add(c);
        }

        task.getTask().end();

        return channels;
    }

    public static <T> List<List<T>> makeChunks(List<T> bigList, int n) {
        List<List<T>> chunks = new ArrayList<>();

        for (int i = 0; i < bigList.size(); i += n) {
            List<T> chunk = bigList.subList(i, Math.min(bigList.size(), i + n));
            chunks.add(chunk);
        }

        return chunks;
    }

    private static List<NumberPair> extractNumberPairs() throws FileNotFoundException {
        List<NumberPair> numberPairs = new ArrayList<>();

        Scanner sc = new Scanner(new File("input"));

        while (sc.hasNext()) {
            int leftNumber = sc.nextInt();
            int rightNumber = sc.nextInt();

            NumberPair numberPair = new NumberPair(leftNumber, rightNumber);

            numberPairs.add(numberPair);
        }

        return numberPairs;
    }
}
