package dev.techdozo.stream.examples;

import dev.techdozo.stream.examples.model.Book;
import dev.techdozo.stream.examples.model.Catalog;
import dev.techdozo.stream.examples.model.Category;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

/** Stream grouping example */
@Slf4j
public class ToMapGrouping {

  public static void main(String[] args) {
    List<Book> books = Catalog.books();
    log.info("Group by category {} ", groupByCategory(books));
    log.info("Group by category {} ", groupByCategorySimplified(books));
  }

  /**
   * Grouping by category of book.
   *
   * @param books List of books
   * @return HashMap containing key as name of book and value List of book.
   */
  private static Map<Category, List<Book>> groupByCategory(List<Book> books) {
    return books.stream()
        .collect(
            toMap(
                Book::getCategory,
                book -> new ArrayList<>(Arrays.asList(book)),
                (oldList, newList) -> {
                  oldList.addAll(newList);
                  return oldList;
                }));
  }

  /**
   * Simplified version of above example using groupingBy collector.Grouping by category of book.
   *
   * @param books List of books
   * @return HashMap containing key as name of book and value List of book.
   */
  private static Map<Category, List<Book>> groupByCategorySimplified(List<Book> books) {
    return books.stream().collect(groupingBy(Book::getCategory));
  }
}
