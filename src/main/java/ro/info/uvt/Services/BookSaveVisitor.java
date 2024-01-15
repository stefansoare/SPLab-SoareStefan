package ro.info.uvt.Services;

import ro.info.uvt.Classes.*;

import java.util.List;


public class BookSaveVisitor implements Visitor<Void> {
    private StringBuilder buildingJson = new StringBuilder();

    public void clearBuffer(){
        buildingJson = new StringBuilder();
    }

    @Override
    public Void visitBook(Book book) {
        String BookPropertiesTemplateJson = """
                {
                    "title": "%s",
                    "class": "%s",
                    "authorList": [
                """;
        buildingJson.append(String.format(BookPropertiesTemplateJson,book.getTitle(), Book.class));
        for (Author author :
                book.getAuthorList()) {
            author.accept(this);
        }
        buildingJson.append("]");
        buildingJson.append(!book.getElementList().isEmpty() ? ",\n \"elementList\": [" : "");
        List<BaseElement> books = book.getElementList();
        printChilds(books);
        buildingJson.append(!book.getElementList().isEmpty() ? "]" : "");
        buildingJson.append("}");
        return null;
    }

    private void printChilds(List<BaseElement> books) {
        for (int i = 0; i < books.size(); i++) {
            books.get(i).accept(this);
            if (i != books.size() - 1) buildingJson.append(",");
        }
    }

    @Override
    public Void visitSection(Section section) {
        String sectionJsonTemplate = """
                {
                    "title": "%s",
                    "class": "%s"%s
                """;
        String json = String.format(sectionJsonTemplate,
                section.getTitle(), Section.class,
                !section.getElementList().isEmpty() ? ", \"elementList\" : [ " : "");
        buildingJson.append(json);
        var sections = section.getElementList();
        printChilds(sections);
        if(!section.getElementList().isEmpty())
            buildingJson.append("]");
        buildingJson.append("}");
        return null;
    }

    @Override
    public Void visitTableOfContents(TableOfContents toc) {
        int pageCount = 1;
        String entryTemplate = "\"%s\":\"%s\"";
        buildingJson.append("{");
        for (String entry :
                toc.getEntries()) {
            if (entry != null)
                buildingJson.append(String.format(entryTemplate, entry, pageCount));
            else pageCount++;
        }
        buildingJson.append("}");
        return null;
    }

    @Override
    public Void visitParagraph(Paragraph paragraph) {
        String paragraphJsonTemplate = """
                {
                    "text": "%s",
                    "class": "%s"
                }
                """;
        buildingJson.append(String.format(paragraphJsonTemplate, paragraph.getText(), Paragraph.class));
        return null;
    }

    @Override
    public Void visitImageProxy(ImageProxy imageProxy) {
        imageProxy.LoadImage().accept(this);
        return null;
    }

    @Override
    public Void visitImage(Image image) {
        String imageJsonTemplate = """
                {
                    "imageName": "%s",
                    "class": "%s"
                }
                """;
        buildingJson.append(String.format(imageJsonTemplate, image.getImageName(), Image.class));
        return null;
    }

    @Override
    public Void visitTable(Table table) {
        String tableJsonTemplate = """
                {
                    "title": "%s",
                    "class": "%s"
                }
                """;
        buildingJson.append(String.format(tableJsonTemplate, table.getTitle(), Table.class));
        return null;
    }

    public Void visitAuthor(Author author) {
        String authorJsonTemplate = """
                {
                    "authorList": "%s",
                    "class": "%s"
                }
                """;
        String json = String.format(authorJsonTemplate, author.getName(), Author.class);
        buildingJson.append(json);
        return null;
    }

    public String getJson(){
        return buildingJson.toString();
    }
}