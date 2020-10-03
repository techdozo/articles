package dev.techdozo.stream.examples;

import dev.techdozo.stream.examples.model.Book;
import dev.techdozo.stream.examples.model.Catalog;
import dev.techdozo.stream.examples.model.Category;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

import static java.util.Comparator.comparingDouble;
import static java.util.stream.Collectors.*;

/** Stream grouping example */
@Slf4j
public class Grouping {

  private enum Type {
    DYNAMIC,
    STATIC
  }

  public static void main(String[] args) {
    List<Book> books = Catalog.books();
    log.info("Grouped by category and print count {} ", groupByCategoryMapCount(books));
    log.info("Grouped by category {} ", groupByCategoryCountImperative(books));
    log.info("Grouped by category {} ", groupByCategory(books));
    log.info("Grouped by category with custom map {} ", groupByCategoryTreeMap(books));
    log.info("Grouped by with custom collector {} ", groupByCategoryCustomCollection(books));
    log.info("Grouped by category and Filter {} ", groupByCategoryFiltering(books));
    log.info("Filter and Grouped by category {} ", filterGroupByCategory(books));
    log.info("Grouped by Type {} ", groupByType(books));
    log.info("Grouped by and average price {} ", groupByCategoryAveragePrice(books));
    log.info("Grouped by and total price {} ", groupByCategoryTotalPrice(books));
    log.info("Grouped by and max price {} ", groupByCategoryMaxPrice(books));
    log.info("Grouped by and max price {} ", groupByCategoryMaxPriceNoOptional(books));
  }

  private static Map<Category, Long> groupByCategoryMapCount(List<Book> books) {
    return books.stream().collect(groupingBy(Book::getCategory, counting()));
  }

  /**
   * The Collector groupingBy performs group by based on a classification function. Default
   * groupingBy implementation returns HashMap.
   *
   * @param books List of books
   * @return HashMap containing key as a category and value as List of books
   */
  private static Map<Category, List<Book>> groupByCategory(List<Book> books) {
    return books.stream().collect(groupingBy(Book::getCategory));
  }

  /**
   * Custom Map implementation of groupingBy using overloaded method, which takes a Supplier and
   * downstream collector.
   *
   * @param books List of books
   * @return TreeMap containing key as a category and value as List of books
   */
  private static Map<Category, List<Book>> groupByCategoryTreeMap(List<Book> books) {
    return books.stream().collect(groupingBy(Book::getCategory, TreeMap::new, toList()));
  }

  /**
   * Custom collection implementation to store grouped books.
   *
   * @param books List of books
   * @return HashMap containing key as a category and value as HashSet of books
   */
  private static Map<Category, Set<Book>> groupByCategoryCustomCollection(List<Book> books) {
    return books.stream().collect(groupingBy(Book::getCategory, toCollection(HashSet::new)));
  }

  /**
   * Groups books by category and filters all books whose price is greater than 51
   *
   * @param books List of books
   * @return HashMap containing key as a category and value filtered list of book
   */
  private static Map<Category, List<Book>> groupByCategoryFiltering(List<Book> books) {
    return books.stream()
        .collect(groupingBy(Book::getCategory, filtering(b -> b.getPrice() > 51, toList())));
  }

  /**
   * Filtering operation of list of book and then grouping based on books category
   *
   * @param books List of books
   * @return HashMap containing key as a category and value as list of book. This operation removes
   *     all keys whose value does not satisfy filtering operation.
   */
  private static Map<Category, List<Book>> filterGroupByCategory(List<Book> books) {
    return books.stream().filter(b -> b.getPrice() > 51).collect(groupingBy(Book::getCategory));
  }

  /**
   * Grouping by a complex condition and mapping element to a different type
   *
   * @param books List of books
   * @return HashMap containing key as a Type of language and value as Set of category of book.
   */
  private static Map<Type, Set<Category>> groupByType(List<Book> books) {
    return books.stream()
        .collect(
            groupingBy(
                book -> {
                  if (book.getCategory().equals(Category.JAVA)
                      || book.getCategory().equals(Category.C_SHARP)) {
                    return Type.STATIC;
                  } else {
                    return Type.DYNAMIC;
                  }
                },
                mapping(Book::getCategory, toSet())));
  }

  /**
   * Grouping by a category with average price
   *
   * @param books List of books
   * @return HashMap containing key as a category and value as a average price of that category.
   */
  private static Map<Category, Double> groupByCategoryAveragePrice(List<Book> books) {
    return books.stream().collect(groupingBy(Book::getCategory, averagingDouble(Book::getPrice)));
  }

  /**
   * Grouping by a category with total price
   *
   * @param books List of books
   * @return HashMap containing key as a category and value as a total price of that category.
   */
  private static Map<Category, Double> groupByCategoryTotalPrice(List<Book> books) {
    return books.stream().collect(groupingBy(Book::getCategory, summingDouble(Book::getPrice)));
  }

  /**
   * Grouping by a category with max price for that category
   *
   * @param books List of books
   * @return HashMap containing key as a Category of book and value as a max price of that category.
   *     This method returns Optional<Book>.
   */
  private static Map<Category, Optional<Book>> groupByCategoryMaxPrice(List<Book> books) {
    return books.stream()
        .collect(groupingBy(Book::getCategory, maxBy(comparingDouble(Book::getPrice))));
  }

  /**
   * Grouping by a category with max price for that category. Function collectingAndThen takes as
   * downstream collector and finisher function. Here, Optional::get is used as a finisher function.
   *
   * @param books List of books
   * @return HashMap containing key as a Category of book and value as a max price of that category.
   *     This method returns Optional<Book>.
   */
  private static Map<Category, Book> groupByCategoryMaxPriceNoOptional(List<Book> books) {
    return books.stream()
        .collect(
            groupingBy(
                Book::getCategory,
                collectingAndThen(maxBy(comparingDouble(Book::getPrice)), Optional::get)));
  }


  /**
   * Imperative style of grouping list of books
   *
   * @param books books List of books
   * @return HashMap containing key as a category and value as List of books
   */
  private static Map<Category, Integer> groupByCategoryCountImperative(List<Book> books) {
    Map<Category, Integer> grouped = new HashMap<>();
    for (Book book : books) {
      Integer count = grouped.get(book.getCategory());
      int nCount;
      if (count == null) {
        nCount = 1;
      } else {
        nCount = count + 1;
      }
      grouped.put(book.getCategory(), nCount);
    }
    return grouped;
  }
}
