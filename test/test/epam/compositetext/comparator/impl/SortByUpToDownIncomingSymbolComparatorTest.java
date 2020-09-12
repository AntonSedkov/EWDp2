package test.epam.compositetext.comparator.impl;

import by.epam.compositetext.comparator.impl.SortByUpToDownIncomingSymbolComparator;
import by.epam.compositetext.composite.TextComponent;
import by.epam.compositetext.parser.impl.LexemeParser;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SortByUpToDownIncomingSymbolComparatorTest {
    SortByUpToDownIncomingSymbolComparator comparator;
    String text;
    List<TextComponent> data;

    @BeforeMethod
    public void setUp() {
        comparator = new SortByUpToDownIncomingSymbolComparator('o');
        text = "It be of jooney opage aheno, looooking ato itoos layout foooost.";
    }

    @Test
    public void testCompareBigger() {
        data = LexemeParser.getInstance().parseLexemes(text);
        int actual = comparator.compare(data.get(6), data.get(4));
        int expected = -1;
        assertEquals(actual, expected);
    }

    @Test
    public void testCompareLess() {
        data = LexemeParser.getInstance().parseComponent(text);
        int actual = comparator.compare(data.get(4), data.get(7));
        int expected = 1;
        assertEquals(actual, expected);
    }

    @Test
    public void testCompareSecondComparatorAfterEqual() {
        data = LexemeParser.getInstance().parseWords(text);
        int actual = comparator.compare(data.get(2), data.get(7));
        boolean actualTest = actual > 0;
        assertTrue(actualTest);
    }

}