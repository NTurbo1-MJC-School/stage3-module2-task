package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.factory.ServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class NewsController implements BaseController<NewsDtoRequest, NewsDtoResponse, Long> {
  @Autowired
  private final BaseService<NewsDtoRequest, NewsDtoResponse, Long> newsService;

  public NewsController() {
    newsService = ServiceFactory.getInstance().getNewsService();
  }

  public List<NewsDtoResponse> readAll() {
    return newsService.readAll();
  }

  public NewsDtoResponse readById(Long newsId) {
    return newsService.readById(newsId);
  }

  public NewsDtoResponse create(NewsDtoRequest dtoRequest) {
    return newsService.create(dtoRequest);
  }

  public NewsDtoResponse update(NewsDtoRequest dtoRequest) {
    return newsService.update(dtoRequest);
  }

  public boolean deleteById(Long newsId) {
    return newsService.deleteById(newsId);
  }
}
