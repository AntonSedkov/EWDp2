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

    //Exspression: 2-3-4*6/3*5+7-3*5 parse: check i0 i2
    /*
     * Queue number = new Queue;
     * Queue terminal = new Queue;
     * flag = true;
     * int start = 0;
     * int end = length -1;
    * while (start<end){
    * int indexStart = expression.matches("[*\\/]{1}")!=null : expression.matches("[*\\/]{1}") ? start+1;
    * if(indexStart!==null){
    * terminal.add(expr[indexStart]);
    * number.add(expr[indexStart-1]);
    * number.add(expr[indexStart+1]);
    * indexNext = indexStart+2;
    * while(indexNext<end){
    * if(expr[indexNext].matches("[*\\/]{1}")){
    * terminal.add(expr[indexNext]);
     * number.add(expr[indexNext+1]);
     * indexNext=indexNext+2;
    * }
    * indexPrevious = indexStart-2;
    * while(indexPrevious>start){
    * terminal.add(expr[indexPrevious]);
     * number.add(expr[indexPrevious-1]);
     * indexPrevious =indexPrevious-2;
    * }
    * start=indexNext;
    * }
    * */

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