package Classes;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private String title;
    private List<Author> authors;
    private List<Section> chapters;
    private TableOfContents tableOfContents;

    public Book(String title) {
        this.title = title;
        this.authors = new ArrayList<>();
        this.chapters = new ArrayList<>();
        this.tableOfContents = new TableOfContents();
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public int createChapter(String name) {
        Section chapter = new Section(name);
        this.chapters.add(chapter);
        return this.chapters.indexOf(chapter);
    }

    public Section getChapter(int indexChapter) {
        return this.chapters.get(indexChapter);
    }
    public void addContent(Element content) {
        this.tableOfContents.add(content);
    }

    public void print() {
        System.out.println("Book: " + title);
        System.out.println("Authors:");
        for (Author author : authors) {
            author.print();
        }
        tableOfContents.print();
    }
}
