package by.epam.compositetext.interpreter.impl;

import by.epam.compositetext.interpreter.BaseMathExpression;
import by.epam.compositetext.interpreter.Context;

public class TerminalDivide implements BaseMathExpression {

    @Override
    public void interpret(Context context) {
        context.pushValue(context.popValue() / context.popValue());
    }

}