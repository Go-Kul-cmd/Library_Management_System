package com.example.library_management_system.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
@Entity @Table(name="books")
public class Book {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @NotBlank @Column(nullable=false) private String title;
    @NotBlank @Column(nullable=false) private String author;
    @NotBlank @Column(nullable=false,unique=true) private String isbn;
    private String category;
    @Min(0) private int quantity;
    @Min(0) private int availableQuantity;
    public Book() {}
    public Long getId(){return id;} public String getTitle(){return title;} public void setTitle(String v){title=v;}
    public String getAuthor(){return author;} public void setAuthor(String v){author=v;}
    public String getIsbn(){return isbn;} public void setIsbn(String v){isbn=v;}
    public String getCategory(){return category;} public void setCategory(String v){category=v;}
    public int getQuantity(){return quantity;} public void setQuantity(int v){quantity=v;}
    public int getAvailableQuantity(){return availableQuantity;} public void setAvailableQuantity(int v){availableQuantity=v;}
}