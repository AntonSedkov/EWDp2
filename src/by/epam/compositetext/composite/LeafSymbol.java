package by.epam.compositetext.composite;

import by.epam.compositetext.exception.ProjectException;

public class LeafSymbol implements TextComponent {
    private static final boolean DEFAULT_PARTING = false;
    private static final TextType CURRENT_TYPE = TextType.SYMBOL;
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
        return DEFAULT_PARTING;
    }

    @Override
    public boolean remove(TextComponent component) {
        return DEFAULT_PARTING;
    }

    @Override
    public TextComponent getChild(int index) throws ProjectException {
        throw new ProjectException("This component has not a child");
    }

}