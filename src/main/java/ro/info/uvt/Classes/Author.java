package ro.info.uvt.Classes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
public class Author implements Visitee {
    @Id
    @GeneratedValue
    private Long id;
    @Getter
    private String name;
    private String surname;

    public Author() {
        name = "";
        surname = "";
    }

    public Author(String name) {
        this.name = name;
    }

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Author(Author author){
        this.name = author.name;
        this.surname = author.surname;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitAuthor(this);
    }
}