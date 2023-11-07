package Classes;

import java.util.ArrayList;
import java.util.List;

public class SubChapter {
    private String name;
    private List<Element> elements;


    public SubChapter(String title) {
        this.name = title;
        this.elements = new ArrayList<>();
    }
    public void createNewParagraph(String s) {
        Paragraph pg= new Paragraph(s);
        this.elements.add(pg);
    }

    public void createNewImage(String s) {
        Image image= new Image(s);
        this.elements.add(image);
    }

    public void createNewTable(String t) {
        Table table= new Table(t);
        this.elements.add(table);
    }
   public void addElement(Element element){
        elements.add(element);
   }
    public void print() {
        System.out.println("SubChapter: " + this.name);
        for(Element element: elements){
            System.out.println(element.print());
        }
    }
}