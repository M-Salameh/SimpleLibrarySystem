package IRopositries;

import Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookRepository extends JpaRepository<Book, Long> {
    public Book findByIsbn(String isbn);
}
