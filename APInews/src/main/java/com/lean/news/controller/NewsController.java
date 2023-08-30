/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lean.news.controller;

import com.lean.news.entity.News;
import com.lean.news.enums.Category;
import com.lean.news.exception.MyException;
import com.lean.news.service.NewsService;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Lean
 */
@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

  

    @PreAuthorize("hasAnyRole('ROLE_WRITER', 'ROLE_EDITOR')")
    @Transactional
    @GetMapping("/manage")
    public String dashboardNews(Model model) {

        List<News> newsList = newsService.newsList();

        model.addAttribute("news", newsList);

        model.addAttribute("categorys", Category.values());
        return "manageNews.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_WRITER', 'ROLE_EDITOR')")
    @Transactional
    @GetMapping("/manage/dateAsc")
    public String dashboardSortDateAsc(Model model) {

        List<News> newsList = newsService.newsListAsc();

        model.addAttribute("news", newsList);

        model.addAttribute("categorys", Category.values());
        return "manageNews.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_WRITER', 'ROLE_EDITOR')")
    @Transactional
    @GetMapping("/manage/writerAZ")
    public String dashboardSortWriterAZ(Model model) {

        List<News> newsList = newsService.newsListWriterAZ();

        model.addAttribute("news", newsList);

        model.addAttribute("categorys", Category.values());
        return "manageNews.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_WRITER', 'ROLE_EDITOR')")
    @Transactional
    @GetMapping("/manage/writerZA")
    public String dashboardSortWriterZA(Model model) {

        List<News> newsList = newsService.newsListWriterZA();

        model.addAttribute("news", newsList);

        model.addAttribute("categorys", Category.values());
        return "manageNews.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_WRITER', 'ROLE_EDITOR')")
    @Transactional
    @GetMapping("/manage/{category}")
    public String dashboardCategory(@PathVariable String category, Model model) {

        List<News> newsList = newsService.categoryList(category);

        model.addAttribute("news", newsList);

        model.addAttribute("categorys", Category.values());
        return "manageNews.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_WRITER', 'ROLE_EDITOR')")
    @Transactional
    @GetMapping("/manage/titleAZ")
    public String dashboardSortTitleAZ(Model model) {

        List<News> newsList = newsService.newsListTitleAsc();

        model.addAttribute("news", newsList);

        model.addAttribute("categorys", Category.values());
        return "manageNews.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_WRITER', 'ROLE_EDITOR')")
    @Transactional
    @GetMapping("/manage/titleZA")
    public String dashboardSortTitleZA(Model model) {

        List<News> newsList = newsService.newsListTitleDesc();

        model.addAttribute("news", newsList);

        model.addAttribute("categorys", Category.values());
        return "manageNews.html";
    }

}
