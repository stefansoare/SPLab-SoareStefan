package Classes;

import java.util.ArrayList;
import java.util.List;

public class SubChapter {
    private String name;
    private List<Image> images;
    private List<Paragraph> paragraphs;
    private List<Table> tables;

    public SubChapter(String title) {
        this.name = title;
        this.images = new ArrayList<>();
        this.paragraphs=new ArrayList<>();
        this.tables=new ArrayList<>();
    }

    public void createNewParagraph(String s) {
        Paragraph pg= new Paragraph(s);
        this.paragraphs.add(pg);
    }

    public void createNewImage(String s) {
        Image image= new Image(s);
        this.images.add(image);
    }

    public void createNewTable(String t) {
        Table table= new Table(t);
        this.tables.add(table);
    }

    public void print() {
        System.out.println("SubChapter: " + this.name);
        for (Paragraph paragraph : paragraphs)
            paragraph.print();
        for (Image image: images)
            image.print();
        for (Table table: tables)
            table.print();
    }
}