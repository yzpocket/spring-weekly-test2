package com.example.jpa_relation_test.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "book_store")
@NoArgsConstructor
public class BookStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column
    private String address;

    @OneToMany(mappedBy = "bookStore")
    @JsonIgnore // 무한 순환 방지
    private List<Book> bookList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void addBook(Book book){
        this.bookList.add(book);
    }
}
