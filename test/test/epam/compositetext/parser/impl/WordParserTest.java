package test.epam.compositetext.parser.impl;

import by.epam.compositetext.composite.TextComponent;
import by.epam.compositetext.parser.impl.WordParser;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class WordParserTest {
    WordParser parser = WordParser.getInstance();
    String text;
    List<TextComponent> actual;

    @BeforeMethod
    public void setUp() {
        text = "Lorem";
        actual = parser.parseComponent(text);
    }

    @Test
    public void testParseComponentSize() {
        int actualSize = actual.size();
        int expectedSize = 5;
        assertEquals(actualSize, expectedSize);
    }

    @Test
    public void testParseComponentElement() {
        String actualSymbol = actual.get(0).toString();
        String expectedSymbol = "L";
        assertEquals(actualSymbol, expectedSymbol);
    }

}