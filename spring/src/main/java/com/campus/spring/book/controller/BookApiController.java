package com.campus.spring.book.controller;

import com.campus.spring.base.exeption.ResourceNotFoundException;
import com.campus.spring.book.entity.BookEntity;
import com.campus.spring.book.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookApiController {
    private final BookService bookService;
    public BookApiController(BookService bookService){
        this.bookService = bookService;
    }
    @GetMapping("/")
    public String ok(){
      return "ok";
    }
    @GetMapping("/api/v1/book")
    public List<BookEntity> all(){
        return bookService.all();
    }
    @GetMapping("/api/v1/book/{id}")
    public BookEntity byId(@PathVariable Integer id){
        return bookService.byId(id).orElseThrow(ResourceNotFoundException::new);
    }

}
