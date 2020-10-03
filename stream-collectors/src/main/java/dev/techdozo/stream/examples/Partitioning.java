package dev.techdozo.stream.examples;

import dev.techdozo.stream.examples.model.Book;
import dev.techdozo.stream.examples.model.Catalog;
import dev.techdozo.stream.examples.model.Category;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

/** Examples of partitioning collector */
@Slf4j
public class Partitioning {

  public static void main(String[] args) {
    List<Book> books = Catalog.books();
    log.info("Partitioned Catalog {} ", partitioningByJavaBooks(books));
    log.info("Partitioned Catalog {} ", partitioningByJavaBooksAndMap(books));
  }

  /**
   * Partitioning books based on category (Java vs Non Java)
   *
   * @param books List of books
   * @return HashMap containing boolean key and List of book
   */
  private static Map<Boolean, List<Book>> partitioningByJavaBooks(List<Book> books) {
    return books.stream().collect(partitioningBy(book -> book.getCategory().equals(Category.JAVA)));
  }

  /**
   * Partitioning books based on category (Java vs Non Java)
   *
   * @param books List of books
   * @return HashMap containing boolean key and List of book names
   */
  private static Map<Boolean, List<String>> partitioningByJavaBooksAndMap(List<Book> books) {
    return books.stream()
        .collect(
            partitioningBy(
                book -> book.getCategory().equals(Category.JAVA),
                mapping(Book::getName, toList())));
  }
}
