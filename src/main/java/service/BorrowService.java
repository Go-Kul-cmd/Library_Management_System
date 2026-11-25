package com.example.library_management_system.service;
import com.example.library_management_system.entity.*;
import com.example.library_management_system.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
@Service
public class BorrowService {
    private final BookRepository books; private final MemberRepository members; private final BorrowRecordRepository records;
    public BorrowService(BookRepository b,MemberRepository m,BorrowRecordRepository r){books=b;members=m;records=r;}
    public List<BorrowRecord> all(){return records.findAllByOrderByIssueDateDesc();}
    @Transactional public BorrowRecord issue(Long bookId,Long memberId,int days){
        if(days<1||days>60)throw new IllegalArgumentException("Loan period must be 1 to 60 days");
        Book b=books.findById(bookId).orElseThrow(()->new java.util.NoSuchElementException("Book not found"));
        Member m=members.findById(memberId).orElseThrow(()->new java.util.NoSuchElementException("Member not found"));
        if(b.getAvailableQuantity()<1)throw new IllegalArgumentException("No copies available");
        b.setAvailableQuantity(b.getAvailableQuantity()-1);books.save(b);
        BorrowRecord r=new BorrowRecord();r.setBook(b);r.setMember(m);r.setIssueDate(LocalDate.now());r.setDueDate(LocalDate.now().plusDays(days));r.setStatus("ISSUED");
        return records.save(r);
    }
    @Transactional public BorrowRecord giveBack(Long id){
        BorrowRecord r=records.findById(id).orElseThrow(()->new java.util.NoSuchElementException("Borrow record not found"));
        if("RETURNED".equals(r.getStatus()))throw new IllegalArgumentException("Already returned");
        Book b=r.getBook();b.setAvailableQuantity(b.getAvailableQuantity()+1);books.save(b);
        r.setStatus("RETURNED");r.setReturnDate(LocalDate.now());return records.save(r);
    }
}