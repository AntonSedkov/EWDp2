package test.epam.compositetext.parser.impl;

import by.epam.compositetext.composite.TextComponent;
import by.epam.compositetext.parser.impl.ParagraphParser;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class ParagraphParserTest {

    ParagraphParser parser = ParagraphParser.getInstance();
    String text;

    @BeforeMethod
    public void setUp() {
        text = "It has survived - not only (five) centuries, but also the leap into 13+1 electronic typesetting, " +
                "remaining 3+5 essentially 6+9*(3-4) unchanged. It was popularised in the 5*(1*2*(3*(4*(5-11+4)-3)-2)-1) with the release " +
                "of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software " +
                "like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                "It is a long established fact that a reader will be distracted by the readable content of a page when " +
                "looking at its layout. The point of using (71-(2*11*(3*(2-1/2*2)-2)-10/2))*11 Ipsum is that it has a more-or-less " +
                "normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English.\n" +
                "It is a (-5+1/2*(2+5*2-1))*1200 established fact that a reader will be of a page when looking at its layout.\n" +
                "Bye.";
    }

    @Test
    public void testParseComponent() {
        List<TextComponent> actual = parser.parseComponent(text);
        int sizeActual = actual.size();
        int sizeExpected = 4;
        assertEquals(sizeActual, sizeExpected);
    }

}