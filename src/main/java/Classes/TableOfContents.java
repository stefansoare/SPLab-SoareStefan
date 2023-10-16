package Classes;

import java.util.ArrayList;
import java.util.List;

public class TableOfContents implements Element {
    private List<Element> elements = new ArrayList<>();

    @Override
    public void print() {
        for (Element element : elements) {
            element.print();
        }
    }

    @Override
    public void add(Element element) {
        elements.add(element);
    }

    @Override
    public void remove(Element element) {
        elements.remove(element);
    }

    @Override
    public Element get(int index) {
        if (index >= 0 && index < elements.size()) {
            return elements.get(index);
        }
        return null; // Or throw an exception to handle invalid index
    }
}
