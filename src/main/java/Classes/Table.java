package Classes;

import java.util.ArrayList;
import java.util.List;

public class Table implements Element {
    private String title;
    private List<Element> elements = new ArrayList<>();

    public Table(String title) {
        this.title = title;
    }

    @Override
    public void print() {
        System.out.println("Table with title: " + this.title);
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
        return null; // Or throw an exception to handle an invalid index
    }
}
