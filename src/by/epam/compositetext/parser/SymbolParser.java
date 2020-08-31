package by.epam.compositetext.parser;

import by.epam.compositetext.composite.LeafSymbol;
import by.epam.compositetext.composite.TextComponent;

public class SymbolParser extends AbstractHandler {

    @Override
    public TextComponent handlerRequest(String text) {
        LeafSymbol leaf = new LeafSymbol();
        return leaf;
    }

}