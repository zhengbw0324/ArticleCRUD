package com.bw.service;

import com.bw.entity.Article;

import java.util.List;

public interface ArticleService {
    /**
     * 查询所有
     * @return
     */
    public List<Article> findAll();

    /**
     * 根据输入关键字模糊查询
     * @param title
     * @return
     */
    public List<Article> findByTitle(String title);

    /**
     * 保存文章
     * @param article
     * @return
     */
    public boolean saveArticle(Article article);

    /**
     * 修改文章
     * @param article
     */
    public boolean updateArticle(Article article);

    /**
     * 删除文章
     * @param title
     */
    public int deleteArticle(String title);


}
