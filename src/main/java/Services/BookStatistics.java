package Services;

import lombok.Getter;
import Classes.*;

@Getter
public class BookStatistics implements Visitor<Void> {
    private int NoImages;
    private int NoTables;
    private int NoParagraphs;

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
        for (Element element :
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
        NoParagraphs++;
        return null;
    }

    @Override
    public Void visitImageProxy(ImageProxy imageProxy) {
        imageProxy.LoadImage().accept(this);
        return null;
    }

    @Override
    public Void visitImage(Image image) {
        NoImages++;
        return null;
    }

    @Override
    public Void visitTable(Table table) {
        NoTables++;
        return null;
    }

    @Override
    public Void visitAuthor(Author author) {
        return null;
    }

    public String getStatistics() {
        String statisticsTemplate = """
                Book Statistics:
                *** Number of images: %d
                *** Number of tables: %d
                *** Number of paragraphs: %d
                """;
        return String.format(statisticsTemplate,NoImages ,NoTables ,NoParagraphs);

    }
}