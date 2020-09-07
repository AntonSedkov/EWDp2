package by.epam.compositetext.service;

import by.epam.compositetext.comparator.TextComponentComparator;
import by.epam.compositetext.composite.TextComponent;

import java.util.List;
import java.util.stream.Collectors;

public class CompositeService {
    private static final CompositeService INSTANCE = new CompositeService();

    private CompositeService() {
    }

    public static CompositeService getInstance() {
        return INSTANCE;
    }

    public List<TextComponent> sortComponents(List<TextComponent> baseList, TextComponentComparator comparator) {
        List<TextComponent> sorted;
        sorted = baseList.stream().sorted(comparator).collect(Collectors.toList());
        return sorted;
    }

}
