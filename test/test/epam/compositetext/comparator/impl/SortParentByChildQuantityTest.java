package test.epam.compositetext.comparator.impl;

import by.epam.compositetext.comparator.impl.SortParentByChildQuantity;
import by.epam.compositetext.composite.TextComponent;
import by.epam.compositetext.parser.impl.ParagraphParser;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class SortParentByChildQuantityTest {
    SortParentByChildQuantity sortParentByChildQuantity = new SortParentByChildQuantity();
    String text;
    List<TextComponent> data;

    @BeforeMethod
    public void setUp() {
        text = "Bye.\n" +
                "It is a long established fact that a reader. The point of using as English.\n" +
                "It has survive. " +
                "It was popularised eti. " +
                "Letraset sheets containing.\n" +
                "It be of a page when looking at its layout.\n";
        data = ParagraphParser.getInstance().parseComponent(text);
    }

    @Test
    public void testCompareLess() {
        int actual = sortParentByChildQuantity.compare(data.get(1), data.get(2));
        int expected = -1;
        assertEquals(actual, expected);
    }

    @Test
    public void testCompareBigger() {
        int actual = sortParentByChildQuantity.compare(data.get(2), data.get(1));
        int expected = 1;
        assertEquals(actual, expected);
    }

    @Test
    public void testCompareEqual() {
        int actual = sortParentByChildQuantity.compare(data.get(3), data.get(3));
        int expected = 0;
        assertEquals(actual, expected);
    }

}