package by.epam.compositetext.parser;

import by.epam.compositetext.composite.TextComponent;
import by.epam.compositetext.composite.TextComposite;
import by.epam.compositetext.composite.TextType;

public class ParagraphParser extends AbstractHandler {
    private final AbstractHandler successor = new SentenceParser();
    private static final String PARAGRAPH_REGEXP = "[\t]|[ ]{4}";

    @Override
    public TextComponent handlerRequest(String text) {
        TextComposite composite = new TextComposite();
        composite.setCurrentType(TextType.PARAGRAPH);
        String[] paragraphs = text.split(PARAGRAPH_REGEXP);
        for (String paragraph : paragraphs) {
            TextComponent component = successor.handlerRequest(paragraph);
            composite.add(component);
        }
        return composite;
    }

}