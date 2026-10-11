package com.bookhub.book;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 200) private String title;
    @Column(nullable = false, length = 150) private String author;
    @Column(length = 100) private String category;
    @Column(unique = true, length = 30) private String isbn;
    @Column(nullable = false) private boolean available = true;

    protected Book() {}
    public Book(String title, String author, String category, String isbn, boolean available) {
        this.title = title; this.author = author; this.category = category; this.isbn = isbn; this.available = available;
    }
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getCategory() { return category; }
    public String getIsbn() { return isbn; }
    public boolean isAvailable() { return available; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setCategory(String category) { this.category = category; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public void setAvailable(boolean available) { this.available = available; }
}
