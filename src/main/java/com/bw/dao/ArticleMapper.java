package com.bw.dao;

import com.bw.entity.Article;
import com.bw.entity.Content;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleMapper {
    /**
     * 查询所有文章
     * @return
     */
    @Select("SELECT * FROM article")
    @Results(id = "articleMap",value ={
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "title",property = "title"),
            @Result(column = "date",property = "date"),
            @Result(column = "image",property = "image",typeHandler = org.apache.ibatis.type.BlobTypeHandler.class),
            @Result(column = "title",property = "contents",many = @Many(select = "com.bw.dao.ContentMapper.findContentByAtitle"))
    })
    public List<Article> findAll();


    /**
     * 根据输入关键字模糊查询
     * @param title
     * @return
     */
    @Select("select * from article where title like #{title}")
    @ResultMap("articleMap")
    public List<Article> findByTitle(String title);

    /**
     * 保存文章
     * @param article
     */
    @Insert("insert into article(title,image,date) value (#{title},#{image},#{date})")
    @ResultMap("articleMap")
    public int saveArticle(Article article);


    /**
     * 修改文章
     * @param article
     */
    @Update("update article set image=#{image},date=#{date} where title=#{title}")
    @ResultMap("articleMap")
    public int updateArticle(Article article);

    /**
     * 删除文章
     * @param title
     */
    @Delete("delete from article where title=#{title}")
    @ResultMap("articleMap")
    public int deleteArticle(String title);

}
