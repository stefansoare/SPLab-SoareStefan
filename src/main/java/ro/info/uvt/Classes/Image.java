package ro.info.uvt.Classes;

import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
public class Image extends BaseElement implements Visitee {

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
    public void add(BaseElement e) {
        throw new IllegalStateException("Cannot add an element");
    }

    @Override
    public void remove(BaseElement e) {
        throw new IllegalStateException("Cannot remove an element");
    }

    @Override
    public BaseElement get(int index) {
        throw new IllegalStateException("Cannot get an element");
    }

    @Override
    public BaseElement clone() {
        return new Image(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitImage(this);
    }
}