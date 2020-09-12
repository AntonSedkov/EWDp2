package by.epam.compositetext.comparator.impl;

import by.epam.compositetext.comparator.TextComponentComparator;
import by.epam.compositetext.composite.TextComponent;
import by.epam.compositetext.composite.impl.TextComposite;

public class SortParentByChildSizeComparator implements TextComponentComparator {

    //it suits for sort Paragraph by sentence quantity, Sentence by word length, Sentence by lexeme length
    @Override
    public int compare(TextComponent sentenceOne, TextComponent sentenceTwo) {
        return Integer.compare(((TextComposite) sentenceOne).getComponents().size(),
                ((TextComposite) sentenceTwo).getComponents().size());
    }

}