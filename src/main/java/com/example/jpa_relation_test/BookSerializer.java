package com.example.jpa_relation_test;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.example.jpa_relation_test.entity.Book;

import java.io.IOException;

public class BookSerializer extends JsonSerializer<Book> {

    @Override
    public void serialize(Book book, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", book.getId());
        jsonGenerator.writeStringField("title", book.getTitle());
        jsonGenerator.writeStringField("author", book.getAuthor());
        jsonGenerator.writeNumberField("price", book.getPrice());
        jsonGenerator.writeNumberField("stock", book.getStock());

        // bookStore 필드 출력
        //jsonGenerator.writeObjectFieldStart("bookStore");
        //jsonGenerator.writeNumberField("id", book.getBookStore().getId());
        //이름만
        jsonGenerator.writeStringField("name", book.getBookStore().getName());
        jsonGenerator.writeEndObject();

        jsonGenerator.writeEndObject();
    }
}
