package dev.techdozo.stream.examples;

import dev.techdozo.stream.examples.model.Book;
import dev.techdozo.stream.examples.model.Catalog;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparingDouble;
import static java.util.stream.Collectors.*;

/**
 * Examples of Min/Max collector
 */
@Slf4j
public class MaxMin {

  public static void main(String[] args) {
    List<Book> books = Catalog.books();
    log.info("Max Price {}", maxPrice(books));
    log.info("Max Price {}", maxPriceSimplified(books));
    log.info("Max Price {}", maxPriceReducingSimplified(books));
    log.info("Max Price {}", maxPriceReducing(books));
    log.info("Min Price {}", minPrice(books));
  }

  /**
   * Book with max price
   *
   * @param books List of book
   * @return Optional of Book with max price
   */
  private static Optional<Book> maxPrice(List<Book> books) {
    Comparator<Book> comparator = comparingDouble(Book::getPrice);
    return books.stream().collect(maxBy(comparator));
  }

  /**
   * Simplified version of Book with max price
   *
   * @param books List of book
   * @return Optional of Book with min price
   */
  private static Optional<Book> maxPriceSimplified(List<Book> books) {
    Comparator<Book> comparator = comparingDouble(Book::getPrice);
    return books.stream().max(comparator);
  }

  /**
   * Book with min price
   *
   * @param books List of book
   * @return Optional of Book with min price
   */
  private static Optional<Book> minPrice(List<Book> books) {
    Comparator<Book> comparator = comparingDouble(Book::getPrice);
    return books.stream().collect(minBy(comparator));
  }

  /**
   * Book with max price using reducing. Reducing operation takes binary accumulator function (T,R) -> U
   *
   * @param books List of book
   * @return Optional of Book with max price
   */
  private static Optional<Book> maxPriceReducing(List<Book> books) {
    return books.stream().collect(reducing((b1, b2) -> b1.getPrice() > b2.getPrice() ? b1 : b2));
  }

  /**
   * Book with max price using reducing. Reduce operation takes binary accumulator function (T,R) -> U
   *
   * @param books List of book
   * @return Optional of Book with max price
   */
  private static Optional<Book> maxPriceReducingSimplified(List<Book> books) {
    return books.stream().reduce((b1, b2) -> b1.getPrice() > b2.getPrice() ? b1 : b2);
  }
}
