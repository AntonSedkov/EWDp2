package by.epam.compositetext.composite;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class TextComposite implements TextComponent {
    private TextType currentType;
    private List<TextComponent> components = new ArrayList<>();

    public TextType getCurrentType() {
        return currentType;
    }

    public void setCurrentType(TextType currentType) {
        this.currentType = currentType;
    }

    @Override
    public String operation() {
        StringJoiner result = new StringJoiner(" ");
        for (TextComponent component : components) {
            result.add(component.operation());
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
        return components.get(index);
    }

}