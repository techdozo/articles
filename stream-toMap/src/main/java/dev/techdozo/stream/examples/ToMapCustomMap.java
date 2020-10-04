package dev.techdozo.stream.examples;

import dev.techdozo.stream.examples.model.Book;
import dev.techdozo.stream.examples.model.Catalog;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentMap;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

/** Stream grouping example with custom map implementation */
@Slf4j
public class ToMapCustomMap {

  public static void main(String[] args) {
    List<Book> books = Catalog.books();
    log.info("Group by category {} ", nameAndAuthor(books));
    log.info("Name and Book, concurrentMap {} ", nameAndBookConcurrent(books));
    log.info("Name and Book, concurrentMap {} ", nameAndBookMergeFunction(books));
    log.info("Name and Book, unmodifiableMap {} ", nameAndBookUnmodifiable(books));
  }

  /**
   * Grouping by name and author.
   *
   * @param books List of books
   * @return TreeMap containing key as book name and value as author.
   */
  private static TreeMap<String, String> nameAndAuthor(List<Book> books) {
    return books.stream().collect(toMap(Book::getName, Book::getAuthor, (a, b) -> a, TreeMap::new));
  }

  /**
   * Grouping by name and Book.
   *
   * @param books List of books
   * @return ConcurrentHashMap containing key as book name and value as Book.
   */
  private static ConcurrentMap<String, Book> nameAndBookConcurrent(List<Book> books) {
    return books.stream().collect(toConcurrentMap(Book::getName, identity()));
  }

  /**
   * Grouping by name and Book. This example demonstrates use of Merge Function.
   *
   * @param books List of books
   * @return ConcurrentHashMap containing key as book name and value as Book.
   */
  private static ConcurrentMap<String, Book> nameAndBookMergeFunction(List<Book> books) {
    return books.stream().collect(toConcurrentMap(Book::getName, identity(), (a, b) -> a));
  }

  /**
   * Grouping by name and Book.
   *
   * @param books List of books
   * @return Unmodifiable Map containing key as book name and value as Book.
   */
  private static Map<String, Book> nameAndBookUnmodifiable(List<Book> books) {
    return books.stream().collect(toUnmodifiableMap(Book::getName, identity()));
  }
}
