package com.bw.service.impl;

import com.bw.dao.ArticleMapper;
import com.bw.dao.ContentMapper;
import com.bw.entity.Article;
import com.bw.entity.Content;
import com.bw.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    ContentMapper contentMapper;

    /**
     * 查询所有文章
     * @return
     */
    @Override
    public List<Article> findAll() {
        List<Article> articles=articleMapper.findAll();
        return articles;
    }

    /**
     * 通过输入模糊查询
     * @param title
     * @return
     */
    @Override
    public List<Article> findByTitle(String title) {
        List<Article> articles=articleMapper.findByTitle(title);
        return articles;
    }

    /**
     * 保存文章
     * @param article
     * @return
     */
    @Override
    public boolean saveArticle(Article article) {
        int i=0,j=1;
        i=articleMapper.saveArticle(article);
        for(Content content:article.getContents()){
            if(contentMapper.saveContent(content)==0){
                j=0;
            }
        }
        if(i!=0&&j!=0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 更新文章
     * @param article
     * @return
     */
    @Override
    public boolean updateArticle(Article article) {
        int i=0,j=1;
        i=articleMapper.updateArticle(article);
        //在表content中不能直接使用update更新，因为多条记录atitle相同，所以先删除再插入新内容。
        contentMapper.deleteContent(article.getTitle());
        for(Content content:article.getContents()){
            if(contentMapper.saveContent(content)==0){
                j=0;
            }
        }
        if(i!=0&&j!=0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 删除文章
     * @param title
     * @return
     */
    @Override
    public int deleteArticle(String title) {
        return articleMapper.deleteArticle(title);
    }
    
}
