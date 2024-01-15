package ro.info.uvt.persistence;

import ro.info.uvt.Classes.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorsRepository extends JpaRepository<Author, Integer> {
}