package dev.techdozo.stream.examples;

import dev.techdozo.stream.examples.model.Book;
import dev.techdozo.stream.examples.model.Catalog;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.*;

/** Stream toMap example */
@Slf4j
public class ToMap {

  public static void main(String[] args) {
    List<Book> books = Catalog.books();
    log.info("Name and author {} ", groupingByNameAndAuthor(books));
    log.info("Name and author - reducing {} ", groupingByNameAndAuthorReducing(books));
    log.info(
        "Name and author - reducing Non Optional {} ",
        groupingByNameAndAuthorReducingNonOptional(books));
    log.info("Name and author {} ", nameAndAuthor(books));
  }

  /**
   * Grouping by name of book and mapping to author. This example demonstrate use of groupingBy
   * collector.
   *
   * @param books List of books
   * @return HashMap containing key as name of Book and value as List of author. List is redundant
   *     as it contains only one element.
   */
  private static Map<String, List<String>> groupingByNameAndAuthor(List<Book> books) {
    return books.stream().collect(groupingBy(Book::getName, mapping(Book::getAuthor, toList())));
  }

  /**
   * In the above example map value list contains only one element. Grouping by name of book and
   * mapping to author. Reducing operation (a, b) -> a is used as one book is written by one author.
   *
   * @param books List of books
   * @return HashMap containing key as name of Book and value as Optional of author.
   */
  private static Map<String, Optional<String>> groupingByNameAndAuthorReducing(List<Book> books) {
    return books.stream()
        .collect(groupingBy(Book::getName, mapping(Book::getAuthor, reducing((a, b) -> a))));
  }

  /**
   * A non optional version of above method. Grouping by name of book and mapping to author.
   * Reducing operation (a, b) -> a is used as one book is written by one author. CollectingAndThen
   * takes Optional::get as finisher function.
   *
   * @param books List of books
   * @return HashMap containing key as name of Book and value as author
   */
  private static Map<String, String> groupingByNameAndAuthorReducingNonOptional(List<Book> books) {
    return books.stream()
        .collect(
            groupingBy(
                Book::getName,
                collectingAndThen(mapping(Book::getAuthor, reducing((a, b) -> a)), Optional::get)));
  }

  /**
   * Grouping by name of book and mapping to author.
   *
   * @param books List of books
   * @return HashMap containing key as name of book and value as author of book. This method returns
   *     Optional<Book>.
   */
  private static Map<String, String> nameAndAuthor(List<Book> books) {
    return books.stream().collect(toMap(Book::getName, Book::getAuthor));
  }
}
