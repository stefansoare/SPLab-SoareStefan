package Classes;
import java.util.ArrayList;
import java.util.List;

public class Book {
    private String title;
    private List<Author> authors;
    private List<Chapter> chapters;
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
        Chapter ch=new Chapter(name);
        this.chapters.add(ch);
        return this.chapters.indexOf(ch);
    }
    public Chapter getChapter(int indexChapter){
        return this.chapters.get(indexChapter);
    }
    public void print(){
        System.out.println(title);
        for(Author a:authors)
            a.print();
        for(Chapter c:chapters)
            c.print();
    }
}
