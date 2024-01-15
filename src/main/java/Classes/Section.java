package Classes;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Getter
@Setter
public class Section extends Element implements Visitee {
    protected String title;

    public Section() {
        title = "";
        elementList = new ArrayList<>();
    }

    public Section(String title) {
        this.title = title;
        elementList = new ArrayList<>();
    }

    public Section(Section other){
        this.title = other.title;
        this.elementList = new ArrayList<>(other.elementList);
    }





    @Override
    public Element clone() {
        return new Section(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitSection(this);
    }
}