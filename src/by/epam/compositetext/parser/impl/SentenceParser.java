package by.epam.compositetext.parser.impl;

import by.epam.compositetext.composite.TextComponent;
import by.epam.compositetext.composite.impl.TextComposite;
import by.epam.compositetext.composite.impl.TextType;
import by.epam.compositetext.parser.BaseHandler;

import java.util.ArrayList;
import java.util.List;

public class SentenceParser implements BaseHandler {
    private static final SentenceParser INSTANCE = new SentenceParser();
    private final BaseHandler successor = LexemeParser.getInstance();
    private static final String SENTENCE_REGEXP = "([А-ЯA-Z](|[^?!.\\(]|\\([^\\)]*\\))*[.?!])";

    private SentenceParser() {
    }

    public static SentenceParser getInstance() {
        return INSTANCE;
    }

    @Override
    public List<TextComponent> parseComponent(String text) {
        List<TextComponent> sentences = new ArrayList<>();
        String[] splitSentences = text.split(SENTENCE_REGEXP);
        for (String splitSentence : splitSentences) {
            TextComponent sentence = new TextComposite(TextType.LEXEME);
            List<TextComponent> lexemes = successor.parseComponent(splitSentence);
            for (TextComponent lexeme : lexemes) {
                sentence.add(lexeme);
            }
            sentences.add(sentence);
        }
        return sentences;
    }

}