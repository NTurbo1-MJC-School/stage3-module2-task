package com.mjc.school.repository.model.implementation;

import com.mjc.school.repository.model.BaseEntity;

import java.time.LocalDateTime;
import java.util.Objects;

public class NewsEntity implements BaseEntity<Long> {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdatedDate;
    private Long authorId;

    public NewsEntity(
            Long id,
            String title,
            String content,
            LocalDateTime createDate,
            LocalDateTime lastUpdatedDate,
            Long authorId
    ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.lastUpdatedDate = lastUpdatedDate;
        this.authorId = authorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewsEntity newsEntity)) return false;
        return Objects.equals(id, newsEntity.id) && Objects.equals(title, newsEntity.title) && Objects.equals(content, newsEntity.content) && Objects.equals(createDate, newsEntity.createDate) && Objects.equals(lastUpdatedDate, newsEntity.lastUpdatedDate) && Objects.equals(authorId, newsEntity.authorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, createDate, lastUpdatedDate, authorId);
    }
}
