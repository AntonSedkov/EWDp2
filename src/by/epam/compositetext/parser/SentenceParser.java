package by.epam.compositetext.parser;

import by.epam.compositetext.composite.TextComponent;
import by.epam.compositetext.composite.TextComposite;
import by.epam.compositetext.composite.TextType;

public class SentenceParser extends AbstractHandler {
    private final AbstractHandler successor = new LexemeParser();
    private static final String SENTENCE_REGEXP = "([А-ЯA-Z](|[^?!.\\(]|\\([^\\)]*\\))*[.?!])";

    @Override
    public TextComponent handlerRequest(String text) {
        TextComposite composite = new TextComposite();
        composite.setCurrentType(TextType.SENTENCE);
        String[] sentences = text.split(SENTENCE_REGEXP);
        System.out.println("sent " + sentences.length);
        for (String sentence : sentences) {
            TextComponent component = successor.handlerRequest(sentence);
            composite.add(component);
        }
        return composite;
    }

}