package by.epam.compositetext.parser;

import by.epam.compositetext.composite.TextComponent;

import java.util.List;

public interface BaseHandler {

    List<TextComponent> parseComponent(String text);

}