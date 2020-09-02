package by.epam.compositetext.parser.impl;

import by.epam.compositetext.composite.TextComponent;
import by.epam.compositetext.parser.BaseHandler;

import java.util.ArrayList;
import java.util.List;

public class WordParser implements BaseHandler {
    private final BaseHandler successor = new SymbolParser();
    private static final String ONE_LETTER = "[^\\W_0-9]{1}";

    @Override
    public List<TextComponent> parseComponent(String text) {
        List<TextComponent> leafs = new ArrayList<>();
        String[] symbols = text.split(ONE_LETTER);
        for (String symbol : symbols) {
            List<TextComponent> leaf = successor.parseComponent(symbol);
            leafs.addAll(leaf);
        }
        return leafs;
    }

}