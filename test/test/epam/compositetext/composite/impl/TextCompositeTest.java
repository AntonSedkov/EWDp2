package test.epam.compositetext.composite.impl;

import by.epam.compositetext.composite.TextComponent;
import by.epam.compositetext.composite.impl.TextComposite;
import by.epam.compositetext.composite.impl.TextType;
import by.epam.compositetext.parser.impl.ParagraphParser;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class TextCompositeTest {

    ParagraphParser parser = ParagraphParser.getInstance();
    String text;

    @BeforeMethod
    public void setUp() {
        text = "It has survived.";
    }

    @Test
    public void testTestToString() {
        List<TextComponent> components = parser.parseComponent(text);
        String actual = components.get(0).toString();
        assertEquals(actual, text);
    }
}