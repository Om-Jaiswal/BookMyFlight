package com.jaiswal.book.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "database_sequences")
public class DatabaseSequence {

    @Id
    private String id;
    private long sequence;
    
}