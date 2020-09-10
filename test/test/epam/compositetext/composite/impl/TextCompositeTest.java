package test.epam.compositetext.composite.impl;

import by.epam.compositetext.composite.TextComponent;
import by.epam.compositetext.parser.impl.TextParser;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class TextCompositeTest {
    TextParser parser = TextParser.getInstance();
    String text;
    String expectedText;

    @BeforeMethod
    public void setUp() {
        text = "It has survived - not only (five) centuries, but also the leap into 13+1 electronic typesetting, " +
                "remaining 3+5 essentially 6+9*(3-4) unchanged. It was popularised in the 5*(1*2*(3*(4*(5-11+4)-3)-2)-1) with the release " +
                "of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software " +
                "like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                "It is a long established fact that a reader will be distracted by the readable content of a page when " +
                "looking at its layout. The point of using (71-(2*11*(3*(2-1/2*2)-2)-10/2))*11 Ipsum is that it has a more or less " +
                "normal distribution of letters, as opposed to using (Content here), making it look like readable English.\n" +
                "It is a (-5+1/2*(2+5*2-1))*1200 established fact that a reader will be of a page when looking at its layout.\n" +
                "Bye.";
        expectedText = "\n\t It has survived - not only ( five ) centuries , but also the leap into One electronic typesetting , " +
                "remaining One essentially One unchanged. It was popularised in the One with the release " +
                "of Letraset sheets containing Lorem Ipsum passages , and more recently with desktop publishing software " +
                "like Aldus PageMaker including versions of Lorem Ipsum.\n\t " +
                "It is a long established fact that a reader will be distracted by the readable content of a page when " +
                "looking at its layout. The point of using One Ipsum is that it has a more or less " +
                "normal distribution of letters , as opposed to using ( Content here) , making it look like readable English.\n\t " +
                "It is a One established fact that a reader will be of a page when looking at its layout.\n\t " +
                "Bye.";
    }

    @Test
    public void testTestToString() {
        List<TextComponent> components = parser.parseComponent(text);
        String actual = components.get(0).toString();
        assertEquals(actual, expectedText);
    }

}