package dev.techdozo.stream.examples;

import dev.techdozo.stream.examples.model.Book;
import dev.techdozo.stream.examples.model.Catalog;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

import static dev.techdozo.stream.examples.model.Category.JAVA;
import static java.util.stream.Collectors.joining;

/** Examples of Stream Operation */
@Slf4j
public class StreamOperation {

  public static void main(String[] args) {
    List<Book> books = Catalog.books();

    List<String> javaAuthors = javaAuthors(books);
    log.info("Java Authors - {} ", javaAuthors);
  }

  /**
   * A stream pipeline consists of a source, zero or more intermediate operations, and a terminal
   * operation. Streams are lazy; computation on the source data is only performed when the terminal
   * operation is initiated. In this example source is a Collection of book, filter and map is
   * intermediate operation and collect is terminal operation.
   *
   * @param books - collection of book
   * @return - list of Java book authors
   */
  private static List<String> javaAuthors(List<Book> books) {
    List<String> javaAuthors =
        books.stream()
            .filter(book -> book.getCategory().equals(JAVA))
            .map(Book::getAuthor)
            .collect(Collectors.toList());
    return javaAuthors;
  }
}
