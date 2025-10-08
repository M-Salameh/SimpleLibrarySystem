package com.maids.LibrarySystem;


import com.maids.LibrarySystem.Entities.Book;
import com.maids.LibrarySystem.Entities.BookAuthor;
import com.maids.LibrarySystem.IRepositries.BookAuthorRepository;
import com.maids.LibrarySystem.IRepositries.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class DataSeederCMD implements CommandLineRunner {

    @Autowired
    private BookAuthorRepository repository;

    @Autowired
    private IBookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {
        //repository.deleteAll();
        Page<BookAuthor> content = repository.findAll(PageRequest.of(0 , 2));
        if (content.isEmpty()) {
            System.out.println("Seeding");
            BookAuthor author = new BookAuthor();
            author.setCity("city-1");
            BookAuthor author2 = new BookAuthor();
            repository.save(author);
            repository.save(author2);
            repository.flush();


            Book book = new Book();
            Book book2 = new Book();

            book.setIsbn("test get null");
            book2.setIsbn("test get null-2");

            book.setBookAuthor(author);
            book2.setBookAuthor(author2);

            book.setAuthor("author with city-1");
            book2.setAuthor("author with city=null but author exists");

            book.setTitle("author with city-1");
            book2.setTitle("author with city=null but author exists");

            Book book3 = new Book();
            book3.setIsbn("test get null-3");
            book3.setAuthor("no book author");
            book3.setTitle("no book author entity");

            bookRepository.save(book);
            bookRepository.save(book2);
            bookRepository.save(book3);
            bookRepository.flush();

            System.out.println("Done Seeding");

        }
    }
}
