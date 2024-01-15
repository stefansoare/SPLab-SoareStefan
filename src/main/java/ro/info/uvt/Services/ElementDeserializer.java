package ro.info.uvt.Services;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import ro.info.uvt.Classes.BaseElement;
import ro.info.uvt.Classes.Book;

import java.io.IOException;

public class ElementDeserializer extends StdDeserializer<BaseElement> {
    public ElementDeserializer() {
        this(null);
    }

    public ElementDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public BaseElement deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonSerializer serializer = new JsonSerializer();
        JsonNode node = jp.getCodec().readTree(jp);
        String className = node.get("class").asText();

        if(className.equals(Book.class.toString())){
            return serializer.DeserializeBookRecursive(node);
        }


        return null;
    }


}