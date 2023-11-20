package Classes;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private String title;
    private List<Author> authors;
    private List<Section> sections;

    public Book(String title) {
        this.title = title;
        this.authors = new ArrayList<>();
        this.sections = new ArrayList<>();
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public Section createChapter(String name) {
        Section chapter = new Section(name);
        sections.add(chapter);
        return chapter;
    }

    public void addContent(Element content) {
        if (!sections.isEmpty()) {
            sections.get(sections.size() - 1).add(content);
        }
    }

    public void print() {
        System.out.println("Book: " + title);
        System.out.println("Authors:");
        for (Author author : authors) {
            author.print();
        }
        for (Section section : sections) {
            section.print();
        }
    }
}