package com.mjc.school.repository.utils;

import com.mjc.school.repository.model.implementation.AuthorEntity;
import com.mjc.school.repository.model.implementation.NewsEntity;

import java.util.List;

import static com.mjc.school.repository.model.data.AuthorData.getAuthorData;
import static com.mjc.school.repository.model.data.NewsData.getNewsData;

public class DataSource {

  private final List<NewsEntity> news;
  private final List<AuthorEntity> authors;

  private DataSource() {
    List<AuthorEntity> authorList = getAuthorData().getAuthorList();
    authors = authorList;
    news = getNewsData(authorList).getNewsList();
  }

  public static DataSource getInstance() {
    return LazyDataSource.INSTANCE;
  }

  public List<NewsEntity> getNews() {
    return news;
  }
  public List<AuthorEntity> getAuthors() {
    return authors;
  }

  private static class LazyDataSource {
    static final DataSource INSTANCE = new DataSource();
  }
}
