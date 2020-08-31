package by.epam.compositetext.parser;

import by.epam.compositetext.composite.TextComponent;

public abstract class AbstractHandler {

    public abstract TextComponent handlerRequest(String text);

}