package dev.techdozo.stream.examples;

import dev.techdozo.stream.examples.model.Book;
import dev.techdozo.stream.examples.model.Catalog;
import dev.techdozo.stream.examples.model.Category;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.Comparator.comparingDouble;
import static java.util.function.BinaryOperator.maxBy;
import static java.util.stream.Collectors.toMap;

/** Stream grouping example with Min and Max operation */
@Slf4j
public class ToMapMinMax {

  public static void main(String[] args) {
    List<Book> books = Catalog.books();
    log.info("Grouped by and max price {} ", groupByCategoryMaxPrice(books));
    log.info("Grouped by and min price {} ", groupByCategoryMinPrice(books));
  }

  /**
   * Grouping by a category with max price for that category.
   *
   * @param books List of books
   * @return HashMap containing key as a Category of book and value as a max price of that category.
   */
  private static Map<Category, Book> groupByCategoryMaxPrice(List<Book> books) {
    return books.stream()
        .collect(
            toMap(Book::getCategory, Function.identity(), maxBy(comparingDouble(Book::getPrice))));
  }

  /**
   * Grouping by book category with min price for that category.
   *
   * @param books List of books
   * @return HashMap containing key as a Category of book and value as a min price of that category.
   */
  private static Map<Category, Book> groupByCategoryMinPrice(List<Book> books) {
    return books.stream()
        .collect(
            toMap(Book::getCategory, Function.identity(), maxBy(comparingDouble(Book::getPrice))));
  }
}
