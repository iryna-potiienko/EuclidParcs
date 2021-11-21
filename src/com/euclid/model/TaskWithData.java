package com.euclid.model;

import parcs.task;

import java.util.List;

public class TaskWithData {

    private final task task;

    private final List<NumberPair> pairs;

    public TaskWithData(parcs.task task, List<NumberPair> pairs) {
        this.task = task;
        this.pairs = pairs;
    }

    public parcs.task getTask() {
        return task;
    }

    public List<NumberPair> getPairs() {
        return pairs;
    }
}
