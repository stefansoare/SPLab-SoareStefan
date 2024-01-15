package ro.info.uvt.Services;

import lombok.Getter;
import ro.info.uvt.Classes.*;

public class TableOfContentUpdate implements Visitor<Void> {
    @Getter
    private TableOfContents toC;

    public TableOfContentUpdate() {
        toC = new TableOfContents();
    }

    @Override
    public Void visitBook(Book book) {
        for (Visitee element :
                book.getElementList()) {
            element.accept(this);
        }
        return null;
    }

    @Override
    public Void visitSection(Section section) {
        toC.addEntry(section.getTitle());
        for (BaseElement element :
                section.getElementList()) {
            element.accept(this);
        }
        return null;
    }

    @Override
    public Void visitTableOfContents(TableOfContents toc) {
        return null;
    }

    @Override
    public Void visitParagraph(Paragraph paragraph) {
        toC.addEntry(null);
        return null;
    }

    @Override
    public Void visitImageProxy(ImageProxy imageProxy) {
        imageProxy.LoadImage().accept(this);
        return null;
    }

    @Override
    public Void visitImage(Image image) {
        toC.addEntry(null);
        return null;
    }

    @Override
    public Void visitTable(Table table) {
        toC.addEntry(null);
        return null;
    }

    @Override
    public Void visitAuthor(Author author) {
        return null;
    }

}