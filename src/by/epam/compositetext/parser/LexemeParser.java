package by.epam.compositetext.parser;

import by.epam.compositetext.composite.TextComponent;
import by.epam.compositetext.composite.TextComposite;
import by.epam.compositetext.composite.TextType;

public class LexemeParser extends AbstractHandler {
    private final AbstractHandler successor = new WordParser();
    private static final String LEXEME_REGEXP = "";

    @Override
    public TextComponent handlerRequest(String text) {
        TextComposite composite = new TextComposite();
        composite.setCurrentType(TextType.LEXEME);
        String[] lexemes = text.split(LEXEME_REGEXP);
        System.out.println("lex " + lexemes.length);
        for (String lexeme : lexemes) {
            TextComponent component = successor.handlerRequest(lexeme);
            composite.add(component);
        }
        return composite;
    }

}