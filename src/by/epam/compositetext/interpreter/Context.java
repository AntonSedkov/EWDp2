package by.epam.compositetext.interpreter;

import java.util.ArrayDeque;

public class Context {
    private ArrayDeque<Integer> expressionValues = new ArrayDeque<>();

    public Integer popValue() {
        return expressionValues.pop();
    }

    public void pushValue(Integer value) {
        this.expressionValues.push(value);
    }

}