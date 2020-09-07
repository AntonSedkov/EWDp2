package by.epam.compositetext.comparator.impl;

import by.epam.compositetext.comparator.TextComponentComparator;
import by.epam.compositetext.composite.TextComponent;
import by.epam.compositetext.composite.impl.TextComposite;

public class SortParentByChildQuantity implements TextComponentComparator {

    @Override
    public int compare(TextComponent sentenceOne, TextComponent sentenceTwo) {
        if (((TextComposite) sentenceOne).getComponents().size() > ((TextComposite) sentenceTwo).getComponents().size()) {
            return 1;
        } else if (((TextComposite) sentenceOne).getComponents().size() < ((TextComposite) sentenceTwo).getComponents().size()) {
            return -1;
        } else {
            return 0;
        }
    }

}