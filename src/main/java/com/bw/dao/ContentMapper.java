package com.bw.dao;

import com.bw.entity.Article;
import com.bw.entity.Content;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentMapper {

    @Select("select * from content where atitle=#{atitle}")
    public List<Content> findContentByAtitle(String atitle);

    @Insert("insert into content(atitle,page) value (#{atitle},#{page})")
    public int saveContent(Content content);


    @Delete("delete from content where atitle=#{atitle}")
    public void deleteContent(String atitle);
}
