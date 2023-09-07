package com.example.jpa_relation_test.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
//일대다 및 다대일 관계로 변경
//SELECT * FROM PURCHASE;
//ID  	BOOK_ID  	MEMBER_ID
@Entity
@Getter
@NoArgsConstructor
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;


    public Purchase(Member member, Book book) {
        this.member = member;
        this.book = book;
    }
}
