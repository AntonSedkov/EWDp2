package by.epam.compositetext.parser.impl;

import by.epam.compositetext.composite.TextComponent;
import by.epam.compositetext.parser.BaseHandler;

import java.util.ArrayList;
import java.util.List;

public class WordParser implements BaseHandler {
    private static final WordParser INSTANCE = new WordParser();
    private final BaseHandler successor = SymbolParser.getInstance();

    private WordParser() {
    }

    public static WordParser getInstance() {
        return INSTANCE;
    }

    @Override
    public List<TextComponent> parseComponent(String text) {
        List<TextComponent> leafs = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            List<TextComponent> leaf = successor.parseComponent(text.substring(i, i + 1));
            leafs.addAll(leaf);
        }
        return leafs;
    }

}