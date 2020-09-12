package by.epam.compositetext.parser.impl;

import by.epam.compositetext.composite.TextComponent;
import by.epam.compositetext.composite.impl.TextComposite;
import by.epam.compositetext.composite.impl.TextType;
import by.epam.compositetext.parser.BaseHandler;

import java.util.ArrayList;
import java.util.List;

public class TextParser implements BaseHandler {
    private static final TextParser INSTANCE = new TextParser();
    private final BaseHandler successor = ParagraphParser.getInstance();

    private TextParser() {
    }

    public static TextParser getInstance() {
        return INSTANCE;
    }

    @Override
    public List<TextComponent> parseComponent(String text) {
        List<TextComponent> resultText = new ArrayList<>();
        TextComponent texts = new TextComposite(TextType.TEXT);
        List<TextComponent> paragraphs = successor.parseComponent(text);
        for (TextComponent paragraph : paragraphs) {
            texts.add(paragraph);
        }
        resultText.add(texts);
        return resultText;
    }

}