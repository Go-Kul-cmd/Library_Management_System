package com.example.library_management_system.service;
import com.example.library_management_system.entity.Book;
import com.example.library_management_system.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
public class BookService {
    private final BookRepository repo; private final BorrowRecordRepository borrowRepo;
    public BookService(BookRepository r,BorrowRecordRepository br){repo=r;borrowRepo=br;}
    public List<Book> all(String q){return q==null||q.isBlank()?repo.findAll():repo.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrIsbnContainingIgnoreCase(q,q,q);}
    public Book one(Long id){return repo.findById(id).orElseThrow(()->new java.util.NoSuchElementException("Book not found: "+id));}
    public Book create(Book b){if(b.getQuantity()<0)throw new IllegalArgumentException("Quantity cannot be negative");b.setAvailableQuantity(b.getQuantity());return repo.save(b);}
    public Book update(Long id,Book x){Book b=one(id);int issued=b.getQuantity()-b.getAvailableQuantity();if(x.getQuantity()<issued)throw new IllegalArgumentException("Quantity cannot be less than copies already issued");b.setTitle(x.getTitle());b.setAuthor(x.getAuthor());b.setIsbn(x.getIsbn());b.setCategory(x.getCategory());b.setQuantity(x.getQuantity());b.setAvailableQuantity(x.getQuantity()-issued);return repo.save(b);}
    @Transactional public void delete(Long id){Book b=one(id);if(borrowRepo.existsByBookIdAndStatus(id,"ISSUED"))throw new IllegalArgumentException("Cannot delete a book while copies are issued");repo.delete(b);}
}