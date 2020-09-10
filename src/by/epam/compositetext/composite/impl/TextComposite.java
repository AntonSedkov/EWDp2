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
    private static final String STRING_JOINER_DELIMITER = "";

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
        StringJoiner st = new StringJoiner(STRING_JOINER_DELIMITER);
        for (TextComponent component : components) {
            TextType currentType = component.getCurrentType();
            if (TextType.PARAGRAPH.equals(currentType)) {
                st.add(NEW_LINE_AND_TAB);
            }
            if (TextType.LEXEME.equals(component.getCurrentType())) {
                st.add(SPACE);
            }
            st.add(component.toString());
            if (TextType.SENTENCE.equals(component.getCurrentType())) {
                st.add(DOT);
            }
        }
        return st.toString();
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