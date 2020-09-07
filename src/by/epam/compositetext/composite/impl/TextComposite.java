package by.epam.compositetext.composite.impl;

import by.epam.compositetext.composite.TextComponent;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {
    private TextType currentType;
    private List<TextComponent> components = new ArrayList<>();
    private static final String SPACE = " ";
    private static final String NEW_LINE_AND_TAB = "\n\t";

    public TextComposite(TextType currentType) {
        this.currentType = currentType;
    }

    public TextType getCurrentType() {
        return currentType;
    }

    public List<TextComponent> getComponents() {
        return components;
    }

    public void setComponents(List<TextComponent> components) {
        this.components = components;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (TextComponent component : components) {
            if (TextType.PARAGRAPH.equals(component.getCurrentType())) {
                result.append(NEW_LINE_AND_TAB);
            }
            if (TextType.SENTENCE.equals(component.getCurrentType()) || TextType.LEXEME.equals(component.getCurrentType())) {
                result.append(SPACE);
            }
            result.append(component.toString());
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