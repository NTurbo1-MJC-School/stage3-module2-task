package com.mjc.school.repository.factory;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.implementation.AuthorRepository;
import com.mjc.school.repository.implementation.NewsRepository;
import com.mjc.school.repository.model.implementation.AuthorEntity;
import com.mjc.school.repository.model.implementation.NewsEntity;

public class RepositoryFactory {
  private static RepositoryFactory instance;
  private final BaseRepository<NewsEntity, Long> newsRepository;
  private final BaseRepository<AuthorEntity, Long> authorRepository;

  private RepositoryFactory() {
    newsRepository = new NewsRepository();
    authorRepository = new AuthorRepository();
  }

  public static RepositoryFactory getInstance() {
    if (instance == null) {
      instance = new RepositoryFactory();
    }
    return instance;
  }

  public BaseRepository<NewsEntity, Long> getNewsRepository() {
    return newsRepository;
  }

  public BaseRepository<AuthorEntity, Long> getAuthorRepository() {
    return authorRepository;
  }
}
