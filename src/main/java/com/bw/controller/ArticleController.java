package com.bw.controller;

import com.bw.entity.Article;
import com.bw.entity.Content;
import com.bw.service.ArticleService;
import com.bw.util.ShowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ShowUtil showUtil;

    public static final int CHARNUM=30;//每页字符数量

    /**
     * 查询所有文章并显示
     * @param response
     */
    @RequestMapping("/findAll")
    public void findAll(HttpServletResponse response){
        List<Article> articles=articleService.findAll();
        showUtil.showArticle(articles,response);
    }

    /**
     * 通过输入关键字模糊查询
     * @param title
     * @param response
     */
    @RequestMapping("/findByTitle")
    public void findByTitle(String title,HttpServletResponse response) {
        title="%"+title+"%";
        List<Article> articles=articleService.findByTitle(title);
        showUtil.showArticle(articles,response);
    }

    /**
     * 保存文章
     * @param title
     * @param content
     * @param image
     * @return
     */
    @RequestMapping("/saveArticle")
    public String saveArticle(String title, String content, MultipartFile image) {
        List<Content> contents=new ArrayList<>();

        Article article=new Article();
        article.setTitle(title);
        article.setDate(new Date());

        try {
            article.setImage(image.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i=0;i<content.length()/CHARNUM+1;i++){
            String page=null;
            if((i+1)*CHARNUM>=content.length()){
                page=content.substring(i*CHARNUM,content.length());
            }else {
                page=content.substring(i*CHARNUM,(i+1)*CHARNUM);
            }
            if(page!=null){
                Content pageContent=new Content();
                pageContent.setAtitle(title);
                pageContent.setPage(page);
                contents.add(pageContent);
            }
        }
        article.setContents(contents);

        if(!articleService.saveArticle(article)){
            return "error";
        }else {
            return "success";
        }
    }

    /**
     * 更新文章
     * @param title
     * @param content
     * @param image
     * @return
     */
    @RequestMapping("/updateArticle")
    public String updateArticle(String title, String content, MultipartFile image) {
        List<Content> contents=new ArrayList<>();

        Article article=new Article();
        article.setTitle(title);
        article.setDate(new Date());

        try {
            article.setImage(image.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i=0;i<content.length()/CHARNUM+1;i++){
            String page=null;
            if((i+1)*CHARNUM>=content.length()){
                page=content.substring(i*CHARNUM,content.length());
            }else {
                page=content.substring(i*CHARNUM,(i+1)*CHARNUM);
            }
            if(page!=null){
                Content pageContent=new Content();
                pageContent.setAtitle(title);
                pageContent.setPage(page);
                contents.add(pageContent);
            }
        }
        article.setContents(contents);

        if(!articleService.updateArticle(article)){
            return "error";
        }else {
            return "success";
        }
    }

    /**
     * 删除文章
     * @param title
     * @return
     */
    @RequestMapping("/deleteArticle")
    public String deleteArticle(String title) {
        if(articleService.deleteArticle(title)==0){
            return "error";
        }else {
            return "success";
        }
    }

}
