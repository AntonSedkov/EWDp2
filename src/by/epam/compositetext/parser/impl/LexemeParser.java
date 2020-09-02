package by.epam.compositetext.parser.impl;

import by.epam.compositetext.composite.TextComponent;
import by.epam.compositetext.composite.impl.TextComposite;
import by.epam.compositetext.composite.impl.TextType;
import by.epam.compositetext.parser.BaseHandler;

import java.util.ArrayList;
import java.util.List;

public class LexemeParser implements BaseHandler {
    private BaseHandler successorWord = new WordParser();
    private BaseHandler successorSymbol = new SymbolParser();
    private static final String LEXEME_REGEXP = "\\b[^ ]\\S*[^ ]\\b";
    private static final int QUANTITY_SYMBOL = 1;
    private static final String WORD_REGEXP = "[^\\W_0-9]{2,}";
    private static final String EXPRESSION_REGEXP = "[\\d+\\-*\\/()]{3,}";
    private static final String PUNKT_REGEXP = "\\p{Punkt}";

    @Override
    public List<TextComponent> parseComponent(String text) {
        List<TextComponent> wordsAndPunct = new ArrayList<>();
        String[] splitLexemes = text.split(LEXEME_REGEXP);
        for (String splitLexeme : splitLexemes) {
            if (splitLexeme.matches(WORD_REGEXP)) {
                TextComponent word = new TextComposite(TextType.WORD);
                List<TextComponent> chars = successorWord.parseComponent(splitLexeme);
                for (TextComponent character : chars) {
                    word.add(character);
                }
                wordsAndPunct.add(word);
                continue;
            }
            if (splitLexeme.length() == QUANTITY_SYMBOL) {
                List<TextComponent> leaf = successorSymbol.parseComponent(splitLexeme);
                wordsAndPunct.addAll(leaf);
                continue;
            }
            if (splitLexeme.matches(EXPRESSION_REGEXP)) {
                String res = ""; //INTERPRETATOR(splitLexeme)
                TextComponent word = new TextComposite(TextType.WORD);
                List<TextComponent> chars = successorWord.parseComponent(res);
                for (TextComponent character : chars) {
                    word.add(character);
                }
                wordsAndPunct.add(word);
                continue;
            }
            String punkt = "";
            String word = "";
            if (splitLexeme.startsWith(PUNKT_REGEXP)) {
                punkt = splitLexeme.substring(0, 1);
                word = splitLexeme.substring(1);
            }
            if (splitLexeme.endsWith(PUNKT_REGEXP)) {
                int preEnd = splitLexeme.length() - 1;
                punkt = splitLexeme.substring(preEnd);
                word = splitLexeme.substring(0, preEnd);
            }
            if (!punkt.isBlank() && !word.isBlank()) {
                List<TextComponent> punktComponent = successorSymbol.parseComponent(punkt);
                wordsAndPunct.addAll(punktComponent);
                TextComponent wordComponent = new TextComposite(TextType.WORD);
                List<TextComponent> chars = successorWord.parseComponent(word);
                for (TextComponent character : chars) {
                    wordComponent.add(character);
                }
                wordsAndPunct.add(wordComponent);
            }
        }
        return wordsAndPunct;
    }

}