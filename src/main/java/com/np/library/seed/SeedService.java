package com.np.library.seed;

import com.np.library.domain.*;
import com.np.library.domain.enumeration.AccountStatus;
import com.np.library.domain.enumeration.UserRole;
import com.np.library.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Klasa koja automacki ubacuje podatke u bazu podataka
 */
@Service
public class SeedService {
    /**
     * Instanca repozitorijuma za rad sa knjigama
     */
    @Autowired
    private BookRepository bookRepository;
    /**
     * Instanca repozitorijuma za rad sa autorima
     */
    @Autowired
    private AuthorRepository authorRepository;
    /**
     * Instanca repozitorijuma za rad sa bibliotekama
     */
    @Autowired
    private LibraryRepository libraryRepository;
    /**
     * Instanca repozitorijuma za rad sa bibliotekarima
     */
    @Autowired
    private LibrarianRepository librarianRepository;
    /**
     * Instanca repozitorijuma za rad sa clanovima biblioteke
     */
    @Autowired
    private LibraryUserRepository libraryUserRepository;

    /**
     * Metoda kreira podatke i ubacuje ih u bazu
     */
    public void init(){
        List<Author> authors=new ArrayList<>();
        Author a1=new Author(null,"Milos Crnjanski","Jedan od najboljih pripovedaca i romansijera",null);
        Author a2=new Author(null,"Lav Tolstoj","Ruski pisac 19. veka, predstavnik realizma",null);
        Author a3=new Author(null,"Ivo Andric","Srpski i jugoslovenski pisac i diplomata",null);
        authors.add(a1);
        authors.add(a2);
        authors.add(a3);

        authorRepository.saveAll(authors);
        Set<Author> authors1=new HashSet<>();
        authors1.add(a1);
        Set<Author> authors2=new HashSet<>();
        authors2.add(a2);
        Set<Author> authors3=new HashSet<>();
        authors3.add(a3);

        Book book1=new Book("11111","Seobe","Roman 19 vek","Laguna","Srpski",340,0,authors1,null);
        Book book2=new Book("22222","Strazilovo","Poezija","Laguna","Srpski",212,0,authors1,null);
        Book book3=new Book("33333","Na drini cuprija","Roman","Vulkan","Srpski",389,0,authors3,null);
        Book book4=new Book("44444","Travnicka hronika","Roman","Vulkan","Srpski",80,0,authors3,null);
        Book book5=new Book("55555","Ana Karenjina","Roman Realizam","Zavod","Srpski",970,0,authors2,null);

        List<Book> books=new ArrayList<>();
        books.add(book1);books.add(book2);books.add(book3);books.add(book4);books.add(book5);
        bookRepository.saveAll(books);

        Library library=new Library(null,"Vukova biblioteka","Narodnih heroja 20","Beograd","Srbija",null,null);
        libraryRepository.save(library);

        Librarian librarian=new Librarian("Mihajlo","Pavlovic", LocalDate.of(2020,06,04),null);
        librarian.setUsername("miha123");
        librarian.setPassword("miha123");
        librarian.setLibrary(library);
        librarian.setRole(UserRole.USER);
        librarianRepository.save(librarian);

        LibraryUser user1=new LibraryUser("Mika","Mikic",20, AccountStatus.ACTIVE,LocalDate.of(2022,06,04),"mika@gmail.com",null);
        user1.setUsername("mika123");
        user1.setPassword("mika123");
        user1.setLibrary(library);
        LibraryUser user2=new LibraryUser("Pera","Peric",24, AccountStatus.ACTIVE,LocalDate.of(2021,11,05),"pera@gmail.com",null);
        user2.setUsername("pera123");
        user2.setPassword("pera123");
        user2.setLibrary(library);
        libraryUserRepository.save(user1);
        libraryUserRepository.save(user2);
    }
}
