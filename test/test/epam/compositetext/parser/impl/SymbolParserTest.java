package test.epam.compositetext.parser.impl;

import by.epam.compositetext.composite.TextComponent;
import by.epam.compositetext.parser.impl.SymbolParser;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class SymbolParserTest {
    SymbolParser parser = SymbolParser.getInstance();
    String text;

    @BeforeMethod
    public void setUp() {
        text = ".";
    }

    @Test
    public void testParseComponentSize() {
        List<TextComponent> actual = parser.parseComponent(text);
        int actualSize = actual.size();
        int expectedSize = 1;
        assertEquals(actualSize, expectedSize);
    }

    @Test
    public void testParseComponentElement() {
        List<TextComponent> actual = parser.parseComponent(text);
        String actualSymbol = actual.get(0).toString();
        String expectedSymbol = text;
        assertEquals(actualSymbol, expectedSymbol);
    }

}