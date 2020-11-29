package dev.techdozo.stream.examples;

import dev.techdozo.stream.examples.model.Book;
import dev.techdozo.stream.examples.model.Catalog;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Stream;

/** Examples of Stream creation */
@Slf4j
public class StreamCreation {

  public static void main(String[] args) {
    List<Book> books = Catalog.books();
    of();
    ofNullable();

    // Stream.ofNullable(..) provides a convenient null check
    Stream<Integer> empty = toStream(null);
    empty.forEach(e -> log.info("" + e));

    // Non empty stream example
    Stream<Integer> numbers = toStream(List.of(1, 2, 3, 4));
    numbers.forEach(e -> log.info("" + e));

    // Non empty stream example
    Stream<Integer> numbers1 = toStreamOptional(List.of(5, 6, 7));
    numbers1.forEach(e -> log.info("" + e));

    fromArray();

    fromCollection();

    fromBuilder();

    fromGenerator();

    fromIterator();
  }

  /** Creating stream from individual element */
  private static void of() {
    log.info("Creating streams from individual elements..");
    Stream<String> numbers = Stream.of("One", "Two", "Three");
    numbers.forEach(log::info);
  }

  /** Creating nullable stream from individual element */
  private static void ofNullable() {
    log.info("Creating streams from Nullable..");
    Stream<String> numbers = Stream.ofNullable("One");
    numbers.forEach(log::info);
    Stream<String> empty = Stream.empty();
    empty.forEach(log::info);
  }

  /**
   * The method Stream.ofNullable provides a simplified null check.
   *
   * @param numbers - possibly a null collection
   * @return - empty stream if numbers is null
   */
  private static Stream<Integer> toStream(Collection<Integer> numbers) {
    return Stream.ofNullable(numbers).flatMap(Collection::stream);
  }

  /**
   * The method Stream.ofNullable provides a simplified null check.
   *
   * @param numbers - possibly a null collection
   * @return - empty stream if numbers is null
   */
  private static Stream<Integer> toStreamOptional(Collection<Integer> numbers) {
    return Optional.ofNullable(numbers).stream().flatMap(Collection::stream);
  }

  /** Creating stream from array */
  private static void fromArray() {
    log.info("Creating streams from array..");
    String[] names = new String[] {"Jack", "Jill"};

    Stream<String> stream1 = Stream.of(names);
    stream1.forEach(log::info);

    Stream<String> stream2 = Arrays.stream(names);
    stream2.forEach(log::info);
  }

  /** Stream creation example from Collection.. */
  private static void fromCollection() {
    log.info("Creating streams from collection..");
    List<String> namesList = List.of("Jack", "Jill");
    Stream<String> stream = namesList.stream();
    stream.forEach(log::info);
  }

  /** Stream creation example using builder.. */
  private static void fromBuilder() {
    log.info("Creating streams using builder..");
    Stream<String> stream = Stream.<String>builder().add("Jack").add("Jill").build();
    stream.forEach(log::info);
  }

  /** Stream creation example using Generator.. */
  private static void fromGenerator() {
    log.info("Creating streams using generator..");
    Stream<Integer> randomNumbers = Stream.generate(new Random()::nextInt).limit(10);
    randomNumbers.forEach(e -> log.info("" + e));
  }

  /** Stream creation example using Iterator.. */
  private static void fromIterator() {
    log.info("Creating streams using Iterator..");
    Stream<Integer> even = Stream.iterate(0, n -> n + 2).limit(10);
    even.forEach(e -> log.info("" + e));
  }
}
