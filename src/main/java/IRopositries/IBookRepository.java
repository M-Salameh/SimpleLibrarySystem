package IRopositries;

import Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookRepository extends JpaRepository<Book, Long> {
    Book findByIsbn(String isbn);
}
