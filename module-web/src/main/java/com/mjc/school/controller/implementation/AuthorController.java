package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.factory.ServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AuthorController implements BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> {
  @Autowired
  private final BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> authorService;

  public AuthorController() {
    authorService = ServiceFactory.getInstance().getAuthorService();
  }

  public List<AuthorDtoResponse> readAll() {
    return authorService.readAll();
  }

  public AuthorDtoResponse readById(Long newsId) {
    return authorService.readById(newsId);
  }

  public AuthorDtoResponse create(AuthorDtoRequest dtoRequest) {
    return authorService.create(dtoRequest);
  }

  public AuthorDtoResponse update(AuthorDtoRequest dtoRequest) {
    return authorService.update(dtoRequest);
  }

  public boolean deleteById(Long newsId) {
    return authorService.deleteById(newsId);
  }
}
