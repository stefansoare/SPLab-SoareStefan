package Classes;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Book extends Section implements Visitee {

    private List<Author> authorList;

    public Book(){
        super("");
        authorList = new ArrayList<>();
    }

    public Book(String title) {
        super(title);
        authorList = new ArrayList<>();
    }

    public Book(Book other){
        super(other.title);
        this.authorList = new ArrayList<>(other.authorList);
    }

    public void addAuthor(Author author) {
        this.authorList.add(new Author(author));
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitBook(this);
    }

}