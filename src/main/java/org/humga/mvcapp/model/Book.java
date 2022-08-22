package org.humga.mvcapp.model;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Book {
    private String isbn;
    private String name;
    private String author;
}
