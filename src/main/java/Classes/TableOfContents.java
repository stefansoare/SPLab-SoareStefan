package Classes;

import lombok.Getter;

import javax.swing.plaf.PanelUI;
import java.util.ArrayList;
import java.util.List;

@Getter
public class TableOfContents extends Element implements Visitee {
    private final List<String> entries;

    public TableOfContents(){
        entries = new ArrayList<>();
    }

    public TableOfContents(TableOfContents other){
        entries = new ArrayList<>(other.entries);
    }

    @Override
    public Element clone() {
        return new TableOfContents(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitTableOfContents(this);
    }


    // add name if chapter/subchapter, adds null if paragraph, image, table
    public void addEntry(String entry){
        entries.add(entry);
    }
}