package Classes;

public class Image implements Element {
    private String imageName;
    public Image(String imageName){
        this.imageName=imageName;
    }
    @Override
    public String print(){
        return "Image with name: "+ this.imageName;
    }
}
