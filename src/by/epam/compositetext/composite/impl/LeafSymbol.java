package by.epam.compositetext.composite.impl;

import by.epam.compositetext.composite.TextComponent;
import by.epam.compositetext.exception.ProjectException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LeafSymbol implements TextComponent {
    private static Logger logger = LogManager.getLogger(LeafSymbol.class);
    private static final boolean DEFAULT_PARTING = false;
    private static final TextType CURRENT_TYPE = TextType.SYMBOL;
    private TypeSymbol type;
    private char symbol;

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public String operation() {
        return String.valueOf(symbol);
    }

    @Override
    public boolean add(TextComponent component) {
        logger.warn("Such method is not allowed");
        return DEFAULT_PARTING;
    }

    @Override
    public boolean remove(TextComponent component) {
        logger.warn("Such method is not allowed");
        return DEFAULT_PARTING;
    }

    @Override
    public TextComponent getChild(int index) throws ProjectException {
        logger.warn("Such method is not allowed");
        throw new ProjectException("This component has not a child");
    }

}