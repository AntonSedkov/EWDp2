package by.epam.compositetext.comparator.impl;

import by.epam.compositetext.comparator.TextComponentComparator;
import by.epam.compositetext.composite.TextComponent;
import by.epam.compositetext.composite.impl.TextComposite;

import java.util.List;

public class SortByUpToDownIncomingSymbolComparator implements TextComponentComparator {
    private final String symbol;

    public SortByUpToDownIncomingSymbolComparator(char symbol) {
        this.symbol = String.valueOf(symbol);
    }

    //suitable for all lexeme parse methods (component, words, lexemes)
    @Override
    public int compare(TextComponent lexemeOne, TextComponent lexemeTwo) {
        if (countQuantitySymbolInLexeme((TextComposite) lexemeOne)
                > countQuantitySymbolInLexeme((TextComposite) lexemeTwo)) {
            return -1;
        } else if (countQuantitySymbolInLexeme((TextComposite) lexemeOne)
                < countQuantitySymbolInLexeme((TextComposite) lexemeTwo)) {
            return 1;
        } else {
            return lexemeOne.toString().compareToIgnoreCase(lexemeTwo.toString());
        }
    }

    private int countQuantitySymbolInLexeme(TextComposite lexeme) {
        int result = 0;
        List<TextComponent> characters = lexeme.getComponents();
        for (TextComponent character : characters) {
            if (character.toString().equalsIgnoreCase(symbol)) {
                result++;
            }
        }
        return result;
    }

}