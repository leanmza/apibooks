package com.lean.news.controller;

import com.lean.news.entity.CustomUser;
import com.lean.news.entity.News;
import com.lean.news.exception.MyException;

import com.lean.news.service.NewsService;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
@RestController
@RequestMapping("/api")
public class PortalController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/newsList")
    public List<News> getNewsList() {
        return newsService.newsList();
    }

    @GetMapping("/{id}")
    public News getNews(@PathVariable String id, Principal principal) {
        return newsService.getOne(id);
    }

    @GetMapping("/category/{category}")
    public List<News> getCategoryList(@PathVariable String category) {
        return newsService.categoryList(category);
    }

    @GetMapping("/search")
    public List<News> getNewsByTitle(@RequestParam("word") String word) {
        return newsService.findNewsByTitle(word);
    }

    @GetMapping("/writer/{id}")
    public List<News> getNewByWriter(@PathVariable String id) {
        return newsService.findNewsByWriter(id);
    }

    
     @PreAuthorize("hasAnyRole('ROLE_WRITER', 'ROLE_EDITOR')")
    @PostMapping("/postNews")
    public ResponseEntity<String> createNews(@RequestParam String title, @RequestParam String body,
            @RequestParam String category, @RequestParam(required = false) boolean subscriberContent, MultipartFile imageFile, Principal principal)
            throws MyException {
        try {
            String writerEmail = principal.getName();
            newsService.createNews(title, body, imageFile, writerEmail, category, subscriberContent);
            return ResponseEntity.ok("Noticia guardada exitosamente");
        } catch (MyException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la noticia: " + e.getMessage());
        }
    }

    
    @PreAuthorize("hasAnyRole('ROLE_WRITER', 'ROLE_EDITOR')")
    @PutMapping("/editNews/{id}")
    public String updateNews(@PathVariable String id, @RequestParam String title, @RequestParam String body,
            @RequestParam String category, @RequestParam(required = false) boolean subscriberContent, MultipartFile imageFile, Principal principal)
            throws MyException {

        newsService.actualizeNews(id, title, body, imageFile, title, category, subscriberContent);

        return "news actualizada";
    }

    @PreAuthorize("hasAnyRole('ROLE_WRITER', 'ROLE_EDITOR')")
    @DeleteMapping("/deleteNews/{id}")
    public String deleteNews(@PathVariable String id) throws MyException {

        newsService.deleteNews(id);

        return "news borrada";
    }

}
