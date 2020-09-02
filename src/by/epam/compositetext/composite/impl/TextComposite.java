package by.epam.compositetext.composite.impl;

import by.epam.compositetext.composite.TextComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class TextComposite implements TextComponent {
    private TextType currentType;
    private List<TextComponent> components = new ArrayList<>();
    private static final String SPACE = " ";
    private static final String NEW_LINE_AND_TAB = "\n\t";
    private static final String DOT = ".";

    public TextComposite(TextType currentType) {
        this.currentType = currentType;
    }

    public TextType getCurrentType() {
        return currentType;
    }

    @Override
    public String operation() {
        StringJoiner result = new StringJoiner(SPACE);
        String delimiter = SPACE;
        switch (currentType) {
            case PARAGRAPH -> {
                for (TextComponent component : components) {
                    delimiter = NEW_LINE_AND_TAB;
                    result.add(component.operation()).add(delimiter);
                }
            }
            case SENTENCE -> {
                for (TextComponent component : components) {
                    delimiter = DOT;
                    result.add(component.operation()).add(delimiter);
                }
            }
            case LEXEME, EXPRESSION -> {
                for (TextComponent component : components) {
                    result.add(component.operation()).add(delimiter);
                }
            }
            case SYMBOL -> {
                for (TextComponent component : components) {
                    result.add(component.operation());
                }
            }
        }
        return result.toString();
    }

    @Override
    public boolean add(TextComponent component) {
        return components.add(component);
    }

    @Override
    public boolean remove(TextComponent component) {
        return components.remove(component);
    }

    @Override
    public TextComponent getChild(int index) {
        TextComponent textComposite = new TextComposite(TextType.TEXT);
        if (index >= 0 && index < components.size()) {
            textComposite = components.get(index);
        }
        return textComposite;
    }

}