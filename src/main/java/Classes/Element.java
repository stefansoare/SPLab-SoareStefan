package Classes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import Services.ElementDeserializer;

import java.util.ArrayList;
import java.util.List;

@Getter
@JsonDeserialize(using = ElementDeserializer.class)
public abstract class Element implements Visitee {

    @Setter
    protected List<Element> elementList;
    public Element() {
        this.elementList = new ArrayList<>();
    }

    public void add(Element e) {
        elementList.add(e);
    }
    public void remove(Element e) {
        elementList.remove(e);
    }
    public Element get(int index) {
        return elementList.get(index);
    }

    public abstract Element clone();
}