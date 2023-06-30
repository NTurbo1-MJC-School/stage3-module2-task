package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.implementation.AuthorEntity;
import com.mjc.school.repository.model.implementation.NewsEntity;
import com.mjc.school.repository.utils.DataSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository implements BaseRepository<AuthorEntity, Long> {

    private final DataSource dataSource;

    public AuthorRepository() {
        this.dataSource = DataSource.getInstance();
    }

    @Override
    public List<AuthorEntity> readAll() {
        return dataSource.getAuthors();
    }

    @Override
    public Optional<AuthorEntity> readById(Long id) {
        return Optional.of(dataSource.getAuthors().stream()
                .filter(author -> id.equals(author.getId()))
                .findFirst()
                .get());
    }

    @Override
    public AuthorEntity create(AuthorEntity entity) {
        List<AuthorEntity> authors = dataSource.getAuthors();
        authors.sort(Comparator.comparing(AuthorEntity::getId));
        if (!authors.isEmpty()) {
            entity.setId(authors.get(authors.size() - 1).getId() + 1);
        } else {
            entity.setId(1L);
        }
        authors.add(entity);
        return entity;
    }

    @Override
    public AuthorEntity update(AuthorEntity entity) {
        AuthorEntity authorEntity = readById(entity.getId()).get();
        authorEntity.setName(entity.getName());
        return authorEntity;
    }

    @Override
    public boolean deleteById(Long id) {
        List<AuthorEntity> deleteList = new ArrayList<>();
        deleteList.add(this.readById(id).get());
        return dataSource.getAuthors().removeAll(deleteList);
    }

    @Override
    public boolean existById(Long id) {
        return dataSource.getAuthors().stream().anyMatch(news -> id.equals(news.getId()));
    }
}
