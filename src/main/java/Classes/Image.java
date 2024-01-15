package Classes;

import lombok.Getter;

public class Image extends Element implements Visitee {
    @Getter
    private String imageName;

    public Image() { imageName = ""; }
    public Image(String imageName) {
        this.imageName = imageName;
    }
    public Image(Image other){
        imageName = other.imageName;
    }



    @Override
    public void add(Element e) {
        throw new IllegalStateException("Cannot add an element");
    }

    @Override
    public void remove(Element e) {
        throw new IllegalStateException("Cannot remove an element");
    }

    @Override
    public Element get(int index) {
        throw new IllegalStateException("Cannot get an element");
    }

    @Override
    public Element clone() {
        return new Image(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitImage(this);
    }
}