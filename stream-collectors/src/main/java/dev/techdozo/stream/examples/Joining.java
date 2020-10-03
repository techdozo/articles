package dev.techdozo.stream.examples;

import dev.techdozo.stream.examples.model.Book;
import dev.techdozo.stream.examples.model.Catalog;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static java.util.stream.Collectors.joining;

/**
 * Examples of Joining collector
 */
@Slf4j
public class Joining {

  public static void main(String[] args) {
    List<Book> books = Catalog.books();
    log.info("Books - {} ", joiningByName(books));
    log.info("Books - {} ", joiningByNamePretty(books));
  }

  /**
   * Joining books by name, separated by delimiter ,
   * @param books List of book
   * @return String containing books separated by '
   */
  private static String joiningByName(List<Book> books) {
    return books.stream().map(Book::getName).collect(joining(","));
  }

  /**
   * Joining books by name, separated by delimiter ,
   * @param books List of book
   * @return String containing books separated by ' with prefix '[' and suffix ']'
   */
  private static String joiningByNamePretty(List<Book> books) {
    return books.stream().map(Book::getName).collect(joining(",", "[", "]"));
  }

}
