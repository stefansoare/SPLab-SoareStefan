package Classes;

public class Image {
    private String imageName;
    public Image(String imageName){
        this.imageName=imageName;
    }

    public void print(){
        System.out.println("Image with name: "+ this.imageName);
    }
}
