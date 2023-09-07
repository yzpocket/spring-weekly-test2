package com.example.jpa_relation_test.entity;

import com.example.jpa_relation_test.BookSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
//@JsonSerialize(using = BookSerializer.class) //<- bookStore에서 name만 추출하기위해 시리얼라이저 BookSerializer를 주입
@Entity
@Getter
@Table(name = "book")
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "book_store_id")
    private BookStore bookStore;

    //일대다 및 다대일 관계로 변경
    //SELECT * FROM PURCHASE;
    //ID  	BOOK_ID  	MEMBER_ID
    @OneToMany(mappedBy = "book")
    private List<Purchase> purchases = new ArrayList<>();

    //Book 엔티티에서 BookStore 엔티티를 참조
    public BookStore getBookStore() {
        return bookStore;
    }


    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setBookStore(BookStore targetStore) {
        if (this.bookStore != null) {
            this.bookStore.getBookList().remove(this); // 기존 서점 책 제거
        }

        this.bookStore = targetStore;

        if (targetStore != null) {
            targetStore.getBookList().add(this); // 이동 서점에 책 추가
        }
    }
}
