package by.epam.compositetext.parser.impl;

import by.epam.compositetext.composite.TextComponent;
import by.epam.compositetext.composite.impl.TextComposite;
import by.epam.compositetext.composite.impl.TextType;
import by.epam.compositetext.parser.BaseHandler;

import java.util.ArrayList;
import java.util.List;

public class ParagraphParser implements BaseHandler {
    private final BaseHandler successor = new SentenceParser();
    private static final String PARAGRAPH_REGEXP = "[\t]|[ ]{4}";

    @Override
    public List<TextComponent> parseComponent(String text) {
        List<TextComponent> paragraphs = new ArrayList<>();
        String[] splitParagraphs = text.split(PARAGRAPH_REGEXP);
        for (String splitParagraph : splitParagraphs) {
            TextComponent paragraph = new TextComposite(TextType.SENTENCE);
            List<TextComponent> sentences = successor.parseComponent(splitParagraph);
            for (TextComponent sentence : sentences) {
                paragraph.add(sentence);
            }
            paragraphs.add(paragraph);
        }
        return paragraphs;
    }

}