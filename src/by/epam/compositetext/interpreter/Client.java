package by.epam.compositetext.interpreter;

import by.epam.compositetext.interpreter.impl.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Client {
    private ArrayList<BaseMathExpression> mathExpression;

    public Client(String expression) {
        mathExpression = new ArrayList<>();
        parse(expression);
    }

    private void parse(String expression) {
        for (String lexeme : expression.split("\\p{Blank}+")) {
            if (lexeme.isEmpty()) {
                continue;
            }
            char temp = lexeme.charAt(0);
            switch (temp) {
                case '+':
                    mathExpression.add(new TerminalPlus());
                    break;
                case '-':
                    mathExpression.add(new TerminalMinus());
                    break;
                case '*':
                    mathExpression.add(new TerminalMultiply());
                    break;
                case '/':
                    mathExpression.add(new TerminalDivide());
                    break;
                default:
                    Scanner scanner = new Scanner(lexeme);
                    if (scanner.hasNextInt()) {
                        mathExpression.add(new NonterminalNumber(scanner.nextInt()));
                    }
            }
        }
    }

    public Number calculate() {
        Context context = new Context();
        for (BaseMathExpression terminal : mathExpression) {
            terminal.interpret(context);
        }
        return context.popValue();
    }


}