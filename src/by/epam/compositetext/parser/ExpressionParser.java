package by.epam.compositetext.parser;

import by.epam.compositetext.composite.TextComponent;
import by.epam.compositetext.composite.TextComposite;
import by.epam.compositetext.composite.TextType;

public class ExpressionParser extends AbstractHandler {
    private final AbstractHandler successor = new SymbolParser();
    private static final String EXPRESSION_REGEXP = "";

    @Override
    public TextComponent handlerRequest(String text) {
        TextComposite composite = new TextComposite();
        composite.setCurrentType(TextType.EXPRESSION);
        String[] expressions = text.split(EXPRESSION_REGEXP);
        for (String expression : expressions) {
            TextComponent component = successor.handlerRequest(expression);
            composite.add(component);
        }
        return composite;
    }

}