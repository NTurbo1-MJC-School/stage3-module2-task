package com.mjc.school.service.utils;

import com.mjc.school.repository.model.implementation.AuthorEntity;
import com.mjc.school.repository.model.implementation.NewsEntity;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public interface ModelMapper {

  List<NewsDtoResponse> newsEntityListToDtoList(List<NewsEntity> newsEntityList);
  List<AuthorDtoResponse> authorEntityListToDtoList(List<AuthorEntity> authorEntityList);

  NewsDtoResponse newsEntityToDto(NewsEntity newsEntity);
  AuthorDtoResponse authorEntityToDto(AuthorEntity authorEntity);

  @Mappings({
    @Mapping(target = "createDate", ignore = true),
    @Mapping(target = "lastUpdatedDate", ignore = true)
  })
  NewsEntity dtoToAuthorEntity(NewsDtoRequest newsDtoRequest);
  AuthorEntity dtoToAuthorEntity(AuthorDtoRequest authorDtoRequest);
}
