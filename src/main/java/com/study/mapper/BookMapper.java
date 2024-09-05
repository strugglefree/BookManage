package com.study.mapper;

import com.study.entity.Book;
import com.study.entity.Borrow;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BookMapper {
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "bid",property = "bid"),
            @Result(column = "sid",property = "sid"),
            @Result(column = "time",property = "time"),
            @Result(column = "title",property = "title"),
            @Result(column = "name",property = "studentName")
    })
    @Select("""
        select * from borrow left join book on book.id = borrow.bid
                                left join student on borrow.sid = student.id
    """)
    List<Borrow> getBorrowList();

    @Insert("insert into borrow(bid,sid,time) values(#{bid},#{sid},NOW())")
    void addBorrow(@Param("bid") int bid, @Param("sid") int sid);

    @Delete("delete from borrow where id = #{id}")
    void deleteBorrow(int id);

    @Select("select * from book")
    List<Book> getBookList();

    @Delete("delete from book where id = #{id}")
    void deleteBook(int id);

    @Insert("insert into book(title, `desc`, price) values(#{title}, #{desc}, #{price})")
    void addBook(@Param("title") String title, @Param("desc") String desc, @Param("price") double price);
}
