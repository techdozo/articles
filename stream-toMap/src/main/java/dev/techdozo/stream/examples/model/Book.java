package dev.techdozo.stream.examples.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class Book {
  private String name;
  private Category category;
  private double price;
  private String author;
  private String publisher;
}
