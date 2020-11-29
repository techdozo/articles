package dev.techdozo.stream.examples.model;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CatalogTest {

  public static final int CATALOG_SIZE = 6;

  @Test
  public void catalogSizeShouldBeFinite() {
    List<Book> books = Catalog.books();
    assertThat(books.size(), is(CATALOG_SIZE));
  }
}
