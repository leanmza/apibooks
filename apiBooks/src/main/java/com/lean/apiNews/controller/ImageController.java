/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lean.apiNews.controller;

import com.lean.apiNews.entity.News;
import com.lean.apiNews.repository.ImageRepository;
import com.lean.apiNews.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Lean
 */
@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    NewsService newsService;



    @Transactional
    @GetMapping("/news/{id}")
    public ResponseEntity<byte[]> imageNews(@PathVariable String id) {
        News news = newsService.getOne(id);

        byte[] image = news.getImage().getContent();

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }
}
