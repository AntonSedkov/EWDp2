package by.epam.compositetext.interpreter.impl;

import by.epam.compositetext.interpreter.BaseMathExpression;
import by.epam.compositetext.interpreter.Context;

public class NonterminalNumber implements BaseMathExpression {
    private int number;

    public NonterminalNumber(int number) {
        this.number = number;
    }

    @Override
    public void interpret(Context context) {
        context.pushValue(number);
    }

}