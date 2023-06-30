package com.mjc.school.service.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.factory.RepositoryFactory;
import com.mjc.school.repository.implementation.NewsRepository;
import com.mjc.school.repository.model.implementation.AuthorEntity;
import com.mjc.school.repository.model.implementation.NewsEntity;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.utils.ModelMapper;
import com.mjc.school.service.validator.AuthorValidator;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mjc.school.service.exceptions.ServiceErrorCode.NEWS_ID_DOES_NOT_EXIST;
import static com.mjc.school.service.validator.AuthorValidator.getAuthorValidator;

@Service
public class AuthorService implements BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> {

    @Autowired
    private final BaseRepository<AuthorEntity, Long> authorRepository;
    @Autowired
    private final BaseRepository<NewsEntity, Long> newsRepository;
    @Autowired
    private final AuthorValidator authorValidator;
    private ModelMapper mapper = Mappers.getMapper(ModelMapper.class);

    public AuthorService() {
        this.authorRepository = RepositoryFactory.getInstance().getAuthorRepository();
        this.newsRepository = RepositoryFactory.getInstance().getNewsRepository();
        this.authorValidator = getAuthorValidator();
    }

    @Override
    public List<AuthorDtoResponse> readAll() {
        return mapper.authorEntityListToDtoList(authorRepository.readAll());
    }

    @Override
    public AuthorDtoResponse readById(Long authorId) {
        authorValidator.validateAuthorId(authorId);
        if (authorRepository.existById(authorId)) {
            AuthorEntity authorEntity = authorRepository.readById(authorId).get();
            return mapper.authorEntityToDto(authorEntity);
        } else {
            throw new NotFoundException(
                    String.format(String.valueOf(NEWS_ID_DOES_NOT_EXIST.getMessage()), authorId));
        }
    }

    @Override
    public AuthorDtoResponse create(AuthorDtoRequest dtoRequest) {
        authorValidator.validateAuthorDto(dtoRequest);
        AuthorEntity entity = mapper.dtoToAuthorEntity(dtoRequest);
        AuthorEntity authorEntity = authorRepository.create(entity);
        return mapper.authorEntityToDto(authorEntity);
    }

    @Override
    public AuthorDtoResponse update(AuthorDtoRequest dtoRequest) {
        authorValidator.validateAuthorId(dtoRequest.id());
        authorValidator.validateAuthorDto(dtoRequest);
        if (authorRepository.existById(dtoRequest.id())) {
            AuthorEntity entity = mapper.dtoToAuthorEntity(dtoRequest);
            AuthorEntity authorEntity = authorRepository.update(entity);
            return mapper.authorEntityToDto(authorEntity);
        } else {
            throw new NotFoundException(
                    String.format(NEWS_ID_DOES_NOT_EXIST.getMessage(), dtoRequest.id()));
        }
    }

    @Override
    public boolean deleteById(Long authorId) {
        authorValidator.validateAuthorId(authorId);
        if (authorRepository.existById(authorId)) {
            for (NewsEntity news : newsRepository.readAll()) {
                if (news.getAuthorId().equals(authorId)) {
                    newsRepository.deleteById(news.getId());
                }
            }
            return authorRepository.deleteById(authorId);
        } else {
            throw new NotFoundException(String.format(NEWS_ID_DOES_NOT_EXIST.getMessage(), authorId));
        }
    }
}
