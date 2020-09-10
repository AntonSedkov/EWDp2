package test.epam.compositetext.composite.impl;

import by.epam.compositetext.composite.impl.LeafSymbol;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LeafSymbolTest {

    @Test
    public void testTestToString() {
        String expected = "_";
        LeafSymbol leafSymbol = new LeafSymbol();
        leafSymbol.setSymbol('_');
        String actual = leafSymbol.toString();
        assertEquals(actual, expected);
    }

}