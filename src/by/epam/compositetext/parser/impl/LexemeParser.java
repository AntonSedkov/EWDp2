package by.epam.compositetext.parser.impl;

import by.epam.compositetext.composite.TextComponent;
import by.epam.compositetext.composite.impl.TextComposite;
import by.epam.compositetext.composite.impl.TextType;
import by.epam.compositetext.parser.BaseHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;

public class LexemeParser implements BaseHandler {
    private static Logger logger = LogManager.getLogger(LexemeParser.class);
    private static final LexemeParser INSTANCE = new LexemeParser();
    private final BaseHandler successorWord = WordParser.getInstance();
    private final BaseHandler successorSymbol = SymbolParser.getInstance();
    private static final String LEXEME_REGEXP = "\\s+";
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
                addComponentWord(splitLexeme, wordsAndPunct);
                continue;
            }
            if (splitLexeme.length() == QUANTITY_SYMBOL) {
                addComponentOneSymbol(splitLexeme, wordsAndPunct);
                continue;
            }
            if (splitLexeme.matches(EXPRESSION_REGEXP)) {
                addComponentExpression(splitLexeme, wordsAndPunct);
                continue;
            }
            addComponentWithPunctuation(splitLexeme, wordsAndPunct);
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

    private void addComponentWord(String splitLexeme, List<TextComponent> wordsAndPunct) {
        TextComponent word = new TextComposite(TextType.LEXEME);
        List<TextComponent> chars = successorWord.parseComponent(splitLexeme);
        for (TextComponent character : chars) {
            word.add(character);
        }
        wordsAndPunct.add(word);
    }

    private void addComponentOneSymbol(String splitLexeme, List<TextComponent> wordsAndPunct) {
        TextComponent symbolComponent = new TextComposite(TextType.LEXEME);
        List<TextComponent> leafs = successorSymbol.parseComponent(splitLexeme);
        for (TextComponent leaf : leafs) {
            symbolComponent.add(leaf);
        }
        wordsAndPunct.add(symbolComponent);
    }

    private void addComponentExpression(String splitLexeme, List<TextComponent> wordsAndPunct) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        Object result = null;
        try {
            result = engine.eval(splitLexeme);
        } catch (ScriptException e) {
            logger.error("Wrong script name for ScriptEngineManager. ", e);
        }
        if (result != null) {
            String resultString = result.toString();
            TextComponent word = new TextComposite(TextType.LEXEME);
            List<TextComponent> chars = successorWord.parseComponent(resultString);
            for (TextComponent character : chars) {
                word.add(character);
            }
            wordsAndPunct.add(word);
        }
    }

    private void addComponentWithPunctuation(String splitLexeme, List<TextComponent> wordsAndPunct) {
        String word = "";
        if (splitLexeme.matches(PUNKT_START)) {
            String punkt = splitLexeme.substring(0, 1);
            word = splitLexeme.substring(1);
            TextComponent symbolComponent = new TextComposite(TextType.LEXEME);
            List<TextComponent> symbols = successorSymbol.parseComponent(punkt);
            for (TextComponent symbol : symbols) {
                symbolComponent.add(symbol);
            }
            wordsAndPunct.add(symbolComponent);
        }
        String punkt2 = "";
        if (splitLexeme.matches(PUNKT_END)) {
            int preEnd = splitLexeme.length() - 1;
            punkt2 = splitLexeme.substring(preEnd);
            word = !word.isBlank() ? word.substring(0, preEnd - 1) : splitLexeme.substring(0, preEnd);
        }
        if (!word.isBlank()) {
            TextComponent wordComponent = new TextComposite(TextType.LEXEME);
            List<TextComponent> chars = successorWord.parseComponent(word);
            for (TextComponent character : chars) {
                wordComponent.add(character);
            }
            wordsAndPunct.add(wordComponent);
            if (!punkt2.isBlank()) {
                TextComponent symbolComponent2 = new TextComposite(TextType.LEXEME);
                List<TextComponent> symbols = successorSymbol.parseComponent(punkt2);
                for (TextComponent symbol : symbols) {
                    symbolComponent2.add(symbol);
                }
                wordsAndPunct.add(symbolComponent2);
            }
        }
    }

}