package test.epam.compositetext.parser.impl;

import by.epam.compositetext.composite.TextComponent;
import by.epam.compositetext.parser.impl.LexemeParser;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class LexemeParserTest {
    LexemeParser parser = LexemeParser.getInstance();
    String text;
    List<TextComponent> actual;

    @BeforeMethod
    public void setUp() {
        text = "It has survived - not only (five) centuries, but also the leap into 13+1 electronic typesetting, " +
                "remaining 3+5 essentially 6+9*(3-4) unchanged. It was popularised in the 5*(1*2*(3*(4*(5-11+4)-3)-2)-1) with the release " +
                "of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software " +
                "like Aldus PageMaker including versions of Lorem Ipsum.";
        actual = parser.parseComponent(text);
    }

    @Test
    public void testParseComponentSize() {
        int actualSize = actual.size();
        int expectedSize = 59;
        assertEquals(actualSize, expectedSize);
    }

    @Test
    public void testParseComponentNotBlank() {
        String actualElement = actual.get(10).toString();
        assertFalse(actualElement.isBlank());
    }

    @Test
    public void testParseComponentNotNull() {
        TextComponent actualElement = actual.get(23);
        assertNotNull(actualElement);
    }

}