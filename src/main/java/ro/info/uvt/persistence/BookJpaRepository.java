package ro.info.uvt.persistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.info.uvt.Classes.Book;

@Repository
public interface BookJpaRepository extends JpaRepository<Book, Long> {
}