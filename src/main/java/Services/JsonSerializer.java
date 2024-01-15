package Services;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.servlet.http.PushBuilder;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import Classes.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PrimitiveIterator;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class JsonSerializer {
    private final BookSaveVisitor saveVisitor;

    public JsonSerializer() {
        saveVisitor = new BookSaveVisitor();
    }

    public String serialize(Visitee book) {
        saveVisitor.clearBuffer();
        book.accept(saveVisitor);
        return saveVisitor.getJson();
    }

    public String serialize(List<Visitee> books) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[");
        for (int i = 0; i < books.size(); i++) {
            saveVisitor.clearBuffer();
            books.get(i).accept(saveVisitor);
            jsonBuilder.append(saveVisitor.getJson());
            if (i != books.size() - 1)
                jsonBuilder.append(",");
        }
        jsonBuilder.append("]");

        return jsonBuilder.toString();
    }


    public Element DeserializeBookRecursive(JsonNode node)
    {
        return internDeserializeBook(node, new Book());
    }

    private Element internDeserializeBook(JsonNode node, Section section){
        JsonNode elementListNode = node.get("elementList");
        if(elementListNode == null){
            return  deserializeBaseType(node);
        }

        Iterator<JsonNode> elementList = elementListNode.elements();
        String title = node.get("title").asText();
        List<Element> tmpElementList = new ArrayList<>();

        while (elementList.hasNext()){
            Element elt = internDeserializeBook(elementList.next(), new Section());
            tmpElementList.add(elt);
        }
        section.setTitle(title);
        section.setElementList(tmpElementList);
        return section;
    }

    private Element deserializeBaseType(JsonNode node){
        String className = node.get("class").asText();

        if(className.equals(Image.class.toString())){
            String imageName = node.get("imageName").asText();
            return new Image(imageName);
        }
        if(className.equals(Paragraph.class.toString())){
            String text = node.get("text").asText();
            return new Paragraph(text);
        }

        if(className.equals(Table.class.toString())){
            String title = node.get("title").asText();
            return new Table(title);
        }
        return null;
    }

}