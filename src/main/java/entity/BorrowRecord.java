package com.example.library_management_system.entity;
import jakarta.persistence.*;
import java.time.LocalDate;
@Entity @Table(name="borrow_records")
public class BorrowRecord {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(optional=false) @JoinColumn(name="book_id") private Book book;
    @ManyToOne(optional=false) @JoinColumn(name="member_id") private Member member;
    private LocalDate issueDate; private LocalDate dueDate; private LocalDate returnDate;
    private String status;
    public BorrowRecord() {}
    public Long getId(){return id;} public Book getBook(){return book;} public void setBook(Book v){book=v;}
    public Member getMember(){return member;} public void setMember(Member v){member=v;}
    public LocalDate getIssueDate(){return issueDate;} public void setIssueDate(LocalDate v){issueDate=v;}
    public LocalDate getDueDate(){return dueDate;} public void setDueDate(LocalDate v){dueDate=v;}
    public LocalDate getReturnDate(){return returnDate;} public void setReturnDate(LocalDate v){returnDate=v;}
    public String getStatus(){return status;} public void setStatus(String v){status=v;}
}
