package Classes;

import Classes.Element;
import Classes.Image;
import Classes.Picture;

public class ImageProxy extends Element implements Picture, Visitee {
    private Image realImage;
    private String url;

    public ImageProxy(String url){
        this.url = url;
    }

    @Override
    public String getUrl() {
        return url;
    }

    public Image LoadImage(){
        if(realImage == null)
            realImage = new Image(url);
        return realImage;
    }



    @Override
    public Element clone() {
        return null;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitImageProxy(this);
    }
}