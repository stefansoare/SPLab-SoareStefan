package ro.uvt.info.persistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.uvt.info.Classes.Book;

@Repository
public interface BookJpaRepository extends JpaRepository<Book, Long> {
}