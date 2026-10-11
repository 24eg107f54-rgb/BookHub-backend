package com.bookhub.book;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookRepository books;
    public BookController(BookRepository books) { this.books = books; }

    @GetMapping
    public List<Book> list(@RequestParam(required = false) String search) {
        return (search == null || search.isBlank()) ? books.findAll() : books.search(search.trim());
    }

    @GetMapping("/{id}")
    public Book get(@PathVariable Long id) {
        return books.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@Valid @RequestBody BookRequest request) {
        Book book = new Book(request.title().trim(), request.author().trim(), request.category(),
            request.isbn(), request.available() == null || request.available());
        return books.save(book);
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable Long id, @Valid @RequestBody BookRequest request) {
        Book book = get(id);
        book.setTitle(request.title().trim());
        book.setAuthor(request.author().trim());
        book.setCategory(request.category());
        book.setIsbn(request.isbn());
        if (request.available() != null) book.setAvailable(request.available());
        return books.save(book);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { books.delete(get(id)); }
}
