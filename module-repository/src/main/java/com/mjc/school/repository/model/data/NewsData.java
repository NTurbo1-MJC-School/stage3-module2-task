package com.mjc.school.repository.model.data;


import com.mjc.school.repository.model.implementation.AuthorEntity;
import com.mjc.school.repository.model.implementation.NewsEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mjc.school.repository.utils.Utils.getRandomContentByFilePath;
import static com.mjc.school.repository.utils.Utils.getRandomDate;

public class NewsData {
  private static final String CONTENT_FILE_NAME = "content";
  private static final String NEWS_FILE_NAME = "news";
  private static NewsData newsData;
  private List<NewsEntity> newsList;

  private NewsData(List<AuthorEntity> authorEntityList) {
    init(authorEntityList);
  }

  public static NewsData getNewsData(List<AuthorEntity> authorEntityList) {
    if (newsData == null) {
      newsData = new NewsData(authorEntityList);
    }
    return newsData;
  }

  private void init(List<AuthorEntity> authorEntityList) {
    newsList = new ArrayList<>();
    Random random = new Random();
    for (long i = 1; i <= 20; i++) {
      LocalDateTime date = getRandomDate();
      newsList.add(
          new NewsEntity(
              i,
              getRandomContentByFilePath(NEWS_FILE_NAME),
              getRandomContentByFilePath(CONTENT_FILE_NAME),
              date,
              date,
              authorEntityList.get(random.nextInt(authorEntityList.size())).getId()));
    }
  }

  public List<NewsEntity> getNewsList() {
    return newsList;
  }
}
