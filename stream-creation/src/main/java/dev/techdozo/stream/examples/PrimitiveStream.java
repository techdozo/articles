package dev.techdozo.stream.examples;

import dev.techdozo.stream.examples.model.Book;
import dev.techdozo.stream.examples.model.Catalog;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/** Examples of Primitive Stream */
@Slf4j
public class PrimitiveStream {

  public static void main(String[] args) {
    List<Book> books = Catalog.books();
    log.info("Sum of book price {} $", priceOfAllBooks(books));
    log.info("Sum of book price {} $", priceOfAllBooksSimplified(books));
    of();
    range();
    generate();
    fibonacci();
    iterate();
  }

  /**
   * Calculating sum of all books from Catalog using Boxed Double stream
   *
   * @param books - collection of book
   * @return - sum of all books
   */
  private static double priceOfAllBooks(List<Book> books) {
    Double allBooksPrice = books.stream().map(Book::getPrice).reduce(0d, Double::sum);
    return allBooksPrice;
  }

  /**
   * Calculating sum of all books from Catalog using primitive stream
   *
   * @param books - collection of book
   * @return - sum of all books
   */
  private static double priceOfAllBooksSimplified(List<Book> books) {
    double allBooksPrice = books.stream().mapToDouble(Book::getPrice).sum();
    return allBooksPrice;
  }

  /** Creating primitive stream from individual element */
  private static void of() {
    log.info("Creating streams from individual elements..");
    IntStream intOne = IntStream.of(1);
    intOne.forEach(n -> log.info("" + n));

    IntStream intOneTwo = IntStream.of(1, 2);
    intOneTwo.forEach(n -> log.info("" + n));

    DoubleStream doubleOne = DoubleStream.of(1);
    doubleOne.forEach(n -> log.info("" + n));

    LongStream longOneTwo = LongStream.of(1, 2);
    longOneTwo.forEach(n -> log.info("" + n));
  }

  /** Creating primitive stream for a range of Number */
  private static void range() {
    log.info("Creating streams for a range..");
    IntStream oneToNine = IntStream.range(1, 10);
    oneToNine.forEach(n -> log.info("" + n));
    // Range inclusive
    IntStream oneToTen = IntStream.rangeClosed(1, 10);
    oneToTen.forEach(n -> log.info("" + n));
  }

  /** Creating primitive primitive stream for a range of Number */
  private static void generate() {
    log.info("Creating streams using Generator..");

    IntStream tenOnes = IntStream.generate(() -> 1).limit(10);
    tenOnes.forEach(n -> log.info("" + n));

    DoubleStream tenRandomDouble = DoubleStream.generate(() -> new Random().nextDouble()).limit(10);
    tenRandomDouble.forEach(n -> log.info("" + n));
  }

  /** Example of Fibonacci number stream */
  private static void fibonacci() {
    log.info("Creating streams of Fibonacci numbers..");

    Fibonacci fibonacci = new Fibonacci();
    IntStream fibStream = fibonacci.stream().limit(10);
    fibStream.forEach(n -> log.info("" + n));
  }

  /** Creating streams using iterator */
  private static void iterate() {
    log.info("Creating streams using iterator..");
    IntStream evenNumbers = IntStream.iterate(0, n -> n + 2).limit(10);
    evenNumbers.forEach(n -> log.info("" + n));
  }

  static class Fibonacci {
    private int prev = 0;
    private int curr = 1;

    private int next() {
      int temp = prev + curr;
      prev = curr;
      curr = temp;
      return curr;
    }

    public IntStream stream() {
      return IntStream.generate(this::next);
    }
  }
}
