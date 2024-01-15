package ro.uvt.info.persistence;

import ro.uvt.info.Classes.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorsRepository extends JpaRepository<Author, Integer> {
}