package com.study.service.Impl;

import com.study.entity.Book;
import com.study.entity.Borrow;
import com.study.mapper.BookMapper;
import com.study.service.BookService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImpl implements BookService {
    @Resource
    BookMapper book;

    @Override
    public List<Borrow> getBorrowList() {
        return book.getBorrowList();
    }

    @Override
    public void addBorrow(int bid,int sid) {
        book.addBorrow(bid, sid);
    }

    @Override
    public void returnBook(int id) {
        book.deleteBorrow(id);
    }

    @Override
    public void addBook(String title, String desc, double price) {
        book.addBook(title,desc,price);
    }

    @Override
    public void deleteBook(int bid) {
        book.deleteBook(bid);
    }

    @Override
    public Map<Book, Boolean> getBookList() {
        Set<Integer> set = new HashSet<>();
        this.getBorrowList().forEach(borrow -> set.add(borrow.getBid()));
        Map<Book, Boolean> map = new LinkedHashMap<>();
        book.getBookList().forEach(book -> map.put(book, set.contains(book.getId())));
        return map;
    }

    @Override
    public List<Book> getActiveBookList() {
        Set<Integer> set = new HashSet<>();
        this.getBorrowList().forEach(borrow -> set.add(borrow.getBid()));
        return book.getBookList()
                .stream()
                .filter(book -> !set.contains(book.getId()))
                .toList();
    }



}
