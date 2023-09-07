package com.example.jpa_relation_test.service;

import com.example.jpa_relation_test.dto.MemberRequestDto;
import com.example.jpa_relation_test.entity.Book;
import com.example.jpa_relation_test.entity.BookStore;
import com.example.jpa_relation_test.entity.Member;
import com.example.jpa_relation_test.repository.BookRepository;
import com.example.jpa_relation_test.repository.BookStoreRepository;
import com.example.jpa_relation_test.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final BookStoreRepository bookStoreRepository;

    @Transactional
    public Member signup(MemberRequestDto memberRequestDto) {
        Member member = new Member();
        member.setEmail(memberRequestDto.getEmail());
        member.setPassword(memberRequestDto.getPassword());
        member.setAddress(memberRequestDto.getAddress());
        member.setPhoneNumber(memberRequestDto.getPhoneNumber());
        member.setNickname(memberRequestDto.getNickname());
        memberRepository.save(member);
        return member;
    }

    public List<Member> findAllMember() {
        return memberRepository.findAll();
    }

    //Q2. 서점에 책 등록된 책 수량 수정 API service 패키지, TestService의 updateBook 메서드를 완성해 주세요.
    //TestService.java의 updateBook()메소드
    @Transactional
    public Book updateBook(Book book, Long bookStoreId, Long bookId) {
        // bookId로 책 객체 생성
        Book existBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("책을 찾을 수 없습니다."));

        // 책 상품 수량 업데이트
        existBook.setStock(book.getStock()); // 재고 업데이트


        // 업데이트 책 상품 정보 저장
        bookRepository.save(existBook);
        return existBook;
    }

    @Transactional
    public List<Book> findBook(Long bookStoreId) {
        return bookRepository.findByBookStoreId(bookStoreId);
    }

    @Transactional // 수정가능하도록
    public void transferBook(Long bookId, Long bookStoreId) {
        // 이동 책 선택
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("책을 찾을 수 없습니다."));

        // 이동 서점 조회
        BookStore targetStore = bookStoreRepository.findById(bookStoreId)
                .orElseThrow(() -> new IllegalArgumentException("대상 서점을 찾을 수 없습니다."));

        // 기존 서점의 책 제거
        BookStore currentStore = book.getBookStore();
        currentStore.getBookList().remove(book);

        // 이동 서점에 책 추가
        targetStore.addBook(book);
        book.setBookStore(targetStore);

        // 변경 내용 저장
        bookRepository.save(book);
        bookStoreRepository.save(currentStore);
        bookStoreRepository.save(targetStore);
    }
}
