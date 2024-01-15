package ro.uvt.info.Services;

public class AlignCenter implements AlignStrategy{
    @Override
    public void render(String text) {
        System.out.println(  "#" + text + "#");
    }
}