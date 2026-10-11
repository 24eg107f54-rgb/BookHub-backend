package com.bookhub.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BookRequest(
    @NotBlank @Size(max = 200) String title,
    @NotBlank @Size(max = 150) String author,
    @Size(max = 100) String category,
    @Size(max = 30) String isbn,
    Boolean available
) {}
