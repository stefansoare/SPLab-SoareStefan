package Classes;

public class Paragraph implements Element{
    private String text;
    public Paragraph(String text){
        this.text=text;
    }
    @Override
    public String print()
    {
        return "Paragraph: " + this.text;
    }

}
