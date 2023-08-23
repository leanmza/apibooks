/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lean.apiNews.controller;

import com.lean.apiNews.entity.News;
import com.lean.apiNews.exception.MyException;
import com.lean.apiNews.service.NewsService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Lean
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class PortalController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/newsList")
    public List<News> getNewsList() {
        return newsService.newsList();
    }

//    @PostMapping("/createNews") //Modelo para trabajar con JSON
//    public String postCreateNews(@RequestBody News news) throws MyException {
//      
//         newsService.createNews(news);
//
//           return "news guardada";
//    }
    
    @PostMapping("/postNews")
    public String postNews(@RequestParam String title, @RequestParam String body, MultipartFile imageFile) throws MyException {
      
         newsService.createNews(title, body, imageFile);

           return "news guardada";
    }
    
    @PutMapping("/editNews/{id}")
    public String updateNews(@PathVariable String  id, @RequestBody News news) throws MyException{
        
        newsService.updateNews(id, news);
        
        return "news actualizada";
    }
    
        @DeleteMapping("/deleteNews/{id}")
    public String updateNews(@PathVariable String  id) throws MyException{
        
        newsService.deleteNews(id);
        
        return "news borrada";
    }
}
