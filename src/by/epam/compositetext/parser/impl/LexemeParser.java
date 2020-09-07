package by.epam.compositetext.parser.impl;

import by.epam.compositetext.composite.TextComponent;
import by.epam.compositetext.composite.impl.TextComposite;
import by.epam.compositetext.composite.impl.TextType;
import by.epam.compositetext.parser.BaseHandler;

import java.util.ArrayList;
import java.util.List;

public class LexemeParser implements BaseHandler {
    private static final LexemeParser INSTANCE = new LexemeParser();
    private final BaseHandler successorWord = WordParser.getInstance();
    private final BaseHandler successorSymbol = SymbolParser.getInstance();
    private static final String LEXEME_REGEXP = "\\s";
    private static final int QUANTITY_SYMBOL = 1;
    private static final String WORD_REGEXP = "[^\\W_0-9]{2,}";
    private static final String EXPRESSION_REGEXP = "[\\d+\\-*\\/()]{3,}";
    private static final String PUNKT = "\\p{Punct}{1}";
    private static final String ANY_WORD = ".*";
    private static final String PUNKT_START = PUNKT + ANY_WORD;
    private static final String PUNKT_END = ANY_WORD + PUNKT;

    private LexemeParser() {
    }

    public static LexemeParser getInstance() {
        return INSTANCE;
    }

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
                String res = "One"; //INTERPRETATOR(splitLexeme)
                TextComponent word = new TextComposite(TextType.WORD);
                List<TextComponent> chars = successorWord.parseComponent(res);
                for (TextComponent character : chars) {
                    word.add(character);
                }
                wordsAndPunct.add(word);
                continue;
            }
            String punkt = "";
            String punkt2 = "";
            String word = "";
            if (splitLexeme.matches(PUNKT_START)) {
                punkt = splitLexeme.substring(0, 1);
                word = splitLexeme.substring(1);
                List<TextComponent> punktComponent = successorSymbol.parseComponent(punkt);
                wordsAndPunct.addAll(punktComponent);
            }
            if (splitLexeme.matches(PUNKT_END)) {
                int preEnd = splitLexeme.length() - 1;
                punkt2 = splitLexeme.substring(preEnd);
                word = !word.isBlank() ? word.substring(0, preEnd - 1) : splitLexeme.substring(0, preEnd);
            }
            if (!word.isBlank()) {
                TextComponent wordComponent = new TextComposite(TextType.WORD);
                List<TextComponent> chars = successorWord.parseComponent(word);
                for (TextComponent character : chars) {
                    wordComponent.add(character);
                }
                wordsAndPunct.add(wordComponent);
                List<TextComponent> punktComponent2 = successorSymbol.parseComponent(punkt2);
                wordsAndPunct.addAll(punktComponent2);
            }
        }
        return wordsAndPunct;
    }

    public List<TextComponent> parseLexemes(String text) {
        List<TextComponent> lexemes = new ArrayList<>();
        String[] splitLexemes = text.split(LEXEME_REGEXP);
        for (String splitLexeme : splitLexemes) {
            TextComponent lexeme = new TextComposite(TextType.LEXEME);
            List<TextComponent> words = successorWord.parseComponent(splitLexeme);
            for (TextComponent word : words) {
                lexeme.add(word);
            }
            lexemes.add(lexeme);
        }
        return lexemes;
    }

    public List<TextComponent> parseWords(String text) {
        List<TextComponent> lexemes = new ArrayList<>();
        String[] splitLexemes = text.split(LEXEME_REGEXP);
        for (String splitLexeme : splitLexemes) {
            if (splitLexeme.matches(WORD_REGEXP)) {
                TextComponent word = new TextComposite(TextType.WORD);
                List<TextComponent> chars = successorWord.parseComponent(splitLexeme);
                for (TextComponent character : chars) {
                    word.add(character);
                }
                lexemes.add(word);
            }
        }
        return lexemes;
    }

}