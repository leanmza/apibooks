/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lean.apiNews.service;

import com.lean.apiNews.entity.Image;
import com.lean.apiNews.entity.News;
import com.lean.apiNews.exception.MyException;
import com.lean.apiNews.repository.NewsRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Lean
 */
@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private ImageService imageService;

    @Transactional
    public News createNews(String title, String body, MultipartFile imageFile) throws MyException {

        News news = new News();
        
        news.setTitle(title);
                
        news.setBody(body);
        
        news.setDate(LocalDate.now());
        
        news.setTime(LocalTime.now());
        
        
         if (imageFile != null) {

            Image image = imageService.saveImage(imageFile);
            news.setImage(image);
        }
        newsRepository.save(news);

        return news;
    }
//    @Transactional
//    public News createNews(News news) throws MyException {
//
//        newsRepository.save(news);
//
//        return news;
//    }

    @Transactional
    public void updateNews(String id, News news) throws MyException {

        Optional<News> optionalNews = newsRepository.findById(id);

        if (optionalNews.isPresent()) {
            News updatedNews = optionalNews.get();

            if (news.getTitle() != null) {
                updatedNews.setTitle(news.getTitle());
            }
            if (news.getBody() != null) {
                updatedNews.setBody(news.getBody());
            }

            newsRepository.save(updatedNews);

        }
    }

    @Transactional
    public void deleteNews(String id) {
        Optional<News> optionalNews = newsRepository.findById(id);

        if (optionalNews.isPresent()) {
            News news = optionalNews.get();
            newsRepository.delete(news);
        }
    }

    @Transactional(readOnly = true)
    public List<News> newsList() {
        List<News> newsList = new ArrayList();
        newsList = newsRepository.findAll();
        return newsList;
    }

    @Transactional(readOnly = true)
    public News getOne(String id) {
        News news = new News();
        news = newsRepository.getOne(id);
        return news;
    }

    public void validate(String title, String body) throws MyException {
        if (title.isEmpty() || title == null) {
            throw new MyException("El título no puede estar vacío o nulo");
        }
        if (body.isEmpty() || body == null) {
            throw new MyException("El cuerpo de la noticia no puede estar vacío o nulo");
        }
    }

}
