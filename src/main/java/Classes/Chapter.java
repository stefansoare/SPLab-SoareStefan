package Classes;
import java.util.ArrayList;
import java.util.List;
public class Chapter {
    private String name;
    private List<SubChapter> subChapters;

    public Chapter(String title) {
        this.name = title;
        this.subChapters = new ArrayList<>();
    }

    public int createSubChapter(String subCh) {
        SubChapter sc= new SubChapter(subCh);
        this.subChapters.add(sc);
        return this.subChapters.indexOf(sc);

    }
    public SubChapter getSubChapter(int indexSubChapter){
        return this.subChapters.get(indexSubChapter);
    }
    public void print(){
        System.out.print(this.name);
        for(SubChapter sc: subChapters)
            sc.print();
    }
}
