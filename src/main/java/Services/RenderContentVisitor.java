package Services;

import jakarta.servlet.http.PushBuilder;
import Classes.*;

public class RenderContentVisitor implements Visitor<Void> {

    @Override
    public Void visitBook(Book book) {
        System.out.println("Book: " + book.getTitle());
        System.out.println();

        System.out.println("Authors: ");
        for (Author author :
                book.getAuthorList()) {
            author.accept(this);
        }
        System.out.println();

        for (Visitee element :
                book.getElementList()) {
            element.accept(this);
        }
        return null;
    }

    @Override
    public Void visitSection(Section section) {
        System.out.println(section.getTitle());
        for (Element element :
                section.getElementList()) {
            element.accept(this);
        }
        return null;
    }

    @Override
    public Void visitTableOfContents(TableOfContents toc) {
        int pageCount = 1;
        System.out.println("TableOfContent");
        for (String entry :
                toc.getEntries()) {
            if(entry != null) System.out.println(entry + ".........." + pageCount);
            else pageCount++;
        }
        return null;
    }

    @Override
    public Void visitParagraph(Paragraph paragraph) {
        System.out.println("Paragraph: " + paragraph.getText());
        return null;
    }

    @Override
    public Void visitImageProxy(ImageProxy imageProxy) {
        imageProxy.LoadImage().accept(this);
        return null;
    }

    @Override
    public Void visitImage(Image image) {
        System.out.println("Image with name: " + image.getImageName());
        return null;
    }

    @Override
    public Void visitTable(Table table) {
        System.out.println("Table with Title: " + table.getTitle());
        return null;
    }

    public Void visitAuthor(Author author) {
        System.out.println("Author: " + author.getName());
        return null;
    }
}