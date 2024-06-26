package com.campus.spring.book.service;

import com.campus.spring.book.entity.BookEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {
    static List<BookEntity> bookStorage = new ArrayList<>();

    public BookService() {
        fillStore();
    }

    private void fillStore(){
        Random random = new Random();
        for(int i=0; i<100; i++){
            BookEntity book = new BookEntity();
            book.setId(i);
            book.setTitle("Book #" + random.nextInt(100,999));
            book.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
            bookStorage.add(book);
        }
    }
    public List<BookEntity> all(){
        return bookStorage;
    }
    public Optional<BookEntity> byId(Integer id){
        return bookStorage.stream().filter((bookEntity -> bookEntity.getId().equals(id))).findFirst();
    }
    public BookEntity create(String title, String description){
        BookEntity book = new BookEntity();
        book.setId(bookStorage.size());
        book.setTitle(title);
        book.setDescription(description);
        bookStorage.add(book);
        return book;
    }
    public Optional<BookEntity> edit(BookEntity book){
        Optional<BookEntity> bookEntityOptional = byId(book.getId());
        if(bookEntityOptional.isEmpty()){
            return Optional.empty();
        }
        BookEntity oldBook = bookEntityOptional.get();
        oldBook.setTitle(book.getTitle());
        oldBook.setDescription(book.getDescription());
        return Optional.of(oldBook);
    }
    public Boolean delete(Integer id){
        Optional<BookEntity> book =byId(id);
        if(book.isEmpty())return false;
        bookStorage.remove(book.get());
        return true;
    }
    public Optional<BookEntity> editPart(Integer id, Map<String, String> fields){
        Optional<BookEntity> optionalBookEntity = byId(id);
        if(optionalBookEntity.isEmpty()) return Optional.empty();
        BookEntity oldBook = optionalBookEntity.get();
        for (String key : fields.keySet()){
            switch (key){
                case "title" -> oldBook.setTitle(fields.get(key));
                case "description" -> oldBook.setDescription(fields.get(key));
            }


        }
        return Optional.of(oldBook);
    }
}
