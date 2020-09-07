package by.epam.compositetext.parser.impl;

import by.epam.compositetext.composite.TextComponent;
import by.epam.compositetext.composite.impl.LeafSymbol;
import by.epam.compositetext.parser.BaseHandler;

import java.util.ArrayList;
import java.util.List;

public class SymbolParser implements BaseHandler {
    private static final SymbolParser INSTANCE = new SymbolParser();

    private SymbolParser() {
    }

    public static SymbolParser getInstance() {
        return INSTANCE;
    }

    @Override
    public List<TextComponent> parseComponent(String text) {
        List<TextComponent> character = new ArrayList<>();
        LeafSymbol leaf = new LeafSymbol();
        leaf.setSymbol(text.charAt(0));
        character.add(leaf);
        return character;
    }

}