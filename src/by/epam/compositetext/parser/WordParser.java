package by.epam.compositetext.parser;

import by.epam.compositetext.composite.TextComponent;
import by.epam.compositetext.composite.TextComposite;
import by.epam.compositetext.composite.TextType;

public class WordParser extends AbstractHandler {
    private final AbstractHandler successor = new SymbolParser();
    private static final String WORD_REGEXP = "\\\\W+";

    @Override
    public TextComponent handlerRequest(String text) {
        TextComposite composite = new TextComposite();
        composite.setCurrentType(TextType.WORD);
        String[] words = text.split(WORD_REGEXP);
        System.out.println("word " + words.length);
        for (String word : words) {
            TextComponent component = successor.handlerRequest(word);
            composite.add(component);
        }
        return composite;
    }

}