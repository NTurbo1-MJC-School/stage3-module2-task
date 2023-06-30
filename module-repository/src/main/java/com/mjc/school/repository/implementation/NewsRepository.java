package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.implementation.NewsEntity;
import com.mjc.school.repository.utils.DataSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
public class NewsRepository implements BaseRepository<NewsEntity, Long> {

  private final DataSource dataSource;

  public NewsRepository() {
    this.dataSource = DataSource.getInstance();
  }

  @Override
  public List<NewsEntity> readAll() {
    return dataSource.getNews();
  }

  @Override
  public Optional<NewsEntity> readById(Long newsId) {
    return Optional.of(dataSource.getNews().stream()
        .filter(news -> newsId.equals(news.getId()))
        .findFirst()
        .get());
  }

  @Override
  public NewsEntity create(NewsEntity entity) {
    List<NewsEntity> newsEntity = dataSource.getNews();
    newsEntity.sort(Comparator.comparing(NewsEntity::getId));
    if (!newsEntity.isEmpty()) {
      entity.setId(newsEntity.get(newsEntity.size() - 1).getId() + 1);
    } else {
      entity.setId(1L);
    }
    newsEntity.add(entity);
    return entity;
  }

  @Override
  public NewsEntity update(NewsEntity entity) {
    NewsEntity newsEntity = readById(entity.getId()).get();
    newsEntity.setTitle(entity.getTitle());
    newsEntity.setContent(entity.getContent());
    newsEntity.setLastUpdatedDate(entity.getLastUpdatedDate());
    newsEntity.setAuthorId(entity.getAuthorId());
    return newsEntity;
  }

  @Override
  public boolean deleteById(Long newsId) {
    List<NewsEntity> deleteList = new ArrayList<>();
    deleteList.add(this.readById(newsId).get());
    return dataSource.getNews().removeAll(deleteList);
  }

  @Override
  public boolean existById(Long newsId) {
    return dataSource.getNews().stream().anyMatch(news -> newsId.equals(news.getId()));
  }
}
