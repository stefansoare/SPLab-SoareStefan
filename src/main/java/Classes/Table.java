package Classes;
import lombok.Getter;

import java.util.ArrayList;

public class Table extends Element implements Visitee {
    @Getter
    private String title;

    public Table() {
        title = "";
    }

    public Table(String title) {
        this.title = title;
    }
    public Table(Table other){
        this.title = other.title;
        this.elementList = new ArrayList<>(other.elementList);
    }


    @Override
    public Element clone() {
        return new Table(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitTable(this);
    }
}