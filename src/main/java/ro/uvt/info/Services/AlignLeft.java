package ro.uvt.info.Services;


public class AlignLeft implements AlignStrategy {
    @Override
    public void render(String text) {
        System.out.println("Paragraph: "  + text + "#");
    }
}