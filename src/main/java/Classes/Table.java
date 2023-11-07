package Classes;

public class Table implements Element{
    private String title;
    public Table(String title){
        this.title=title;
    }
    @Override
    public String print(){
        return "Table with title: " + this.title;
    }
}
