package test.epam.compositetext.service;

import by.epam.compositetext.comparator.TextComponentComparator;
import by.epam.compositetext.comparator.impl.SortByUpToDownIncomingSymbolComparator;
import by.epam.compositetext.comparator.impl.SortParentByChildSizeComparator;
import by.epam.compositetext.composite.TextComponent;
import by.epam.compositetext.parser.impl.LexemeParser;
import by.epam.compositetext.parser.impl.ParagraphParser;
import by.epam.compositetext.service.CompositeService;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class CompositeServiceTest {
    CompositeService service = CompositeService.getInstance();
    TextComponentComparator comparator;
    String text;
    List<TextComponent> components;
    List<TextComponent> sortedComponents;
    String actual;
    String expected;

    @Test
    public void testSortParagraphsBySentenceQuantity() {
        text = "Bye.\n" +
                "It is a long established fact that a reader. The point of using as English.\n" +
                "It has survive. " +
                "It was popularised eti. " +
                "Letraset sheets containing.\n" +
                "It be of a page when looking at its layout.\n";
        components = ParagraphParser.getInstance().parseComponent(text);
        comparator = new SortParentByChildSizeComparator();
        sortedComponents = service.sortComponents(components, comparator);
        actual = sortedComponents.get(1).toString();
        expected = " It be of a page when looking at its layout.";
        assertEquals(actual, expected);
    }

    @Test
    public void testSortParagraphsBySentenceQuantityNotEqual() {
        text = "Bye.\n" +
                "It is a long established fact that a reader. The point of using as English.\n" +
                "It has survive. " +
                "It was popularised eti. " +
                "Letraset sheets containing.\n" +
                "It be of a page when looking at its layout.\n";
        components = ParagraphParser.getInstance().parseComponent(text);
        comparator = new SortParentByChildSizeComparator();
        sortedComponents = service.sortComponents(components, comparator);
        assertNotEquals(components, sortedComponents);
    }

    @Test
    public void testSortSentenceByWordLength() {
        text = "It wizard popularised eti.";
        components = LexemeParser.getInstance().parseWords(text);
        comparator = new SortParentByChildSizeComparator();
        sortedComponents = service.sortComponents(components, comparator);
        actual = sortedComponents.toString();
        expected = "[It, eti, wizard, popularised]";
        assertEquals(actual, expected);
    }

    @Test
    public void testSortSentenceByLexemeLength() {
        text = "(It) wizard___) - popularised eti";
        components = LexemeParser.getInstance().parseLexemes(text);
        comparator = new SortParentByChildSizeComparator();
        sortedComponents = service.sortComponents(components, comparator);
        actual = sortedComponents.toString();
        expected = "[-, eti, (It), wizard___), popularised]";
        assertEquals(actual, expected);
    }

    @Test
    public void testSortSentenceBySymbolIn() {
        text = "It be of jooney opage aheno* looooking ato itoos layout foooost";
        components = LexemeParser.getInstance().parseLexemes(text);
        comparator = new SortByUpToDownIncomingSymbolComparator('o');
        sortedComponents = service.sortComponents(components, comparator);
        actual = sortedComponents.toString();
        expected = "[foooost, looooking, itoos, jooney, aheno*, ato, layout, of, opage, be, It]";
        assertEquals(actual, expected);
    }

    @Test
    public void testSortSentenceBySymbolInBySortComponent() {
        text = "It be of jooney opage aheno* looooking ato itoos layout foooost";
        components = LexemeParser.getInstance().parseComponent(text);
        comparator = new SortByUpToDownIncomingSymbolComparator('o');
        sortedComponents = service.sortComponents(components, comparator);
        actual = sortedComponents.toString();
        expected = "[foooost, looooking, itoos, jooney, aheno, ato, layout, of, opage, *, be, It]";
        assertEquals(actual, expected);
    }

}