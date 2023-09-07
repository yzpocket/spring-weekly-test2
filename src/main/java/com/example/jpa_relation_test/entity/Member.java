package com.example.jpa_relation_test.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "member")
public class Member {
    //Q1. 회원가입 API entity 패키지의 member 클래스를 완성해 주세요.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String address;

    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;

    @Column(name = "NICKNAME", nullable = false)
    private String nickname;

    // Q5. bookstoreid -> Sparta_Store_Id FK컬럼명변경
    //SELECT * FROM MEMBER;
    //ID  	EMAIL  	PASSWORD  	ADDRESS  	PHONE_NUMBER  	NICKNAME  	SPARTA_STORE_ID
    @ManyToOne
    @JoinColumn(name = "Sparta_Store_Id")
    private BookStore bookStoreId;

    // - 회원 가입은 DTO 계층을 만들어서 작업했습니다.
    // - 요청 메소드의 void 반환 부분을 DTO 객체를 반환시켜 return으로 정상 결과를 확인했습니다.

    //Q4.일대다 및 다대일 관계로 변경
    //SELECT * FROM PURCHASE;
    //ID  	BOOK_ID  	MEMBER_ID
    @OneToMany(mappedBy = "member")
    private List<Purchase> purchases = new ArrayList<>();
}
////다대다를 지양해라? 1:M 1:M으로 풀으라 결국 연결 테이블을 매핑하는 연결 엔티티를 만들고 이곳에 추가한 컬럼들을 매핑해야 한다.
////그리고 엔티티 간의 관계도 테이블 관계처럼 다대다에서 일대다, 다대일 관계로 풀어야 한다. https://www.nowwatersblog.com/jpa/ch6/6-4 참고하기
////다대다 관계 대신, 일대다 관계로 수정 중간 테이블인 Purchase를 만들자.

