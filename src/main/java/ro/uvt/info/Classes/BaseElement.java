package ro.uvt.info.Classes;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Getter;
import ro.uvt.info.Services.ElementDeserializer;

@Getter
@JsonDeserialize(using = ElementDeserializer.class)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BaseElement implements Visitee {
    @Id
    @GeneratedValue
    protected Long id;

    public BaseElement() {}

    public void add(BaseElement e) {}
    public void remove(BaseElement e) {}
    public BaseElement get(int index) { throw new UnsupportedOperationException("not implemented for BaseElement");}
    public BaseElement clone(){
        throw new UnsupportedOperationException("not implemented for BaseElement");
    }
}