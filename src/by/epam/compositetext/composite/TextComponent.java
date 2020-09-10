package by.epam.compositetext.composite;

import by.epam.compositetext.composite.impl.TextType;
import by.epam.compositetext.exception.ProjectException;

public interface TextComponent {

    TextType getCurrentType();

    boolean add(TextComponent component);

    boolean remove(TextComponent component);

    TextComponent getChild(int index) throws ProjectException;

}